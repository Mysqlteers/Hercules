/**
 * This script handles the generation of the timeline.
 * gets the list elements representing days, and the ones representing tasks.
 *
  * @param e
 */

function createChart(e) {
    const days = document.querySelectorAll(".chart-values li");
    const tasks = document.querySelectorAll(".chart-bars li");
    const dayElements = [...days]
    let daysArray = new Array()

    days.forEach(dayElement => {
        daysArray.push(dayElement.dataset.period)
    })

    tasks.forEach(el => {
    const duration = el.dataset.duration.split("/");
    let startDay = duration[0];
    let endDay = duration[1];
    let left = 0,
    width = 0;
        console.log("children  " +el.childElementCount)


    if (!daysArray.includes(startDay) || !daysArray.includes(endDay)) {

        //task is before
        if(startDay<daysArray[0] && endDay<daysArray[0]) {
            return;
        }
        //task is after
        else if(startDay>daysArray[daysArray.length-1] && endDay>daysArray[daysArray.length-1]) {
            return;
        }
        //endday is inclued, but not startday
        else if (daysArray.includes(endDay) && !daysArray.includes(startDay)) {
            startDay = daysArray[0];
        }
        //startday is included but not endday
        else if (daysArray.includes(startDay) && !daysArray.includes(endDay)) {
            endDay = daysArray[daysArray.length - 1]
        }



    }

    //find element where day == startday
    let filteredArray = dayElements.filter(day => day.dataset.period === startDay);
    left = filteredArray[0].offsetLeft;

    //find element where day == startday
    filteredArray = dayElements.filter(day => day.dataset.period === endDay);
    width = filteredArray[0].offsetLeft + filteredArray[0].offsetWidth - left;


    // apply css
    el.style.left = `${left}px`;
    el.style.width = `${width}px`;
    if (e.type === "load") {
    el.style.backgroundColor = el.dataset.color;
    el.style.opacity = 1;




    //add a tag with class="u-btn u-button-style u-dialog-link u-btn-1"
        // href="# TaskId()"
        let child =  document.createElement("a");
        child.setAttribute('class', "u-btn u-button-style u-dialog-link u-btn-1")
        child.setAttribute('href', el.dataset.href)
        width-=40;
        child.style.left = `${left}px`;
        child.style.width = `${width}px`;
        el.appendChild(child)


    }
});
}

    window.addEventListener("load", createChart);
    window.addEventListener("resize", createChart);
