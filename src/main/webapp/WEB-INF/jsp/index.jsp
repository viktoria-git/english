<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>English</title>
</head>
<body>

<form class="form-inline" method="post" action="/add">
    <br>
    <br>
    <br>
    <div class="form-group mx-sm-3 mb-2">
        <label class="sr-only">Word</label>
        <label>
            <input type="text" class="form-control" name="word" placeholder="Word" value="${param.word}">
        </label>
    </div>
    <div class="form-group mx-sm-3 mb-2">
        <label class="sr-only">Word</label>
        <label>
            <input type="text" class="form-control" name="translate" placeholder="Translate" value="${param.translate}">
        </label>
    </div>
    <jsp:include page="./fragments/selectTopic.jsp"/>
    <jsp:include page="./fragments/selectLevel.jsp"/>

    <button type="submit" class="btn btn-danger" style="background: slategray;border: slategray">Add</button>

</form>


<div class="row">
    <div class="col-2">
        <form class="form-inline" method="get" action="${pageContext.request.contextPath}/getAllFiltered">
            <jsp:include page="./fragments/selectTopic.jsp"/>
            <button type="submit" class="btn btn-success" id="button-type-submit-filter">Filter</button>
        </form>
        <form class="form-inline" method="get" action="${pageContext.request.contextPath}/find">
            <div class="form-group mx-sm-3 mb-2">
                <label class="sr-only">Word</label>
                <label>
                    <input type="text" class="form-control" name="searchedWord" placeholder="Word">
                </label>
            </div>
            <button type="submit" class="btn btn-success" id="button-type-submit-find">Find</button>
        </form>
    </div>



    <div class="col-10">
        <table class="table">
            <thead class="thead-dark">
            <tr class="table-success">
                <th>Word
                    <form method="get" style="margin: 0;" action="${pageContext.request.contextPath}/sort">
                        <input type="hidden" name="sort" value="word">
                        <jsp:include page="./fragments/buttonWithIconSort.jsp"/>
                    </form>
                </th>
                <th>Translate
                    <form method="get" style="margin: 0;" action="${pageContext.request.contextPath}/sort">
                        <input type="hidden" name="sort" value="translate">
                        <jsp:include page="./fragments/buttonWithIconSort.jsp"/>
                    </form>
                </th>
                <th>Topic
                    <form method="get" style="margin: 0;" action="${pageContext.request.contextPath}/sort">
                        <input type="hidden" name="sort" value="topic">
                        <jsp:include page="./fragments/buttonWithIconSort.jsp"/>
                    </form>
                </th>
                <th>
                    Level
                    <form method="get" style="margin: 0;" action="${pageContext.request.contextPath}/sort">
                        <input type="hidden" name="sort" value="level">
                        <jsp:include page="./fragments/buttonWithIconSort.jsp"/>
                    </form>
                </th>
                <th>Delete
                    <form method="get" style="margin: 0;" action="${pageContext.request.contextPath}/removeAll">
                        <button type="submit" class="button-icon-style">
                            <i class="icon-remove-sign"></i>
                        </button>
                    </form>
                </th>
            </tr>
            </thead>
            <c:forEach items="${words}" var="word">
                <jsp:useBean id="word" type="com.english.entity.WordResponse"/>
                <tr word-color="${word.color}" values="${word.allocated}">
                    <td>${word.word}</td>
                    <td>${word.translate}</td>
                    <td>${word.topicName}</td>
                    <td>${word.levelName}</td>
                    <td>
                        <form class="table-form" method="get" action="${pageContext.request.contextPath}/remove">
                            <input type="hidden" name="id" value="${word.id}"/>
                            <button type="submit" style="background: transparent;border: none;">
                                <i class="icon-remove"></i></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>

<link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="css/index.css">
</html>
