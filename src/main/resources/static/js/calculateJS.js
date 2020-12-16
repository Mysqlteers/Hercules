function preventFormDefault(form) {
    form.submit(function(event) {
        event.preventDefault();
        calculateTotal($("#jobtype").val(), $("#material").val(), $("#cubicarea").val());
    })
}

function calculateTotal(jobtype, material, cubicarea) {
    let calculate = {};
    calculate["jobtype"] = jobtype;
    calculate["material"] = material;
    calculate["cubicarea"] = cubicarea;
    $.ajax({
        url: "/api/calculateTotal",
        type: "POST",
        contentType: "application/JSON",
        data: JSON.stringify(calculate),
        success: function(data) {
            $("#priceDiv").html(data)
        },
        error: function() {
            console.log("Error in AJAX request.")
        }
    })
}