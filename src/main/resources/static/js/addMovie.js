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
            if (data.message) {
                Toastify({
                    text: "Xin hãy nhập đầy đủ thông tin phim",
                    className: "warning",
                    style: {
                        background: "linear-gradient(to right, #ff9900, #ffcc00)",
                    },
                    duration: 3000,
                    gravity: "top",
                    position: "right",
                }).showToast();
            } else {
                $(".row-phim").prepend(renderCard(data));
                $("#movieForm")[0].reset();
                $("#movieModal").modal("hide");
            }
        })
        .catch(err => console.log(err));
    });

    
})