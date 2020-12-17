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
        success: function(result) {
            $("#priceDiv").html("Pris med mark up: " + result.priceWithMarkup + " kr." + "<br>" +
                                "Pris uden mark up: " + result.priceWithoutMarkup + " kr.")
        },
        error: function() {
            console.log("Error in AJAX request.")
        }
    })
}