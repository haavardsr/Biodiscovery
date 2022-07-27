<jsp:include page="header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>



<link rel="stylesheet" href="css/login.css">


<div class="container-fluid m-0 p-0">
    <div  class="row d-flex justify-content-center align-items-center p-0 m-0 main-section">
        <img class="image-cover" src="media/Biodiscovery/tanggronn.jpg" alt="">

        <div id="picture_form" >
            <img src="" class="" alt="">
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
                    <h6 class="mt-3">Forgot your password? <a href="forgotpassword">Click here</a></h6>
                    <h6 class="mt-3">Create a new user? <a href="createUser">Click here</a></h6>

                </div>
                <button class="btn btn-primary" type="submit">
                    Login
                </button>
            </form>
        </div>
    </div>
<jsp:include page="footer.jsp"/>