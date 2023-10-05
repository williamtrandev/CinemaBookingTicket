$(function() {
    $("#movieSubmitBtn").click(function () {
//        let dynamicPath = /*[[@{/api/v1/movie}]]*/ '';
        let formBody = new FormData($("#movieForm")[0]);
        const id = $("#updateMovieBtn").data("id");
        fetch(`/cinema/api/v1/movie/update/${id}`, {
            method: "PUT",
            body: formBody,
        })
        .then(res => res.json())
        .then(data => {
            $(".card-movie").empty().append(renderImage(data));
            $(".detail-movie").empty().append(renderDetail(data));
            
            $("#movieModal").modal("hide");
        })
        .catch(err => console.log(err));
    })
})