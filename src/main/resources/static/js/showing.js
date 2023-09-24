
$(function() {
    $("#search-input").on("input", function() {
        let val = $(this).val().trim();
        let data = searchMovies(val);
        $(".row-phim").empty().append(renderCard(data));
    })
})