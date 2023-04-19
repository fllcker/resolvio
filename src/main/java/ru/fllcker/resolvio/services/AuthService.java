package ru.fllcker.resolvio.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.resolvio.dto.JwtAuthentication;
import ru.fllcker.resolvio.dto.JwtRequest;
import ru.fllcker.resolvio.dto.JwtResponse;
import ru.fllcker.resolvio.dto.SignUpDto;
import ru.fllcker.resolvio.models.Token;
import ru.fllcker.resolvio.models.User;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder encoder;
    private final UsersService usersService;
    private final TokensService tokensService;
    private final JwtProvider jwtProvider;

    public JwtResponse login(JwtRequest jwtRequest) {
        User user = usersService.findByEmail(jwtRequest.getEmail());

        if (!encoder.matches(jwtRequest.getPassword(), user.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong data");

        String accessToken = jwtProvider.generateToken(user, false);
        String refreshToken = jwtProvider.generateToken(user, true);
        Token token = Token.builder()
                .user(user)
                .value(refreshToken).build();

        tokensService.save(token);
        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse signup(SignUpDto dto) {
        if (usersService.existsByEmail(dto.getEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email already exists!");

        User user = User.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .name(dto.getName())
                .build();

        usersService.save(user);

        String accessToken = jwtProvider.generateToken(user, false);
        String refreshToken = jwtProvider.generateToken(user, true);
        Token token = Token.builder()
                .user(user)
                .value(refreshToken).build();

        tokensService.save(token);

        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse getTokensByRefresh(String refreshToken, boolean refresh) {
        String subject = jwtProvider.getRefreshClaims(refreshToken)
                .getSubject();

        if (!jwtProvider.validateRefreshToken(refreshToken))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        Token token = tokensService.findByValue(refreshToken);

        if (!token.getValue().equals(refreshToken) || !token.getUser().getEmail().equals(subject))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);


        User user = usersService.findByEmail(subject);

        String accessToken = jwtProvider.generateToken(user, false);
        String newRefreshToken = null;

        if (refresh) {
            newRefreshToken = jwtProvider.generateToken(user, true);
            tokensService.save(Token.builder().user(user).value(refreshToken).build());
        }

        return new JwtResponse(accessToken, newRefreshToken);
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}