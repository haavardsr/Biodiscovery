<jsp:include page="header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h1>Biodiscovery</h1>
<h4>Styled file browse button using Bootstrap v5.0 and Alpine.js</h4>
<div class="container">
    <div class="form-group" x-data="{ fileName: '' }">
        <div class="input-group shadow">
            <span class="input-group-text px-3 text-muted"><i class="fas fa-image fa-lg"></i></span>
            <input type="file" x-ref="file" @change="fileName = $refs.file.files[0].name" name="img[]" class="d-none">
            <input type="text" class="form-control form-control-lg" placeholder="Upload Image" x-model="fileName">
            <button class="browse btn btn-primary px-4" type="button" x-on:click.prevent="$refs.file.click()"><i class="fas fa-image"></i> Browse</button>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>