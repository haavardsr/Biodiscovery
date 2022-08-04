<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>



<link rel="stylesheet" href="css/login.css">


<div class="container-fluid m-0 p-0">
    <div  class="row d-flex justify-content-center align-items-center p-0 m-0 main-section">
        <img class="image-cover" src="media/Biodiscovery/blae.jpg" alt="">

        <div id="picture_form" >
            <img src="media/Biodiscovery/logo.png" class="img-fluid login-image" alt="logo-picture">
            <p id="text_for_logo">Reset password</p>
            <form autocomplete="off" action="/resetpassword" method = "post">
                <input type="hidden" name="csrf" value="<% out.print(session.getAttribute("csrf")); %>">

                <%
                    if(session.getAttribute("error") != null){
                        session.removeAttribute("success");
                        out.println("<div class='alert alert-danger' role='alert'>" + session.getAttribute("error") + "</div>");
                        session.removeAttribute("error");
                    }
                    if(session.getAttribute("success") != null){
                        session.removeAttribute("error");
                        out.println("<div class='alert alert-success' role='alert'>" + session.getAttribute("success") + "</div>");
                        session.removeAttribute("success");
                    }
                %>
                <div class="form-group p-1">

                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter your email" name="email" required>

                </div>
                <div class="form-group p-1">
                    <!-- <label for="exampleInputPassword1">Password address</label> -->

                    <input type="password" class="form-control" id="exampleInputPassword1" aria-describedby="passwordHelp" placeholder="Enter password" name="password1" required>

                </div>
                <div class="form-group p-1">
                    <!-- <label for="exampleInputPassword1">Password address</label> -->

                    <input type="password" class="form-control" id="password2" aria-describedby="passwordHelp" placeholder="Gjenta passordet" name="password2" required>

                </div>
                <button class="btn btn-primary" type="submit">
                    Reset
                </button>
            </form>
        </div>

    </div>
    <div class="container-fluid m-0 p-0">
        <div class="footer w-100 m-0">

<jsp:include page="/footer.jsp"/>