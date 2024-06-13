// SHOW MORE MOVIES ON MAIN PAGE

const moviesItems = document.querySelectorAll('.item-movies');
const refreshButton = document.querySelector(".movies__button");

const MOVIES_PER_PAGE = 9;
let clickCounts = 1;

moviesItems.forEach((item, index) => {
    if (index >= MOVIES_PER_PAGE) {
        item.style.display = "none";
        refreshButton.style.display = "flex";
    }
});

function showMoreMovies() {
    clickCounts += 1;

    moviesItems.forEach((item, index) => {
        if (index < MOVIES_PER_PAGE * clickCounts) item.style.display = "block";
    });

    if (moviesItems[moviesItems.length - 1].style.display === 'block') refreshButton.style.display = 'none';
}

refreshButton.addEventListener("click", showMoreMovies)
