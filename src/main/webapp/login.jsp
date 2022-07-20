<jsp:include page="header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>



<link rel="stylesheet" href="css/login.css">


<div class="container-fluid m-0 p-0">
    <div  class="row d-flex justify-content-center align-items-center p-0 m-0 main-section">
        <img class="image-cover" src="media/Biodiscovery/pd19batch37-053-gloy.jpg" alt="">

        <div id="picture_form" >
            <img src="media/Biodiscovery/logo_size-1.jpg" class="img-fluid login-image" alt="logo-picture">
            <p id="text_for_logo">Log in</p>
            <form autocomplete="off" action="login" method = "post">
                <input type="hidden" name="csrf" value="<% out.print(session.getAttribute("csrf")); %>">

                <%
                    if(session.getAttribute("error") != null){
                        out.println("<div class='alert alert-danger' role='alert'>" + session.getAttribute("error") + "</div>");
                        session.removeAttribute("error");
                    }
                %>
                <div class="form-group p-1">
                    <!--  <label for="exampleInputEmail1">Email address</label> -->

                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" name="email" required>

                </div>
                <div class="form-group p-1">
                    <!-- <label for="exampleInputPassword1">Password address</label> -->

                    <input type="password" class="form-control" id="exampleInputPassword1" aria-describedby="passwordHelp" placeholder="Enter password" name="password" required>
                    <h6 class="mt-3">Glemt ditt passord? <a href="forgotpassword">Trykk her</a></h6>
                    <h6 class="mt-3">Lag ny bruker? <a href="createUser">Trykk her</a></h6>

                </div>
                <button class="btn btn-primary" type="submit">
                    Login
                </button>
            </form>
        </div>
        <div class="container-fluid m-0 p-0">
            <div class="footer w-100 m-0">
<jsp:include page="footer.jsp"/>