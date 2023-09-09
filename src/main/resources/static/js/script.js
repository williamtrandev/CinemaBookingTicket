const body = $("body"),
    sidebar = $(".my-sidebar"),
    toggle = $(".toggle"),
    searchBox = $(".search-box"),
    modeSwitch = $(".toggle-switch"),
    modeText = $(".mode-text");
$(window).scroll(() => {
    if (!sidebar.hasClass("close")) sidebar.toggleClass("close");
});
toggle.click(() => sidebar.toggleClass("close"));
modeSwitch.click(() => {
    const theme = body.hasClass("dark") ? "light" : "dark";
    localStorage.setItem("theme", theme);
    body.toggleClass("dark");
    if (body.hasClass("dark")) {
        modeText.text("Light Mode");
    } else {
        modeText.text("Dark Mode");
    }
});

$("input").attr("spellcheck", false);
const trig = $(".my-nav-bottom-link").toArray(); // Lay tat ca the nav
let lastClickElement = trig[0]; // Biến lưu lại phần tử vừa click, mặc định là trang home. Tối ưu thay vì dùng loop để duyệt và tìm
let preClickElement = trig[0]; // Biến lưu lại phần tử vừa click, mặc định là trang home. Tối ưu thay vì dùng loop để duyệt và tìm
// Gan su kien click cho cac the
trig.forEach((element) => {
    $(element).on("click", () => {
        // Xử lý trường hợp khi đang ở setting nhưng click vào setting một lần nữa thì mặc định trả lại trạng thái active ở trang hiện tại
        if (
            element === lastClickElement &&
            $(element).hasClass("has-sub-menu")
        ) {
            $(preClickElement).addClass("active");
            $(element).toggleClass("active");
            lastClickElement = preClickElement;
            return;
        }
        $(lastClickElement).removeClass("active"); // Xóa trạng thái active của thẻ đã click trước đó
        console.log("last", $(lastClickElement));
        if ($(element).hasClass("has-sub-menu")) {
            $(element).addClass("active");
        } else {
            if (!$(element).hasClass("active")) {
                $(element).addClass("active");
            }
        }
        preClickElement = lastClickElement;
        lastClickElement = element; // Ghi lại thẻ vừa click để xóa
    });
});


//--------------------------------------//
// Xử lý active loại phim
let typeFilm = $(".button-type-film-showing").toArray();
let typeClick = $("#all-film-showing"); // Mặc định ban đầu là tất cả film
typeFilm.forEach((element) => {
    $(element).click(() => {
        typeClick.removeClass("active"); // Xóa trạng thái active
        $(element).addClass("active"); // Thêm trạng thái active
        typeClick = $(element); // Lưu lại button vừa click
    });
});

let typeFilmComing = $(".button-type-film-coming").toArray();
let typeClickComing = $("#all-film-coming"); // Mặc định ban đầu là tất cả film
typeFilmComing.forEach((element) => {
    $(element).click(() => {
        typeClickComing.removeClass("active"); // Xóa trạng thái active
        $(element).addClass("active"); // Thêm trạng thái active
        typeClickComing = $(element); // Lưu lại button vừa click
    });
});


let prevBtn = $("#pre-btn-slider");
let nextBtn = $("#next-btn-slider");
let images = $(".images");
let items = $(".images .item");
let contents = $(".content .item");
let rotate = 0;
let active = 0;
let countItem = items.length;
let rotateAdd = 360 / countItem;
function nextSlider() {
    active = active + 1 > countItem - 1 ? 0 : active + 1;
    rotate = rotate + rotateAdd;
    show();
}
function prevSlider() {
    active = active - 1 < 0 ? countItem - 1 : active - 1;
    rotate = rotate - rotateAdd;
    show();
}
function show() {
    images.css("--rotate", rotate + 'deg');
    images.css("--rotate", rotate + 'deg');
    contents.toArray().forEach((content, key) => {
        if (key == active) {
            $(content).addClass('active');
        } else {
            $(content).removeClass('active');
        }
    })
}
nextBtn.click(nextSlider);
prevBtn.click(prevSlider);
const autoNext = setInterval(nextSlider, 4000);


//--------------------------------------//
// Xử lí dark mode. //
(() => {
    const storedTheme = localStorage.getItem("theme");  // Lấy giá trị theme được lưu ở local storage
    // Nếu giá trị theme đã lưu là dark thì bật dark mode. Các trường hợp khác là light mode.
    const setTheme = function () {
        if (
            window.matchMedia("(prefers-color-scheme: dark)").matches ||
            storedTheme === "dark"
        ) {
            $("body").addClass("dark");
        } else {
            $("body").removeClass("dark");
        }
    };
    setTheme();
})();