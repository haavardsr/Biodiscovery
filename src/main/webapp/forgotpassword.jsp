<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>



<link rel="stylesheet" href="css/login.css">


<div class="container-fluid m-0 p-0">
    <div  class="row d-flex justify-content-center align-items-center p-0 m-0 main-section">
        <img class="image-cover" src="https://s3.amazonaws.com/upload.uxpin/files/1159501/1120188/image-1854303b3639c8a2b889df6e9a5812b2-83640f.png" alt="">

        <div id="picture_form" >
            <img src="https://s3.amazonaws.com/upload.uxpin/files/1159501/1120188/logo-809595524b2b6d4faeb20844d7a36414.png" class="img-fluid login-image" alt="logo-picture">
            <p id="text_for_logo">Equipment rental</p>
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
                    <label for="exampleInputEmail1">E-post addresse:</label>

                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Skriv inn e-post" name="email" required>
                    <small>Skriv inn din e-post adresse for å motta e-post med instruksjoner.</small>

                </div>
                <button class="btn btn-primary" type="submit">
                    Send tilbakestille e-post
                </button>
            </form>
        </div>

    </div>
    <div class="container-fluid m-0 p-0">
        <div class="footer w-100 m-0">
            <h5 class="font-weight-bold text-center footer_text">Solid. Creative. Proud.</h5>
            <p class="font-weight-bold text-center">AMV AS</p>
            <p class="text-center">Svegeskogen 2</p>
            <p class="text-center">4400 Flekkefjord</p>
            <p class="text-center">+47 38 32 04 20</p>
        </div>
    </div>
</div>

<jsp:include page="/footer.jsp"/>