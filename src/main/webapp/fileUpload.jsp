<jsp:include page="header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>File Upload</h1>
<h4></h4>
<div class="container">
    <div class="form-group" x-data="{ fileName: '' }">
        <div class="input-group shadow">
            <span class="input-group-text px-3 text-muted"><i class="fas fa-image fa-lg"></i></span>
            <form action="/fileUpload" method="post"
                enctype="multipart/form-data">
     <label for='file'>Select a file:</label>
         <input type='file' id='file' name='file'>
            <button type="submit">submit</button>
                </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>