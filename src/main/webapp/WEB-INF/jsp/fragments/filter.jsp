<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<p>Filter:</p>
<form class="form-inline" method="get" action="${pageContext.request.contextPath}/filter">

    <div class="form-group mx-sm-3 mb-2" style="position: relative;left:-10px;">
        <jsp:include page="./selectTopic.jsp"/>
    </div>

    <div class="form-group mx-sm-3 mb-2" style="position: relative;left:-10px;">
        <jsp:include page="./selectLevel.jsp"/>
    </div>
    <button type="submit" class="btn btn-dark" style="position: relative;left: 15px;">Filter</button>
</form>