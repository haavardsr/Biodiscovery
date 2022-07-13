<jsp:include page="header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>


<div class="container-fluid m-0 p-0">
  <div  class="row d-flex justify-content-center align-items-center p-0 m-0 main-section">
    <img class="image-cover" src="media/Biodiscovery/challenger-voyage-scientific-art%20(1).png" alt="">

    <div id="picture_form" >
      <img src="media/Biodiscovery/logo_size-1.jpg" class="img-fluid login-image" alt="logo-picture">
      <p id="text_for_logo">Log in</p>
      <form autocomplete="off" action="login" method = "post">
        <input type="hidden" name="csrf" value="<% out.print(session.getAttribute("csrf")); %>">


<!DOCTYPE html>
<html>
<head>
  <title> Java File Upload Servlet Example </title>
</head>
<body>

<form method="post" action="fileuploadservlet" enctype="multipart/form-data">
  <input type="file" name="file" />
  <input type="submit" value="Upload" />
  <i class="fas fa-file-upload"></i>
      </form>
    <div>
    <div class="container-fluid m-0 p-0">
    </div>
</div>

      
<jsp:include page="footer.jsp"/>