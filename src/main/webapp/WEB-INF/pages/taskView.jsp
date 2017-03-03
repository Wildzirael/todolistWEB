<%--
  Created by IntelliJ IDEA.
  User: wz
  Date: 01.03.17
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Список заданий</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style type="text/css">
        .myrow-container {
            margin: 20px;
        }
    </style>

</head>
<body class=".container-fluid">
<div class="container myrow-container">
    <div class="panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">
                <div align="center"><b>Список заданий</b></div>
            </h3>
        </div>
        <div class="panel-body">
            <div class="panel-heading">
                <h3 class="panel-title pull-left">
                    <div align="left">
                        <c:if test="${filter.equals('all')}">
                            Все
                        </c:if>
                        <c:if test="${!filter.equals('all')}">
                            <a href="getAllTasks?done=all&page=1">Все</a>
                        </c:if>

                        <c:if test="${filter.equals('done')}">
                            Выполненые
                        </c:if>
                        <c:if test="${!filter.equals('done')}">
                            <a href="getAllTasks?done=done&page=1">Выполненые</a>
                        </c:if>

                        <c:if test="${filter.equals('undone')}">
                            Невыполненые
                        </c:if>
                        <c:if test="${!filter.equals('undone')}">
                            <a href="getAllTasks?done=undone&page=1">Невыполненые</a>
                        </c:if>
                    </div>
                </h3>
                <h3 class="panel-title pull-right">
                    <div align="right">
                        <a href="/createTask">Создать</a>
                        <a href="addTestTasks">Добавить тестовые задания</a>
                    </div>
                </h3>
            </div>

            <c:if test="${empty tasksList}">
                Задания отсутствуют.
            </c:if>
            <c:if test="${not empty tasksList}">
                <table class="table table-hover table-bordered table-condensed">
                    <thead style="background-color: #428bca; color: white;">
                    <tr>
                        <th>Номер</th>
                        <th>Название</th>
                        <th>Описание</th>
                        <th>Срок выполнения</th>
                        <th>Готовность</th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="taskModel" items="${tasksList}">
                        <tr>
                            <th><c:out value="${taskModel.id}"/></th>
                            <th><c:out value="${taskModel.name}"/></th>
                            <th><c:out value="${taskModel.description}"/></th>
                            <th><fmt:formatDate value="${taskModel.schedule}" pattern="yyyy-MM-dd"/></th>
                            <th><c:if test="${taskModel.done}"><img src="/resources/done.png" width="32" height="32"/></c:if>
                                <c:if test="${not taskModel.done}"><img src="/resources/undone.png" width="32" height="32"></c:if></th>
                            <th><a href="editTask?id=<c:out value='${taskModel.id}'/>">Изменить</a> </th>
                            <th><a href="deleteTask?id=<c:out value='${taskModel.id}'/>">Удалить</a> </th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div align="left">
                    <c:forEach var="pageNum" items="${pageList}">
                        <c:if test="${pageNum == curPage}">
                            ${pageNum}
                        </c:if>
                        <c:if test="${pageNum != curPage}">
                            <a href="getAllTasks?done=${filter}&page=${pageNum}">${pageNum}</a>
                        </c:if>
                    </c:forEach>
                </div>
            </c:if>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>
