<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="form-group mx-sm-3 mb-2">
    <label class="sr-only">Topic</label>
    <select name="topic" style="height: 38px;width: 210px;" class="custom-select custom-select-sm">
        <option selected value="0">Choose topic</option>
        <c:forEach items="${topics}" var="topic">
            <jsp:useBean id="topic" type="com.english.entity.Topic"/>
            <option value="${topic.topicName}">${topic.topicName}</option>
        </c:forEach>
    </select>
</div>
