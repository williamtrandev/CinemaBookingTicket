$(function() {
// Lấy danh sách các ngày ban đầu
//        var daysAvailable = document.querySelector(".days-available");
//
//        // Lấy ngày và thứ hiện tại
//        var currentDate = new Date();
//        var currentDay = currentDate.getDate();
//        var currentMonth = currentDate.getMonth() + 1; // Tháng bắt đầu từ 0
//        var currentYear = currentDate.getFullYear();
//        var currentWeekday = currentDate.getDay(); // 0 là Chủ Nhật, 1 là Thứ 2, ...
//
//        // Tạo 6 ngày kế tiếp
//        for (var i = 0; i <= 5; i++) {
//            // Tính toán ngày tiếp theo
//            var nextDate = new Date(currentYear, currentMonth - 1, currentDay + i);
//            var nextDay = nextDate.getDate();
//            var nextMonth = nextDate.getMonth() + 1;
//            var nextYear = nextDate.getFullYear();
//            var nextWeekday = nextDate.getDay();
//
//            // Tạo div ngày mới
//            var newDay = document.createElement("div");
//            if(i == 0) newDay.className="days active";
//            else newDay.className = "days";
//            newDay.setAttribute("data-date", nextYear + "-" + nextMonth + "-" + nextDay);
//            var dateSpan = document.createElement("span");
//            dateSpan.textContent = nextDay + "/" + nextMonth;
//            dateSpan.id = "day" + (i + 1);
//            var weekdaySpan = document.createElement("span");
//            var weekdays = ["CN", "Th 2", "Th 3", "Th 4", "Th 5", "Th 6", "Th 7"];
//            weekdaySpan.textContent = weekdays[nextWeekday];
//            weekdaySpan.id = "weekday" + (i + 1);
//
//            newDay.appendChild(dateSpan);
//            newDay.appendChild(weekdaySpan);
//            daysAvailable.appendChild(newDay);
//        }

})