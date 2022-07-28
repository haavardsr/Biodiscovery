<jsp:include page="header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>



<link rel="stylesheet" href="css/forgotpassword.css">


<div class="container-fluid m-0 p-0">
    <div  class="row d-flex justify-content-center align-items-center p-0 m-0 main-section">
        <img class="image-cover" src="media/Biodiscovery/tang.jpg" alt="">
        <div id="picture_form" >



            <p id="text_for_logo">Reset password</p>
            <form autocomplete="off" action="/forgotpassword" method = "post">
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
                    <label for="exampleInputEmail1">E - mail adress:</label>

                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" name="email" required>
                    <small>Enter your email address to receive an email with instructions.</small>

                </div>
                <button class="btn btn-primary" type="submit">
                    Send reset email
                </button>
            </form>
    </div>
</div>

<jsp:include page="footer.jsp"/>