<%@ page import="java.util.List" %>
<%@ page import="entity.Answer" %>
<%@ page import="entity.Question" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="header.html"/>
    <title>Quest</title>
</head>

<body>
<%
    Question question = (Question) request.getAttribute("question");
    List<Answer> answers = (List<Answer>) request.getAttribute("answers");
%>
<div class="row">
    <div class="col-sm-6">
        <div class="card">
            <h6 class="card-header">Вопрос №<%= question.getId()%></h6>
            <div class="card-body">
                <h5 class="card-title"><%= question.getText()%> </h5>
                <div class="list-group">
                    <% for (Answer answer : answers) { %>
                    <a class="text-decoration-none list-group-item list-group-item-action" href="/quest?id=<%= answer.getNextQuestionId()%>">
                        <%= answer.getText()%>
                    </a>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>