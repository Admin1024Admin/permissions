<%@page language="java" contentType="text/html;charset=utf-8" isELIgnored="false" %>
<%--<jsp:forward page="${pageContext.request.contextPath}/main_index.action"/>--%>
<html>
<body>
<h2>Hello World!</h2>
<script>
    window.location.href='${pageContext.request.contextPath}/main_index.action';
</script>
</body>
</html>
