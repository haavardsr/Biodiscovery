
function redirect(to){
    window.location.href=to;
}

$(".delete-employee-btn").click(function(e){
    let id = $(this).children("input.employee-id").val();
    $("input[name='employee-id']").val(id);
    $('#delete-employee-modal').modal('show');

})

$("#user-table").on("click",".delete-employee-btn",function(e){
    let id = $(this).children("input.employee-id").val();
    $("input[name='employee-id']").val(id);
    $('#delete-employee-modal').modal('show');
})
$("#cancel-delete-employee").click(function(e){
    $('#delete-employee-modal').modal('hide');
    $("input[name='employee-id']").val("");

})


$(".report-rent-btn").click(function(e){
    let id = $(this).children("input[name='report-equipment-id']").val();
    $("input[name='equipment_id']").val(id);
    $('#report-modal').modal('show');

})


$("#cancel-report").click(function(e){
    $('#report-modal').modal('hide');
    $("input[name='equipment_id']").val("");

})




$(".delete-equipment-show").click(function(e){
    let id = $(this).siblings(".rent-inputs").children("input[name='id']").val()


    $("input[name='equipment_id']").val(id)
    $('#delete-equipment-modal').modal('show');
})


$("#rent-btn").click(function(e)
{
    $(".delete-equipment-show").each(function() {
        $(this).addClass("d-none")
    })

    $(".rent-inputs").each(function() {
        $(this).removeClass("d-none")
    })

})
$("#delete-equipment-btn").click(function(e){
    $(".rent-inputs").each(function() {
        $(this).addClass("d-none")
    })
    $(".delete-equipment-show").each(function() {
        $(this).removeClass("d-none")
    })

    createCalender()
    $('#calenderModal').modal('show');

})


$("#cancel-delete-equipment").click(function(e){
    $('#delete-equipment-modal').modal('hide');
})
$('.modal').modal({
    backdrop: 'static',
    keyboard: false,
    show: false,
})

$("#delete-equipment-form").on("submit",function(e){
    e.preventDefault();
    $('#delete-equipment-modal').modal('hide');

    let data = $(this).serialize()
    let formdata = $(this).serializeArray()
    $(".alert").remove();
    $.ajax({
        url:'deleteEquipmentType',
        type:'POST',
        data:data,
        success : function(response){

            let res;
            try {
                res= JSON.parse(response);
            }catch{
                res = response
            }
            if(res === true){
                $("#calenderModal .modal-body").parent().prepend("<div class=\"alert alert-success  w-100\">\n" +
                    "  <strong>Vellykket!</strong> Utstyret ble slettet!\n" +
                    "</div>")

                let id = formdata[0].value;
                let cal = ".calender-"+id
                $(cal).remove()
                let dateDiv = ".calender-date"+id

                $(dateDiv).remove();

                if($(".rent-form").length === 1){
                    $(".rent-form").addClass("col-12");
                }else if($(".rent-form").length === 0){
                    $(".delete-equipment-show").addClass("disabled")
                    $(".delete-equipment-show").html("<div class=\"spinner-border text-light\" role=\"status\">\n" +
                        "  <span class=\"sr-only\">Vennligst vent..</span>\n" +
                        "</div>")
                    setTimeout(function(){
                        redirect("home") },2000)
                }

            }else{
                $(".alert").remove()
                $("#calenderModal .modal-body").parent().prepend("<div class=\"alert alert-danger  w-100\">\n" +
                    "  <strong>Feil!</strong> " + response +  "\n" +
                    "</div>")
            }


            document.body.scrollTop = 0; // For Safari
            document.documentElement.scrollTop = 0; // for other browsers
            $( 'html, body, .modal' ).animate( { scrollTop: 0 }, 0 );


        },
        error: function (errorMessage) {
            $("#calenderModal .modal-body").parent().prepend("<div class=\"alert alert-danger  w-100\">\n" +
                "  <strong>Feil!</strong> En feil har oppstått! Vennligst prøv igjen senere.\n" +
                "</div>")
            document.body.scrollTop = 0; // For Safari
            document.documentElement.scrollTop = 0; // for other browsers
        }

    });

})






$(document).ready(function(){
let title = $(document).attr('title');

    let table = $('#user-table').DataTable({

        responsive: true,
        "order": [[ 2, "desc" ]],
        "language": {
            "emptyTable": "Ingen data registrert!"
        },
        "columnDefs": [
            {
                responsivePriority: 10006, targets: 1
            },
            {
                responsivePriority: 10002, targets: 2
            },
            {
                responsivePriority: 10003, targets: 3
            },
            {
                responsivePriority: 10004, targets: 4
            },
            {
                responsivePriority: 10005, targets: 5
            },

        ],
        breakpoints: [
            { name: 'desktop',  width: Infinity },
            { name: 'tablet-l', width: 1440 },
            { name: 'tablet-p', width: 860 },
            { name: 'mobile-l', width: 500 },
            { name: 'mobile-p', width: 500 }
        ],
        paging: false,
        scrollY: 500,
        dom: 'Brt',
            buttons: [
                {
                    extend: 'excelHtml5',
                    title: title,
                    text:' <i class="fas fa-file-excel mr-2"></i> Export to Excel',
                    className:'btn-excel',
                    exportOptions: {
                        columns: ':not(:last-child)'
                    }

                },
                {
                    extend: 'pdfHtml5',
                    title: title,
                    className:'btn-pdf',
                    text: '<i class="fas fa-file-pdf mr-2"></i> Export to PDF ',
                    exportOptions: {
                        columns: ':not(:last-child)'

                    },
                    customize: function(doc) {
                        doc.content[1].margin = [ 100, 0, 100, 0 ] //left, top, right, bottom
                    }

                },
            ]

        })




    $('#user-table_filter input').attr("placeholder","search")
    $('#user-table_filter input').attr("class","w-100 form-control")
    $('#user-table_filter').remove()
    $('.btn-excel').attr("class","btn btn-success w-100  w-50 mr-1 amv-font");
    $('.btn-pdf').attr("class","btn btn-danger w-100  w-50 ml-1 amv-font res-excel-btn");
    $(".dataTables_scrollHeadInner").addClass(" m-0").addClass("w-100").children("table").addClass("w-100")
    $('.dt-buttons').attr("class","w-100 d-flex p-3 export-btns").css({
        'background-color':'#161e4a',
        paddingRight:'15px',
        paddingLeft:'15px'
    })

    $("#update-ansatt-form").on("submit", function(e){
        e.preventDefault();
        let data = $(this).serialize();
        $(".alert").remove();
        $.ajax({
            url:'updateEmployee',
            type:'POST',
            data:data,
            success : function(data){
                let res;
                try {
                    res= JSON.parse(data);
                }catch{
                    res = data
                }
                $('#update-ansatt-section').find('.alert').remove();
                if(res.success){

                    $("#update-ansatt-form").parent().prepend("<div class=\"alert alert-success\">\n" +
                        "  <strong>Success!</strong> " + res.success+ "\n" +
                        "</div>")


                }else{

                    $("#update-ansatt-form").parent().prepend("<div class=\"alert alert-danger\">\n" +
                        "  <strong>Feil!</strong> " + res.error +  "\n" +
                        "</div>")
                }

                $(window).scrollTop(0);

            },
            error: function (errorMessage) {
                $("#update-ansatt-form").parent().prepend("<div class=\"alert alert-danger\">\n" +

                    "  <strong>Feil!</strong> Noe gikk galt! Vennligst prøv igjen senere ( Feil kode: 500).\n" +
                    "</div>")
            }
        });


    })

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#equip-img').attr('src', e.target.result).removeClass("d-none");
            }
            reader.readAsDataURL(input.files[0]);
        } else {
            $('#equip-img').attr('src', '');
        }
    }

    $("input[type='file']").change(function() {
        readURL(this);
    });




    $("#update-type-form").on("submit", function(e){
        e.preventDefault();

        var data = new FormData(this);

        $(".alert").remove();
        $.ajax({
            url:'updateEquipment',
            type:'POST',
            contentType: false,
            processData: false,
            data:data,
            success : function(data){
                let res;
                try {
                    res= JSON.parse(data);
                }catch{
                    res = data
                }
                if(res.success){

                    $("#update-type-form fieldset").prepend("<div class=\"alert alert-success\">\n" +
                        "  <strong>Vellykket!</strong> " + res.success+ "\n" +
                        "</div>")


                }else{

                    $("#update-type-form fieldset").prepend("<div class=\"alert alert-danger\">\n" +
                        "  <strong>Feil!</strong> " + res.error +  "\n" +
                        "</div>")
                }

                $(window).scrollTop(0);

            },
            error: function (errorMessage) {
                $("#update-type-form fieldset").prepend("<div class=\"alert alert-danger\">\n" +

                    "  <strong>Feil!</strong> Noe gikk galt! Vennligst prøv igjen senere ( Feil kode: 500).\n" +
                    "</div>")
            }
        });


    })


    $("#create-ansatt-form").on("submit", function(e){
        e.preventDefault();
        let data = $(this).serialize();
        $(".alert").remove();
        $.ajax({
            url:'createEmployee',
            type:'POST',
            data:data,
            success : function(data){
                let res;
                try {
                    res= JSON.parse(data);
                }catch{
                    res = data
                }
                $('#create-ansatt-section').find('.alert').remove();
                if(res.success){

                    $("#create-ansatt-form fieldset").parent().prepend("<div class=\"alert alert-success\">\n" +
                        "  <strong>Success!</strong> " + res.success+ "\n" +
                        "</div>")


                }else{

                    $("#create-ansatt-form fieldset").parent().prepend("<div class=\"alert alert-danger\">\n" +
                        "  <strong>Feil!</strong> " + res.error +  "\n" +
                        "</div>")
                }

                $(window).scrollTop(0);

            },
            error: function (errorMessage) {
                $("#create-ansatt-form fieldset").parent().prepend("<div class=\"alert alert-danger\">\n" +
                    "  <strong>Feil!</strong> Noe gikk galt! Vennligst prøv igjen senere ( Feil kode: 500).</div>")
            }
        });


    })
    
    $("#report-form").on("submit", function(e){
        e.preventDefault();
        let data = $(this).serialize();
        $(".alert").remove();

        $.ajax({
            url:'createReport',
            type:'POST',
            data:data,
            success : function(data){
                let res;
                try {
                    res= JSON.parse(data);
                }catch{
                    res = data
                }
                $('.add-equip-main .col-lg-10').find('.alert').remove();
                if(res.success){

                    $(".add-equip-main .col-lg-10").prepend("<div class=\"alert alert-success\">\n" +
                        "  <strong>Success!</strong> " + res.success+ "\n" +
                        "</div>")


                }else{

                    $(".add-equip-main .col-lg-10").prepend("<div class=\"alert alert-danger\">\n" +
                        "  <strong>Feil!</strong> " + res.error +  "\n" +
                        "</div>")
                }

                $(window).scrollTop(0);

            },
            error: function (errorMessage) {
                $(".add-equip-main .col-lg-10").prepend("<div class=\"alert alert-danger\">\n" +
                    "  <strong>Feil!</strong> Noe gikk galt! Vennligst prøv igjen senere ( Feil kode: 500).</div>")

            }
        });
        $('#report-modal').modal('hide');



    })

    $(".mark-returned").on("click", function(e){
        e.preventDefault();
        $(this).closest("form").submit()

    })
    $(".change-status").on("click", function(e){
        e.preventDefault();
        $(this).closest("form").submit()

    })
    $(".mark-paid").on("click", function(e){
        e.preventDefault();
        $(this).closest("form").submit()

    })
    
    $(".all-rents").click()



    $(".rent-form").on("submit", function(e){
        $(".alert").remove();
        let thisForm = this
        let child = $(this).find(".range")
        let classList = $(this).attr("class").split(" ");
        const found = classList.find(element => element.startsWith("form-"));
        let empty = false;
        


        let id = found.split("form-")[1];

        e.preventDefault();

        $(this).find('input[readonly]').each(function (i,e) {
            if(!$(e).val()){

                empty= true;
            }
        });

        if(!empty){
            let formData = $(this).serializeArray();
            pickmeup('.range').clear();

            $.ajax({
                url:'rentEquipment',
                type:'POST',
                data:formData,
                success : function(response){

                    let res;
                    try {
                        res= JSON.parse(response);
                    }catch{
                        res = response
                    }
                    if(res === true){

                        $("#calenderModal .modal-body").prepend("<div class=\"alert alert-success  w-100\">\n" +
                            "  <strong class='amv-font'>Vellykket! Utstyr ble leid! Du kan se dine kviteringer <a href='kvitteringer' class='link-primary '>her.</a></strong>\n" +

                            "</div>")
                        $(".rent-form")[0].reset();
                        let days = getDays(new Date(formData[0].value), new Date(formData[1].value))
                        for(let i =0; i<days.length; i++){

                            let that = $(child).find('.date-available:contains("' + days[i].replace(/^0+/, '') + '")').not(".pmu-not-in-month").not(".pmu-month");
                            if(that){
                                $(child).find('.date-available:contains("' + days[i].replace(/^0+/, '') + '")').filter(function() {
                                    return $(this).text() === days[i].replace(/^0+/, '');
                                }).addClass("pmu-disabled").addClass("booked");
                            }
                        }
                        $(".pmu-month").removeClass("pmu-disabled")
                        $(".pmu-month").removeClass("booked")
                        $("#calenderModal .modal-body").append("<div class=\"d-none dates date-"+ id +" \">\n" +
                            "<input type=\"hidden\" name=\"start_date_"+ id +"\" value=\""+ formData[0].value +"\">\n" +
                            "<input type=\"hidden\" name=\"end_date_"+ id +"\" value=\""+ formData[1].value +"\">\n" +
                            "</div>")
                        $(".alert-danger").remove()

                    }else{
                        $(".alert-success").remove()
                        $("#calenderModal .modal-body").parent().prepend("<div class=\"alert alert-danger  w-100\">\n" +
                            "  <strong>Feil!</strong> " + response +  "\n" +
                            "</div>")
                    }


                    document.body.scrollTop = 0; // For Safari
                    document.documentElement.scrollTop = 0; // for other browsers
                    $( 'html, body, .modal' ).animate( { scrollTop: 0 }, 0 );


                },
                error: function (errorMessage) {
                    $("#calenderModal .modal-body").parent().prepend("<div class=\"alert alert-danger  w-100\">\n" +
                        "  <strong>Feil!</strong> En feil har oppstått! Vennligst prøv senere.\n" +
                        "</div>")
                    document.body.scrollTop = 0; // For Safari
                    document.documentElement.scrollTop = 0; // for other browsers
                }

            });
        }else{
            $(thisForm).append("<div class='alert alert-danger w-100'>Du må velge start dato og slutt dato for å kunne leie.</div>")
        }
        


    })




})

/** Calender configuration */

pickmeup.defaults.locales['en'] = {
    days: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
    daysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
    daysMin: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'],
    months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
    monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
};


function getDateString(date){

    var dd = String(date.getDate()).padStart(2, '0');
    var mm = String(date.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = date.getFullYear();

    date = yyyy+ "-" + mm +"-" + dd
    return date

}

function getDaysString(date){

    return  String(date.getDate()).padStart(2, '0');


}

function getDays(start, end) {
    for(var arr=[],dt=new Date(start); dt<=end; dt.setDate(dt.getDate()+1)){

        arr.push(getDaysString(dt));
    }
    return arr;
}


function getDates(start, end) {
    for(var arr=[],dt=new Date(start); dt<=end; dt.setDate(dt.getDate()+1)){

        arr.push(getDateString(dt));
    }
    return arr;
}
function createCalender(){
    $(".alert").remove()
    let calenders = $(".range").length;
    for (let i =1;i <= calenders;i++){
        let class_name = ".range-" + i;

        pickmeup(class_name, {
            locale:"en",
            flat : true,
            format:"Y-m-d",
            mode : 'range',
            render : function (date) {
                let rentedDates = []
                $(".date-" + i).each(function (){
                    let input1 = $(this).children("input")[0]
                    let input2 = $(this).children("input")[1]
                    let start =$(input1).val()
                    let end = $(input2).val()
                    rentedDates = rentedDates.concat(getDates(new Date(start),new Date(end)))


                })

                if (rentedDates.includes(getDateString(date)) ) {
                    return {disabled : true, class_name : 'booked'};

                    }else if(date < new Date()){
                    return {disabled : true, class_name : 'date-in-past'};
                }else{
                    return {disabled : false, class_name : 'date-available'};
                }

            }


        });


    }
    let lengthForms = $(".rent-form").length;
    if(lengthForms > 1){
        $(".rent-form").addClass("col-6")
    }else{
        $(".rent-form").addClass("col-12")
    }

    $(".pmu-today").removeClass("pmu-today");
    $(".pmu-not-in-month").remove()

}



$(".range").on('pickmeup-change',function(e){
    $(".pmu-not-in-month").remove()

    $(e.target).parents("form").find("input[name='start_date']").val(e.detail.formatted_date[0])
    $(e.target).parents("form").find("input[name='end_date']").val(e.detail.formatted_date[1])
})
$(".range").on('pickmeup-fill', function (e) {
    $(".pmu-not-in-month").remove()
    $(".pmu-month").removeClass("pmu-disabled")
    $(".pmu-month").removeClass("booked")
})



/** End Calender configuration */

$(document).on('change', '#filter',function () {
    let selected= $(this).find(":selected").val();
    $("tbody tr").hide()
    console.log(selected)
    if(selected === "overdue") {

        $(".overdue-1").each(function (i, e) {


            $(e).show(0)
        })
    }else if(selected === "not-paid"){
        $(".paid-0").each(function (i, e) {
            $(e).show(0)
        })
    }else{
        $("tr").show()
    }
})
