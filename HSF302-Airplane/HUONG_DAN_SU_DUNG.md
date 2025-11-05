# HƯỚNG DẪN SỬ DỤNG - ỨNG DỤNG ĐẶT VÉ MÁY BAY

## 🔐 THÔNG TIN ĐĂNG NHẬP

### Admin (Quản lý tài khoản)
- **Email:** admin@gmail.com
- **Password:** admin1
- **Chức năng:** 
  - ✅ CRUD (Tạo, Xem, Sửa, Xóa) tài khoản người dùng
  - ✅ Quản lý danh sách Manager và User
  - ✅ Xem thống kê tổng quan

### Manager (Quản lý chuyến bay & ghế)
- **Email:** manager@gmail.com
- **Password:** manager1
- **Chức năng:**
  - ✅ CRUD chuyến bay
  - ✅ CRUD sân bay
  - ✅ Quản lý chỗ ngồi trên các chuyến bay
  - ✅ Xem thống kê chuyến bay

### User (Khách hàng)
- **Email:** user@gmail.com
- **Password:** user1
- **Chức năng:**
  - ✅ Tìm kiếm chuyến bay
  - ✅ Đặt vé máy bay
  - ✅ Xem danh sách đơn hàng
  - ✅ Xem chi tiết đơn hàng
  - ✅ Quản lý thông tin cá nhân

---

## 🚀 CÁCH CHẠY ỨNG DỤNG

### Bước 1: Chuẩn bị Database
1. Đảm bảo SQL Server đã được cài đặt và đang chạy
2. Tạo database mới tên `hsf302_airplane`:
   ```sql
   CREATE DATABASE hsf302_airplane;
   ```

### Bước 2: Cấu hình kết nối
Kiểm tra file `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=hsf302_airplane;encrypt=true;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=12345
```
**Lưu ý:** Thay đổi username và password nếu cần

### Bước 3: Chạy ứng dụng
Có 2 cách:

**Cách 1: Dùng Maven (Command Line)**
```bash
# Windows PowerShell
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

**Cách 2: Chạy từ IDE (IntelliJ IDEA / Eclipse)**
- Mở file `MainApplication.java`
- Click vào nút Run/Debug

### Bước 4: Truy cập ứng dụng
Mở trình duyệt và truy cập: **http://localhost:8080**

---

## 📝 LƯU Ý QUAN TRỌNG

### Nếu gặp lỗi đăng nhập:
1. **Xóa toàn bộ dữ liệu cũ trong database:**
   ```sql
   USE hsf302_airplane;
   DELETE FROM tickets;
   DELETE FROM orders;
   DELETE FROM flight_seats;
   DELETE FROM seats;
   DELETE FROM flights;
   DELETE FROM airports;
   DELETE FROM users;
   DELETE FROM roles;
   ```

2. **Khởi động lại ứng dụng** - Dữ liệu mẫu sẽ được tự động tạo lại

3. **Kiểm tra console log** để xem thông tin user đã được tạo:
   ```
   ✅ Users initialized
   📧 Admin: admin@gmail.com / admin1
   📧 Manager: manager@gmail.com / manager1
   📧 User: user@gmail.com / user1
   ```

### Nếu gặp lỗi "Access Denied" sau khi đăng nhập:
- Đảm bảo bạn đang truy cập đúng URL theo role:
  - Admin: http://localhost:8080/admin
  - Manager: http://localhost:8080/manager
  - User: http://localhost:8080/user

---

## 🎯 HƯỚNG DẪN KIỂM TRA CHỨC NĂNG

### 1️⃣ Kiểm tra chức năng ADMIN
1. Đăng nhập với: `admin@gmail.com` / `admin1`
2. Sau khi đăng nhập thành công, bạn sẽ được chuyển đến `/admin`
3. Thử các chức năng:
   - Xem danh sách người dùng
   - Thêm người dùng mới
   - Sửa thông tin người dùng
   - Xóa người dùng

### 2️⃣ Kiểm tra chức năng MANAGER
1. Đăng xuất (nếu đang đăng nhập)
2. Đăng nhập với: `manager@gmail.com` / `manager1`
3. Sau khi đăng nhập, bạn sẽ được chuyển đến `/manager`
4. Thử các chức năng:
   - Xem danh sách chuyến bay
   - Thêm chuyến bay mới
   - Sửa thông tin chuyến bay
   - Xóa chuyến bay
   - Quản lý sân bay

### 3️⃣ Kiểm tra chức năng USER
1. Đăng xuất (nếu đang đăng nhập)
2. Đăng nhập với: `user@gmail.com` / `user1`
3. Sau khi đăng nhập, bạn sẽ được chuyển đến `/user`
4. Thử các chức năng:
   - Xem dashboard cá nhân
   - Tìm kiếm chuyến bay
   - Đặt vé máy bay
   - Xem danh sách đơn hàng

---

## ⚙️ CẤU TRÚC DỮ LIỆU MẪU

### Sân bay mẫu:
- HAN - Sân bay Nội Bài (Hà Nội)
- SGN - Sân bay Tân Sơn Nhất (TP.HCM)
- DAD - Sân bay Đà Nẵng
- CXR - Sân bay Cam Ranh (Nha Trang)
- PQC - Sân bay Phú Quốc
- Và nhiều sân bay khác...

### Chuyến bay mẫu:
- HAN → SGN: 2,500,000 VNĐ
- SGN → HAN: 2,500,000 VNĐ
- HAN → DAD: 1,800,000 VNĐ
- Và nhiều chuyến bay khác...

### Ghế ngồi:
- **Business Class:** Hàng 1-5 (A, B, C, D)
- **Economy Class:** Hàng 10-25 (A, B, C, D, E, F)

---

## 🛠️ TROUBLESHOOTING

### Lỗi: "Cannot connect to database"
- Kiểm tra SQL Server đã chạy chưa
- Kiểm tra username/password trong `application.properties`
- Kiểm tra firewall có chặn port 1433 không

### Lỗi: "Port 8080 already in use"
- Đổi port trong `application.properties`:
  ```properties
  server.port=8081
  ```

### Lỗi: "User not found" hoặc "Bad credentials"
- Xóa dữ liệu cũ trong database (xem hướng dẫn ở trên)
- Khởi động lại ứng dụng để tạo lại user mới

---

## 📞 HỖ TRỢ
Nếu gặp vấn đề, hãy kiểm tra:
1. Console log của ứng dụng
2. Log của SQL Server
3. Network tab trong Developer Tools của trình duyệt

---

**Chúc bạn sử dụng ứng dụng thành công! ✈️**
