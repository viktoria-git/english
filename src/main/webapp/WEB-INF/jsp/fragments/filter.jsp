<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<p>Filter:</p>
<form class="form-inline" method="get" action="${pageContext.request.contextPath}/filter">
    <jsp:include page="./selectTopic.jsp"/>
    <jsp:include page="./selectLevel.jsp"/>
    <button type="submit" class="btn btn-success" id="button-type-submit-filterLevel">Filter</button>
</form>