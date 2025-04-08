<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="header.html"/>
    <title>Finish</title>
</head>

<body>
<%
    int counter = (int) session.getAttribute("counter");
    long result = (long) request.getAttribute("result");
%>
<div class="card text-center">
    <div class="card-header">Квест завершен</div>
    <div class="card-body">
        <h5 class="card-title">По результатам нашего собеседования</h5>
            <% if (result < 0) { %>
                <p class="card-text">К сожалению, мы решили продолжить с другим кандидатом.</p>
            <% } else { %>
                <p class="card-text">Мы рады предложить вам стать частью нашей команды!</p>
            <% } %>
        <a href="/quest" class="btn btn-primary position-relative mb-2">Пройти квест еще раз
            <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                <%=counter%>
            </span>
        </a>
        <div>
            <a href="/quest?erase=true" class="text-decoration-none">Сбросить счетчик сессий</a>
        </div>
    </div>
    <div class="card-footer text-muted">Copyright (c) 2025 ver. 1.0.1</div>
</div>

</body>
</html>
