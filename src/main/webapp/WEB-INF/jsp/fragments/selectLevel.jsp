<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="form-group mx-sm-3 mb-2">
    <label class="sr-only">Level</label>
    <select name="levelId" style="height: 38px;width: 210px;" class="custom-select custom-select-sm">
        <option selected value="0">Choose level</option>
        <c:forEach items="${levels}" var="level">
            <jsp:useBean id="level" type="com.english.entity.Level"/>
            <option style="font-size: 18px;" value="${level.id}">${level.levelName}</option>
        </c:forEach>
    </select>
</div>