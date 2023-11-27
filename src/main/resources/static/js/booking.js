$(function() {
    const bookingInfo = JSON.parse(sessionStorage.getItem("bookingInfo"));
    $("#name-movie").text(`${bookingInfo.name}`);
    $("#type-ticket").text(`Loại vé: ${bookingInfo.ticketType}`);
    $("#time-show").text(`Giờ chiếu: ${bookingInfo.timeShow}`);
    const parts = bookingInfo.showDate.split("-");
    const date = parts[2] + "-" + parts[1] + "-" + parts[0];
    $("#date-show").text(`Ngày chiếu: ${date}`);
    // Danh sách các ghế đã chọn
    const seatList = JSON.parse(sessionStorage.getItem("gheDaChon"));
    // Chuyển sang dạng A1, B1...
    const outputArray = seatList.map(item => `${item.row}${item.pos}`);
    const stringSeat = outputArray.join(', ');
    $(".ghe").text(stringSeat);
    const price = bookingInfo.price;
    const formatter = new Intl.NumberFormat('en-US');
    const formattedPrice = formatter.format(price);
    $(".price-ticket").text(formattedPrice);
})