let numProduct = $(".num-product");
let addBtns = $(".add-product");
let minusBtns = $(".minus-product");
let productInfo = $("#product-buying");
let num = JSON.parse(sessionStorage.getItem("gheDaChon")).length;
let price = JSON.parse(sessionStorage.getItem("bookingInfo")).price;
let total = parseInt(price) * num;
$(".price-total-ticket").text(total.toLocaleString('vi-VN') + " VND");

// Kiểm tra nếu số lượng món > 0 thì cho giảm nữa
function checkValue() {
	numProduct.toArray().forEach(element => {
		if(parseInt($(element).val()) > 0) {
			console.log($(element).siblings('.minus-product'));
			$(element).siblings('.minus-product').prop('disabled', false);
		} else {
			$(element).siblings('.minus-product').prop('disabled', true);
		}
	});
}
// Gọi hàm chạy lần đầu duyệt web, sử dụng cho trường hợp lưu giá trị vào local storage
checkValue();

if(productInfo.find("p").length == 0) {
	productInfo.append("<p class='msg-has-no'>Chưa chọn sản phẩm nào</p>");
}

function checkExist(name) {
	return productInfo.find(name).length > 0;

}


addBtns.click((event)=>{
	if(productInfo.find(".msg-has-no").length > 0) {
		$(".msg-has-no").remove();
	}


	let tds = $(event.target).closest("tr").find("td");
	let price = parseInt(tds.last().text().replaceAll(",", ""));
	let targetInput = $(event.target).siblings('.num-product');	// Tìm ô input tương ứng
	let numProduct = parseInt(targetInput.val()) + 1;	// Tăng số lượng
	targetInput.val(numProduct); 	// Gán lại giá trị cho ô input
	let minusTarget = $(event.target).siblings('.minus-product');	// Tìm nút giảm số lượng
	$(minusTarget).removeClass("disabled");	// Xóa class disabled
	total += price;
	let tdFirst = $(event.target).closest("tr").find("td")[0]; // Lấy thẻ td đầu tiên để lấy tên sản phẩm
	let nameProduct = $(tdFirst).find(".name-product").text(); // Lấy tên của sản phẩm
	let idProduct = $(tdFirst).find(".name-product").attr("id"); // Lấy id của sản phẩm
	nameProduct = nameProduct + ` (${numProduct})`;	
	// Nếu đã tồn tại thì chỉ cần đổi tên
	if(checkExist(`.${idProduct}`)) {
		$(`.${idProduct}`).text(nameProduct);
	} else {
		// Nếu không, insert vào
		let pInsert = `<p class="${idProduct}">${nameProduct}</p>`;
		productInfo.append(pInsert);
	}
	// Tạo đối tượng combo
	let combo = {
		id: parseInt(idProduct)	,
		quantity: numProduct,
		price: price
	};

	// Lấy danh sách combo từ localStorage (nếu có)
	let combos = JSON.parse(localStorage.getItem('comboDetails')) || [];

	// Kiểm tra xem combo đã tồn tại trong danh sách chưa
	let existingComboIndex = combos.findIndex(item => item.id === parseInt(idProduct));

	if (existingComboIndex !== -1) {
		// Nếu combo đã tồn tại, cập nhật thông tin số lượng và giá
		combos[existingComboIndex] = combo;
	} else {
		// Nếu combo chưa tồn tại, thêm vào danh sách
		combos.push(combo);
	}

	// Lưu danh sách combo vào localStorage
	localStorage.setItem('comboDetails', JSON.stringify(combos));
	$(".price-total-ticket").text(total.toLocaleString('vi-VN') + " VND");
})	
minusBtns.click((event)=> {
	let minusTarget = $(event.target);	// Tìm nút giảm số lượng
	let tds = $(event.target).closest("tr").find("td");
	let price = parseInt(tds.last().text().replaceAll(",", ""));
	let targetInput = minusTarget.siblings('.num-product');	// Tìm ô input tương ứng
	let numProduct = parseInt(targetInput.val());	// Lấy giá trị ô input
	let tdFirst = $(event.target).closest("tr").find("td")[0]; // Lấy thẻ td đầu tiên để lấy tên sản phẩm
	let nameProduct = $(tdFirst).find(".name-product").text(); // Lấy tên của sản phẩm
	let idProduct = $(tdFirst).find(".name-product").attr("id"); // Lấy id của sản phẩm
	if (numProduct > 0) {    // Nếu số lượng hiện tại lớn hơn 0 thì mới làm tiếp
		numProduct = parseInt(targetInput.val()) - 1;    // Giảm giá trị số lượng
		targetInput.val(numProduct);    // Gán lại giá trị
		total -= price;

		let combo = {
			id: parseInt(idProduct),
			quantity: numProduct,
			price: price
		};

		// Lấy danh sách combo từ localStorage (nếu có)
		let combos = JSON.parse(localStorage.getItem('comboDetails')) || [];

		// Tìm index của combo trong danh sách
		let existingComboIndex = combos.findIndex(item => item.id === parseInt(idProduct));

		if (existingComboIndex !== -1) {
			if (numProduct === 0) {
				// Nếu số lượng là 0, xóa combo khỏi danh sách
				combos.splice(existingComboIndex, 1);
				$(`.${idProduct}`).remove();
			} else {
				// Nếu không, cập nhật thông tin số lượng và giá
				combos[existingComboIndex] = combo;
				nameProduct = nameProduct + ` (${numProduct})`;
				$(`.${idProduct}`).text(nameProduct);
			}

			// Lưu danh sách combo vào localStorage
			localStorage.setItem('comboDetails', JSON.stringify(combos));
		}

		// Update UI và hiển thị tổng giá
		if (numProduct === 0) {
			minusTarget.addClass("disabled");
			if (productInfo.find("p").length === 0) {
				productInfo.append("<p class='msg-has-no'>Chưa chọn sản phẩm nào</p>");
			}
		}

		$(".price-total-ticket").text(total.toLocaleString('vi-VN') + " VND");
	}
	
})

let ghe = sessionStorage.getItem("gheHienThi");
$(".ghe").text(ghe);

