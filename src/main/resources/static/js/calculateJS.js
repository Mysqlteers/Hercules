function preventFormDefault(form) {
    form.submit(function(event) {
        event.preventDefault();
        calculateTotal($("#jobtype").val(), $("#material").val(), $("#cubicarea").val(), $("#markup").val());
    })
}

function calculateTotal(jobtype, material, cubicarea, markup) {
    let calculate = {};
    calculate["jobtype"] = jobtype;
    calculate["material"] = material;
    calculate["cubicarea"] = cubicarea;
    calculate["markup"] = markup;
    $.ajax({
        url: "/api/calculateTotal",
        type: "POST",
        contentType: "application/JSON",
        data: JSON.stringify(calculate),
        success: function(data) {
            $("#priceDiv").html("Pris med markup: " + data.priceWithMarkup + " kr." + "<br>" +
                                "Pris uden markup: " + data.priceWithoutMarkup + " kr.")
        },
        error: function() {
            console.log("Error in AJAX request.")
        }
    })
}