# Hướng dẫn sử dụng tính năng Check-in

## Tổng quan
Tính năng check-in cho phép người dùng yêu cầu check-in vé máy bay sau khi thanh toán. Manager sẽ xác nhận yêu cầu và user sẽ nhận được vé máy bay với đầy đủ thông tin.

## Quy trình

### 1. User thanh toán vé
- Sau khi thanh toán thành công, đơn hàng chuyển sang trạng thái **"Đã thanh toán"** (PAID)
- Trong trang chi tiết đơn hàng, user sẽ thấy button **"YÊU CẦU CHECK-IN"**

### 2. User gửi yêu cầu check-in
- Click button **"YÊU CẦU CHECK-IN"**
- Trạng thái đơn hàng chuyển sang **"Chờ xác nhận"** (CHECK_IN_PENDING)
- Hệ thống tạo một yêu cầu check-in và gửi cho Manager

### 3. Manager xem và xử lý yêu cầu
- Manager vào menu **"Yêu cầu Check-in"** trong sidebar
- Xem danh sách các yêu cầu đang chờ xử lý
- Thông tin hiển thị:
  - Mã đơn hàng
  - Thông tin khách hàng (tên, email, SĐT)
  - Chi tiết chuyến bay (điểm đi, điểm đến, giờ bay, mã chuyến bay, hạng ghế)
  
### 4. Manager xác nhận
**Nếu Xác nhận (Approve):**
- Trạng thái đơn hàng → **"Đã xác nhận"** (CONFIRMED)
- Trạng thái vé → **"Đã check-in"** (CHECKED_IN)
- User có thể xem vé máy bay với đầy đủ thông tin:
  - Nơi đi, nơi đến
  - Thời gian đi, thời gian đến
  - Mã chuyến bay
  - Hạng ghế
  - Hãng máy bay (Vietnam Airlines)

**Nếu Từ chối (Reject):**
- Trạng thái đơn hàng quay về **"Đã thanh toán"** (PAID)
- User có thể gửi lại yêu cầu check-in

## Database Changes

### Bảng mới: check_in_requests
```sql
- request_id: ID yêu cầu (Primary Key)
- order_id: ID đơn hàng
- user_id: ID người dùng
- status: Trạng thái (PENDING, APPROVED, REJECTED)
- created_at: Thời gian tạo
- processed_at: Thời gian xử lý
- processed_by: ID Manager xử lý
- notes: Ghi chú (nếu từ chối)
```

### Enum mới: OrderStatus.CHECK_IN_PENDING
Thêm trạng thái mới cho đơn hàng khi đang chờ xác nhận check-in.

## Files đã thay đổi

### Backend (Java)
1. **OrderStatus.java** - Thêm trạng thái CHECK_IN_PENDING
2. **CheckInRequest.java** - Entity mới cho yêu cầu check-in
3. **CheckInRequestRepository.java** - Repository mới
4. **Order.java** - Thêm quan hệ với Ticket
5. **UserController.java** - Thêm endpoint gửi yêu cầu check-in
6. **ManagerController.java** - Thêm endpoint xem và xử lý yêu cầu

### Frontend (HTML)
1. **orders.html** - Thêm button Check-in
2. **order-detail.html** - Thêm button và hiển thị trạng thái mới
3. **check-in-requests.html** - Trang mới cho Manager
4. **dashboard.html** - Thêm link menu Check-in
5. **flights.html, airports.html, flight-form.html, airport-form.html, flight-detail.html** - Cập nhật menu sidebar

### SQL Scripts
1. **create_check_in_requests_table.sql** - Script tạo bảng mới

## Cài đặt

### 1. Chạy SQL script để tạo bảng mới
```sql
-- Chạy file: src/main/resources/sql/create_check_in_requests_table.sql
```

### 2. Build và chạy ứng dụng
```bash
mvn clean install
mvn spring-boot:run
```

## Kiểm tra

### Test flow hoàn chỉnh:
1. Đăng nhập với tài khoản User
2. Tìm kiếm và đặt vé máy bay
3. Thanh toán vé → Xem trạng thái "Đã thanh toán"
4. Click button "YÊU CẦU CHECK-IN" → Trạng thái chuyển sang "Chờ xác nhận"
5. Đăng nhập với tài khoản Manager
6. Vào menu "Yêu cầu Check-in"
7. Xác nhận yêu cầu
8. Quay lại tài khoản User
9. Xem chi tiết đơn hàng → Trạng thái "Đã xác nhận"
10. Xem thông tin vé máy bay đầy đủ

## Endpoints mới

### User
- `POST /user/check-in/request` - Gửi yêu cầu check-in
  - Params: orderId

### Manager
- `GET /manager/check-in-requests` - Xem danh sách yêu cầu
- `POST /manager/check-in-requests/approve/{id}` - Xác nhận check-in
- `POST /manager/check-in-requests/reject/{id}` - Từ chối check-in
  - Params: notes (optional)

## Lưu ý
- User chỉ có thể gửi yêu cầu check-in khi đơn hàng đã thanh toán (PAID)
- Không thể gửi nhiều yêu cầu check-in cho cùng một đơn hàng
- Manager cần đăng nhập để xử lý yêu cầu
- Sau khi xác nhận, user có thể xem vé máy bay đầy đủ thông tin
