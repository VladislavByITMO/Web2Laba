let r;

// перед отправкой формы, её нужно вставить в документ


document.getElementById("R_field")
    .addEventListener('input', e => {
        check_R();
        r = inputR.value;
    })
document.getElementById("graphic").onclick = function(event) {
    const rect = document.getElementById("graphic").getBoundingClientRect();
    var cordX;
    var cordY;

    if ((r>=2&&r<=5)) {

        const x = ((event.clientX - rect.left -125)/(18*5)*r);
        const y = (((- event.clientY) + rect.bottom -125)/(18*5)*r);
        let xt = (event.clientX - rect.left);
        let yt = (( event.clientY) - rect.top );

    //    console.log(rect.left +"  "+rect.top + " "+ rect.bottom)
        changePoint(xt,yt)
        let inputY = document.getElementById("Y_field");
        inputY.value = y.toString() ;
        let inputx = document.getElementById("X_field");
        inputx.value = x.toString() ;
       $("#submit2").click()


    } else {
      alert("I can't check your point \n" +
          "Please check R ")
    }


}


function changePoint(x,y) {
    let point = $("#point");
    point.attr({
        cx: x,
        cy: y,
        visibility: "visible"
    });
}