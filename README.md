ㅤ<h1 align="center">Resolvio

[![](https://img.shields.io/badge/Developed%20by-fllcker-%236DB33F)](https://github.com/fllcker)
![](https://img.shields.io/badge/JDK-17-%23E76F00)
![](https://img.shields.io/badge/Spring%20Boot-3.0.5-%236DB33F)
[![](https://img.shields.io/badge/DBMS-Postgres-%234476ff)](https://www.postgresql.org/)
</h1>


<p>The project is a question-answer service. The project uses <strong>web sockets</strong> to send notifications (with an <strong>event-driven architecture</strong>), uses caching with <strong>redis</strong> to get questions for keywords, and also has <strong>analytics collection</strong>.</p>

<h1>Interesting features</h1>
<li>Using <strong>web sockets</strong> to send notifications (with an <strong>event-driven architecture</strong>)</li>
<li>Caching with <strong>redis</strong></li>
<li><strong>Analytics collection</strong></li>

<h1>Important endpoints</h1>

<li>/swagger/swagger-ui/index.html</li>
<li>/actuator/health</li>
<li>/ws/topic/question-ID (Subscribe to question to receive notifications of new replies</li>
<li>/ws/topic/answer-ID (Subscribe to answer to receive notifications of activities from answer)</li>
