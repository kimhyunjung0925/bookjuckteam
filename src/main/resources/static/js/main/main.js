var menuDivElem = document.getElementsByClassName('menuDiv');

menuDivElem[0].classList.add("clicked");

function menuClick(event) {
    console.log(event.target);
    console.log(event.target.classList);

    if (event.target.classList[1] === "clicked") {
        event.target.classList.remove("clicked");
    } else {
        for (var i = 0; i < menuDivElem.length; i++) {
            menuDivElem[i].classList.remove("clicked");
        }
        event.target.classList.add("clicked");
    }
}

function init() {
    for (var i = 0; i < menuDivElem.length; i++) {
        menuDivElem[i].addEventListener("click", menuClick);
    }
}

init();