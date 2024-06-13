// SHOWING FORM FOR ADDING REVIEW

const buttonReviews = document.querySelector(".content__button_review");
const formReviews = document.querySelector(".reviews__form");

let isOpen = false;

function toggleBlockAddReview() {
    if (!isOpen) {
        formReviews.style.height = "auto";
        formReviews.style.opacity = "1";
        isOpen = true;
    } else {
        formReviews.style.opacity = "0";
        formReviews.style.height = "0";
        isOpen = false;
    }
}

if (buttonReviews) buttonReviews.addEventListener("click", toggleBlockAddReview);

// SET RATING FOR MOVIE

const stars = document.querySelectorAll(".rating__star");
const output = document.querySelector("#input-rating");

function updateRating(n) {
    stars.forEach(star => star.classList.remove('active'));

    for (let i = 0; i < n; i += 1) {
        stars[i].classList.add('active');
    }

    output.value = n;
}