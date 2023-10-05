$(function() {
    $("#movieSubmitBtn").click(()=>{
//        let dynamicPath = /*[[@{/api/v1/movie}]]*/ '';
        let formBody = new FormData($("#movieForm")[0]);
        fetch(`/cinema/api/v1/movie/save`, {
            method: "POST",
            body: formBody,
        })
        .then(res => res.json())
        .then(data => {
            $(".row-phim").append(renderCard(data));
            $("#movieForm")[0].reset();
            $("#movieModal").modal("hide");
        })
        .catch(err => console.log(err));
    });

    
})