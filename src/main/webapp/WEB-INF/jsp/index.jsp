<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>English</title>

    <link href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="css/index.css">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<body>
<h2></h2>

<form class="form-inline" method="get" action="/find">
    <div class="form-group mx-sm-3 mb-2">
        <label class="sr-only">Word</label>
        <input type="text" class="form-control" name="searchedWord" placeholder="Word">
    </div>
    <button type="submit" class="btn btn-success">Find</button>
</form>

<form class="form-inline" method="post" action="/add">
    <div class="form-group mx-sm-3 mb-2">
        <label class="sr-only">Word</label>
        <input type="text" class="form-control" name="word" placeholder="Word" value="${param.word}">
    </div>
    <div class="form-group mx-sm-3 mb-2">
        <label class="sr-only">Word</label>
        <input type="text" class="form-control" name="translate" placeholder="Translate" value="${param.translate}">
    </div>
    <button type="submit" class="btn btn-danger">Add</button>

</form>

<div class="container">
    <table class="table">
        <thead>
        <tr class="table-success">
            <th>Word</th>
            <th>Translate</th>
            <th>Delete</th>
        </tr>
        </thead>
        <c:forEach items="${words}" var="word">
            <jsp:useBean id="word" type="com.english.entity.WordTo"/>
            <tr word-color="${word.color}" values="${word.allocated}" >
                <td>${word.word}</td>
                <td>${word.translate}</td>
                <td>
                    <form class="table-form" method="get" action="/remove">
                        <input type="hidden" name="id" value="${word.id}"/>
                        <button type="submit" id="delete-CSS"><i class="icon-remove"></i></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
