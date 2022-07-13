<jsp:include page="header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="d-flex justify-content-center align-items-center w-100 h-100 flex-column">
    <div>Hey from landing page</div>
    <div class="h-25 w-25 d-flex justify-content-center align-items-center ">
        <button class="btn btn-secondary m-2" onclick="redirect('/home')">Go to home page</button>
        <button class="btn btn-info m-2" onclick="redirect('/login')">Go to login page</button>
    </div>
</div>
<jsp:include page="footer.jsp"/>