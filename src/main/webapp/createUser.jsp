<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<jsp:include page="/header.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" %>

<link rel="stylesheet" href="css/createEmployee.css">

<div class="container media-no-padding">
    <div  class="row d-flex justify-content-center align-items-center p-0 m-0 main-section">
        <img class="image-cover" src="https://s3.amazonaws.com/upload.uxpin/files/1159501/1120188/image-1854303b3639c8a2b889df6e9a5812b2-83640f.png" alt="">

        <div id="create-ansatt-section" class="col main pt-5 mt-3 dynamic-section active-section">

            <div id="picture_form">
                <form id="create-ansatt-form" action="createEmployee" method="POST" >
                    <fieldset class="form-group">
                        <h3 class="border-bottom mb-4 text-center headtext">Ansatt Skjema</h3>

                        <div class="form-row">
                            <div id="div_id_first_name" class="form-group col-md-6 text-left">
                                <label for="id_first_name" class=" requiredField">
                                    First name
                                    <span class="asteriskField">*</span>
                                </label>
                                <input type="text" name="first_name" maxlength="100" class="textInput form-control" required="" id="id_first_name">
                            </div>

                            <div id="div_id_last_name" class="form-group col-md-6 text-left">
                                <label for="id_last_name" class=" requiredField">
                                    Last name
                                    <span class="asteriskField">*</span>
                                </label>
                                <input type="text" name="last_name" maxlength="100" class="textInput form-control" required="" id="id_last_name">
                            </div>

                            <div id="div_id_email" class="form-group col-md-6 text-left">
                                <label for="id_email" class=" requiredField">
                                    Email
                                    <span class="asteriskField">*</span>
                                </label>
                                <input type="email" name="email" maxlength="254" autofocus="" class="emailinput form-control" required="" id="id_email">
                            </div>

                            <div id="div_id_tlf" class="form-group col-md-6 text-left">
                                <label for="id_tlf" class=" requiredField">
                                    Tlf
                                    <span class="asteriskField">*</span>
                                </label>
                                <input type="number" name="tlf" maxlength="254" autofocus="" class="emailinput form-control" required="" id="id_tlf">

                            </div>

                            <div id="div_id_password1" class="form-group col-md-6 text-left">
                                <label for="id_password1" class=" requiredField">
                                    Password
                                    <span class="asteriskField">*</span>
                                </label>
                                <input type="password" name="password1" autocomplete="new-password" class="textInput form-control" required="" id="id_password1" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}">
                                <small id="hint_id_password1" class="form-text text-muted">
                                    <ul>
                                        <li>Your password can’t be too similar to your other personal information.</li>
                                        <li>Your password must contain at least 8 characters.</li>
                                        <li>Your password can’t be a commonly used password.</li>
                                        <li>Your password can’t be entirely numeric.</li>
                                    </ul>
                                </small>
                            </div>

                            <div id="div_id_password2" class="form-group col-md-6 text-left">
                                <label for="id_password2" class=" requiredField">
                                    Password confirmation
                                    <span class="asteriskField">*</span>
                                </label>
                                <input type="password" name="password2" autocomplete="new-password" class="textInput form-control" required="" id="id_password2" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}">
                                <small id="hint_id_password2" class="form-text text-muted">Enter the same password as before,
                                    for
                                    verification.</small>
                            </div>

                        </div>


                        <select id="types" name="types" class="selectpicker"
                                data-live-search="true" data-selected-text-format="count"
                                data-count-selected-text="({0} types chosen)" data-size="10"
                                data-actions-box="true"
                                title="Choose type of equipment the employee is allowed to rent" data-width="100%"
                                multiple="multiple">
                        </select>


                        <div class="form-group justify-content-around">
                            <div id="div_id_is_union" class="form-check">
                                <input type="checkbox" name="is_union" class="checkboxinput form-check-input" id="id_is_union">
                                <label for="id_is_union" class="form-check-label requiredField" id="union_employee">
                                    Union ansatt
                                </label>
                            </div>
                            <div id="div_id_is_superuser" class="form-check">
                                <input type="checkbox" name="is_superuser" class="checkboxinput form-check-input" id="id_is_superuser">
                                <label for="id_is_superuser" class="form-check-label requiredField" id="administrator">
                                    Administrator
                                </label>
                            </div>
                        </div>
                        <input type="hidden" name="csrf" value="<% out.print(session.getAttribute("csrf")); %>">
                        <div class="text-center">
                            <button class="btn btn-primary button" type="submit">Lagre</button>
                        </div>
                        <!----HCNv7J4Q3VDzGuF----->

                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/footer.jsp"/>
