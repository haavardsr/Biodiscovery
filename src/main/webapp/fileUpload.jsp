<jsp:include page="header.jsp"/>
<jsp:include page="nav.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="css/fileUpload.css">

<div class="container-fluid m-0 p-0">
    <div class="row d-flex justify-content-center align-items-center p-0 m-0 main-section">
        <img class="image-cover" src="media/Biodiscovery/tanggronn.jpg" alt="">

<div id="picture_form" >
<h1>File Upload</h1>
<h4></h4>
    <div class="form-group" x-data="{ fileName: '' }">
        <div class="input-group shadow">
            <span class="input-group-text px-3 text-muted"><i class="fas fa-image fa-lg"></i></span>
            <form action="/fileUpload" method="post"
                  enctype="multipart/form-data">
                <label for='file'>Select a file:</label>
                <input type='file' id='file' name='file'>
                <div id="div_id_file_name" class="form-group col-md-6 text-left">
                    <label for="file_name" class=" requiredField">
                        Sample Name, Cant use underscore (_)
                        <span class="asteriskField">*</span>
                    </label>
                    <input type="text" name="file_name" maxlength="100" class="textInput form-control" required="" id="file_name" pattern="[^_]+">'
                    <button type="submit">submit</button>
                </div>
            </form>
            </div>
    </div>
</div>


<jsp:include page="footer.jsp"/>