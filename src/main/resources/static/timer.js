function timer(timeInSec) {
    var i = 0;
    var o = document.getElementById('time');
    var button = document.getElementById('sendAnswer')
    i = timeInSec;
    var t = setInterval(function () {
        i--;
        if (i <= 0) {
            clearInterval(t);
            button.click();
        }
        if (i<6){
            o.style.color="red";
        }

        o.innerHTML = "Pozostały czas: " + i + "s";

    }, 1e3);
}

