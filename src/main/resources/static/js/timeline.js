/**
 * This script handles the generation of the timeline.
 * gets the list elements representing days, and the ones representing tasks.
 *
  * @param e
 */

function createChart(e) {
    let days = document.querySelectorAll(".chart-values li");
    let tasks = document.querySelectorAll(".chart-bars li");
    let dayElements = [...days]
    let daysArray = new Array()

    days.forEach(dayElement => {
        daysArray.push(dayElement.dataset.period)
    })

    tasks.forEach(el => {
    let duration = el.dataset.duration.split("/");
    let startDay = duration[0];
    let endDay = duration[1];
    let left = 0;
    width = 0;
    let skip = false;

    if (!daysArray.includes(startDay) || !daysArray.includes(endDay)) {
        //task is before
        if(startDay<daysArray[0] && endDay<daysArray[0]) {
            //set width 0;
            left=0;
            width=0;
            skip=true;
        }
        //task is after
        else if(startDay>daysArray[daysArray.length-1] && endDay>daysArray[daysArray.length-1]) {
            skip=true;
        }
        //endday is inclued, but not startday
        else if (daysArray.includes(endDay) && !daysArray.includes(startDay)) {
            startDay = daysArray[0];
        }
        //startday is included but not endday
        else if (daysArray.includes(startDay) && !daysArray.includes(endDay)) {
            endDay = daysArray[daysArray.length - 1]
        }
        else { // if task goes beyond both start and end
            startDay = daysArray[0];
            endDay = daysArray[daysArray.length - 1]
        }
    }

        if (!skip) {
            let filteredArray = dayElements.filter(day => day.dataset.period === startDay);
            if (filteredArray[0] != undefined) {
                left = filteredArray[0].offsetLeft;
            }
            //find element where day == startday
            filteredArray = dayElements.filter(day => day.dataset.period === endDay);
            if (filteredArray[0] != undefined) {
                width = filteredArray[0].offsetLeft + filteredArray[0].offsetWidth - left;
            }
        }
    // apply css
    el.style.left = `${left}px`;
    el.style.width = `${width}px`;
    el.style.backgroundColor = el.dataset.color;
    el.style.opacity = '1';


        let child =  document.createElement("a");
        child.setAttribute('class', "u-btn u-button-style u-dialog-link u-btn-1")
        child.setAttribute('href', el.dataset.href)
        width-=30;
        child.style.left = `${left}px`;
        child.style.width = `${width}px`;
        child.style.display = "none";
        if (el.childElementCount<=0) {
            el.appendChild(child)
            el.onclick = function() {
                child.click()}

        }

});
}

function changeMonth(n)
{
    let days = document.querySelectorAll(".chart-values li");
    let daysList = days[0].parentNode;
    let startDate = days[0].dataset.period;
    console.log('number = ' + n)
    console.log('startDate = ' + startDate)
    $.ajax({
        url: "/api/changeMonth",
        type: "POST",
        contentType: "application/JSON",
        data: JSON.stringify({
            number : n,
            startDate : startDate
        }),
        success: function(result) {

            days.forEach( e => {
                e.remove();
            })

            result.forEach(e =>{
                console.log(e)
                let child = document.createElement("LI");
                child.innerHTML = e.toString();
                child.setAttribute('data-period', e.toString());
                    // <li th:attr="data-period=${day}" th:each="day : ${dates}" th:text="${day.getDayOfWeek().name()}+'  '+${day.getDayOfMonth()}"></li>
                daysList.appendChild(child);
            })
            //



            console.log("success")
        },
        error: function() {
            console.log("Error in AJAX request.")
        }
    })
}

window.addEventListener("mousemove", e => {createChart();})
window.addEventListener("load", createChart);
window.addEventListener("resize", createChart);
