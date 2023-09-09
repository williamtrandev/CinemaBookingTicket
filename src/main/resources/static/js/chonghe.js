
// function dùng để tạo hàng ghế
function generateSeat(numRows, numSeatsEachRow = []) {
	const numCols = 18; // 14 ghế, 2 khoảng trắng và 2 chữ cái
	const seats = $(".seats");
	for (let row = 0; row < numRows; row++) {
		const element = jQuery('<div>', { class: 'row-seat mt-2' });    // Tạo thẻ div classname row
		// 0 5 12 17
		for (let col = 0; col <= 17; col++) {
			if (col == 0 || col == 17) {
				const charRow = intToChar(row); // Tên hàng ghế
				element.append(`<div class="col-seat-name" style="width: 30px; cursor: default;"><span class="text w-100 h-100 d-flex justify-content-center align-items-center">${charRow}</span></div>`);
			} else if (col == 5 || col == 12) {
				element.append("<div class='col-seat'></div>");
			} else if (col == 6 || col == 13) {
				element.append(`<div class="col-seat" id="${col - 1}"><div class="seat w-100" style="padding-bottom: 100%" choose="false"></div></div>`);    // Bỏ các khoảng trống nên ghế sau đó sẽ có giá trị col - 1
			} else {
				element.append(`<div class="col-seat" id="${col}"><div class="seat w-100" style="padding-bottom: 100%" choose="false"></div></div>`);
			}
		}
		seats.append(element);
	}
}
// function dùng để tự tạo hàng ghế theo chữ cái A, B, C...
function intToChar(num) {
	const code = 'A'.charCodeAt(0);
	return String.fromCharCode(code + num);
}
// $(function () {
// 	generateSeat(8);
// 	$(".seat").click((e) => {
// 		$(e.target).toggleClass("choosing");
// 	})
// })
