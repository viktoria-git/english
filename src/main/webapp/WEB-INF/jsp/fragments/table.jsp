<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table class="table">
    <thead class="thead-dark">
    <tr class="table-success">
        <th>Word
            <form method="get" style="margin: 0;" action="/sort">
                <input type="hidden" name="sort" value="word">
                <jsp:include page="./buttonWithIconSort.jsp"/>
            </form>
        </th>
        <th>Translate
            <form method="get" style="margin: 0;" action="/sort">
                <input type="hidden" name="sort" value="translate">
                <jsp:include page="./buttonWithIconSort.jsp"/>
            </form>
        </th>
        <th>Delete
            <form method="get" style="margin: 0;" action="/removeAll">
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
            <td>
                <form class="table-form" method="get" action="/remove">
                    <input type="hidden" name="id" value="${word.id}"/>
                    <button type="submit" style="background: transparent;border: none;">
                        <i class="icon-remove"></i></button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>