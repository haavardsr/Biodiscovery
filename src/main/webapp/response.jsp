<jsp:include page="nav.jsp"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Upload File Response</title>
</head>
<body>
<%-- Using JSP EL to get message attribute value from request scope --%>
<h2>${requestScope.message}</h2>
<h3 class="mt-3">Upload more files? <a href="fileUpload">Click here</a></h3>
</body>
</html>