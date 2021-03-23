console.log("Method work");
var inputs = document.getElementsByClassName('input');
var sums = document.getElementsByClassName('sum');
var save = document.getElementsByClassName('div-save');
for (let i = 0; i < inputs.length; i++) {
    inputs[i].value = (inputs[i].value == Math.round(inputs[i].value)) ? Math.round(inputs[i].value) : Math.round(inputs[i].value * Math.pow(10, 1)) / Math.pow(10, 1);
    inputs[i].type = "number";
    if (inputs[i].value == 0) {
        inputs[i].style = "background-color: #BED7EB; " + "color: rgba(0, 0, 0, 0);";
    } else {
        inputs[i].style = "background-color: #FFF; " + "color: rgba(0, 0, 0);";
    }
}

for (let i = 0; i < sums.length; i++) {
    sums[i].value = (sums[i].value == Math.round(sums[i].value)) ? Math.round(sums[i].value) : Math.round(sums[i].value * Math.pow(10, 1)) / Math.pow(10, 1);
    sums[i].type = "number";
}
for (let i = 0; i < save.length; i++) {
    save[i].style = "border-width: 2px";
}

function ChangeColor(Element) {
    let type = document.getElementById('typeDay');
    let hours = document.getElementById('hours');
    let check = document.getElementById('check');
    let count;
    let color;
    let fcolor;
    if (type.value == 1)
        color = "#FFF";
    else if (type.value == 2)
        color = "#FF0";
    else if (type.value == 3)
        color = "#8DD452";
    else if (type.value == 4)
        color = "#03AE48";
    else if (type.value == 5)
        color = "#BED7EB";
    else if (type.value == 6)
        color = "#F9D1C5";
    else if (type.value == 7)
        color = "#DEC8EE";
    else if (type.value == 8)
        color = "#FFF";
    else if (type.value == 9)
        color = "#D5DADE";
    else if (type.value == 10)
        color = "#F565F8";
    else if (type.value == 11)
        color = "#FFBF01";
    else if (type.value == 12)
        color = "#BD0000";
    count = hours.value;
    if (count == 0) {
        fcolor = "color: rgba(0, 0, 0, 0)";
    } else {
        fcolor = "color: rgba(0, 0, 0)";
    }
    if (check.checked) {
        Element.style = "background-color: " + color + "; color: " + fcolor + ";";
        Element.value = count;
    }
}

function updateTable() {
    var month = $("#monthInt").val();
    var year = $("#yearInt").val();
    var url = "../hours/" + month + "-" + year;
    $.get(url, function (fragment) { // get from controller
        $("#table-hours").replaceWith(fragment); // update snippet of page
    });
}