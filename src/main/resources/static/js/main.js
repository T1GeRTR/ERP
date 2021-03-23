console.log("Method work");
var inputs = document.getElementsByClassName('input');
var sums = document.getElementsByClassName('sum');
var save = document.getElementsByClassName('div-save');
for (let i = 0; i < inputs.length; i++) {
    inputs[i].value = (inputs[i].value == Math.round(inputs[i].value)) ? Math.round(inputs[i].value) : inputs[i].value;
    inputs[i].type = "text";
    typeDay = inputs[i].parentNode.children[3];
    if (inputs[i].value == '0') {
        inputs[i].style = "background-color: " + colorType(inputs[i], typeDay) + "; " + "color: rgba(0, 0, 0, 0);";
    } else {
        inputs[i].style = "background-color: " + colorType(inputs[i], typeDay) + "; " + "color: rgba(0, 0, 0);";
    }
}

for (let i = 0; i < sums.length; i++) {
    sums[i].value = (sums[i].value == Math.round(sums[i].value)) ? Math.round(sums[i].value) : Math.round(sums[i].value * Math.pow(10, 1)) / Math.pow(10, 1);
    sums[i].type = "number";
}
for (let i = 0; i < save.length; i++) {
    typeDay = save[i].parentNode.children[3];
    save[i].style = "background-color: " + colorType(save[i], typeDay) + "; " + "border-width: 2px";
}

function ChangeColor(Element) {
    let type = document.getElementById('typeDay');
    let hours = document.getElementById('hours');
    let check = document.getElementById('check');
    let typeDay = Element.parentNode.children[3];
    let count;
    let fcolor;
    count = hours.value;
    if (count == 0) {
        fcolor = "color: rgba(0, 0, 0, 0)";
    } else {
        fcolor = "color: rgba(0, 0, 0)";
    }
    if (check.checked) {
        typeDay.value = type.value;
        Element.style = "background-color:" + colorType(Element, typeDay) + "; color: " + fcolor + ";";
        Element.value = count;
    }
}

function colorType(Element, typeDay){
    console.log(Element);
    let color;
    console.log(typeDay.value);
    if (typeDay.value == 1)
        color = "#FFF";
    else if (typeDay.value == 2)
        color = "#FF0";
    else if (typeDay.value == 3)
        color = "#8DD452";
    else if (typeDay.value == 4)
        color = "#03AE48";
    else if (typeDay.value == 5)
        color = "#BED7EB";
    else if (typeDay.value == 6)
        color = "#F9D1C5";
    else if (typeDay.value == 7)
        color = "#DEC8EE";
    else if (typeDay.value == 8)
        color = "#FFF";
    else if (typeDay.value == 9)
        color = "#D5DADE";
    else if (typeDay.value == 10)
        color = "#F565F8";
    else if (typeDay.value == 11)
        color = "#FFBF01";
    else if (typeDay.value == 12)
        color = "#BD0000";
    return color;
    Element.style = "background-color: " + color + ";";
    console.log(Element.style);
}

function updateTable() {
    var month = $("#monthInt").val();
    var year = $("#yearInt").val();
    var url = "../hours/" + month + "-" + year;
    $.get(url, function (fragment) { // get from controller
        $("#table-hours").replaceWith(fragment); // update snippet of page
    });
}