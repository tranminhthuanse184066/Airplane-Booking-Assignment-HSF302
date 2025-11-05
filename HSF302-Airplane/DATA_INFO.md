# 📊 DỮ LIỆU HỆ THỐNG ĐẶT VÉ MÁY BAY

## 🔐 THÔNG TIN ĐĂNG NHẬP

### Admin (Quản trị viên)
- **Email:** `admin@admin`
- **Password:** `admin`
- **Quyền:** Toàn quyền quản lý hệ thống, quản lý users

### Manager (Quản lý)
- **Email:** `manager1@manager`, `manager2@manager`, `manager3@manager`
- **Password:** `manager`
- **Quyền:** Quản lý chuyến bay, airports, seats

### User (Người dùng)
- **Email:** `user1@user`, `user2@user`, `user3@user`, `user4@user`, `user5@user`
- **Password:** `user`
- **Quyền:** Đặt vé, xem lịch sử đặt vé

---

## ✈️ DỮ LIỆU SÂN BAY (15 Airports)

### Miền Bắc
1. **HAN** - Sân bay Quốc tế Nội Bài (Hà Nội)
2. **HPH** - Sân bay Cát Bi (Hải Phòng)
3. **VDO** - Sân bay Vân Đồn (Quảng Ninh)
4. **VII** - Sân bay Vinh (Nghệ An)
5. **THD** - Sân bay Thọ Xuân (Thanh Hóa)

### Miền Trung
6. **VDH** - Sân bay Đồng Hới (Quảng Bình)
7. **HUI** - Sân bay Phú Bài (Huế)
8. **DAD** - Sân bay Quốc tế Đà Nẵng
9. **UIH** - Sân bay Phù Cát (Quy Nhơn)
10. **CXR** - Sân bay Quốc tế Cam Ranh (Nha Trang)

### Miền Nam
11. **BMV** - Sân bay Buôn Ma Thuột
12. **DLI** - Sân bay Liên Khương (Đà Lạt)
13. **SGN** - Sân bay Quốc tế Tân Sơn Nhất (TP.HCM)
14. **VCA** - Sân bay Cần Thơ
15. **PQC** - Sân bay Quốc tế Phú Quốc

---

## 🛫 DỮ LIỆU CHUYẾN BAY (32 Flights)

### Tuyến Hà Nội - TP.HCM (6 chuyến/ngày)
- HAN → SGN: 06:00, 12:00, 18:30
- SGN → HAN: 07:00, 14:00, 20:00
- Giá: 2,500,000 - 3,000,000 VNĐ

### Tuyến Hà Nội - Đà Nẵng (4 chuyến)
- HAN → DAD: 09:00, 16:00
- DAD → HAN: 11:30, 19:00
- Giá: 1,700,000 - 1,900,000 VNĐ

### Tuyến TP.HCM - Đà Nẵng (2 chuyến)
- SGN → DAD: 08:00
- DAD → SGN: 15:00
- Giá: 1,600,000 VNĐ

### Tuyến TP.HCM - Phú Quốc (4 chuyến)
- SGN → PQC: 07:30, 14:00
- PQC → SGN: 10:00, 17:30
- Giá: 1,500,000 - 1,700,000 VNĐ

### Tuyến Hà Nội - Nha Trang (2 chuyến)
- HAN → CXR: 10:00
- CXR → HAN: 18:00
- Giá: 2,200,000 VNĐ

### Tuyến TP.HCM - Nha Trang (2 chuyến)
- SGN → CXR: 09:00
- CXR → SGN: 16:00
- Giá: 1,400,000 VNĐ

### Tuyến TP.HCM - Cần Thơ (2 chuyến)
- SGN → VCA: 11:00
- VCA → SGN: 17:00
- Giá: 1,200,000 VNĐ

### Tuyến Hà Nội - Hải Phòng (2 chuyến)
- HAN → HPH: 06:00
- HPH → HAN: 19:00
- Giá: 800,000 VNĐ

### Tuyến Hà Nội - Đà Lạt (2 chuyến)
- HAN → DLI: 08:30
- DLI → HAN: 15:00
- Giá: 2,100,000 VNĐ

### Tuyến TP.HCM - Đà Lạt (2 chuyến)
- SGN → DLI: 10:00
- DLI → SGN: 16:30
- Giá: 1,300,000 VNĐ

### Tuyến Hà Nội - Vân Đồn (2 chuyến)
- HAN → VDO: 07:00
- VDO → HAN: 18:00
- Giá: 900,000 VNĐ

---

## 💺 DỮ LIỆU GHẾ NGỒI (116 Seats)

### Business Class (20 ghế)
- **Hàng 1-5:** 1A, 1B, 1C, 1D, 2A, 2B, 2C, 2D, 3A, 3B, 3C, 3D, 4A, 4B, 4C, 4D, 5A, 5B, 5C, 5D
- **Tổng:** 20 ghế

### Economy Class (96 ghế)
- **Hàng 10-25:** Mỗi hàng có 6 ghế (A, B, C, D, E, F)
- **Tổng:** 16 hàng × 6 ghế = 96 ghế

---

## 🗂️ CẤU TRÚC DATABASE

### Tables (Bảng)

1. **roles** - Vai trò người dùng
   - roleId (PK)
   - roleName (ADMIN, MANAGER, USER)

2. **users** - Người dùng
   - userId (PK)
   - email (unique)
   - password (encrypted)
   - full_name
   - phone
   - roleId (FK)

3. **airports** - Sân bay
   - airport_id (PK)
   - code (IATA code - unique)
   - name
   - city
   - country

4. **flights** - Chuyến bay
   - flight_id (PK)
   - departure_airport (FK)
   - arrival_airport (FK)
   - departure_time
   - arrival_time
   - departure_date
   - arrival_date
   - price
   - status (SCHEDULED, DELAYED, CANCELLED, COMPLETED)

5. **seats** - Ghế ngồi
   - seat_id (PK)
   - seat_number (unique)

6. **flight_seats** - Ghế của chuyến bay
   - flightseat_id (PK)
   - flight_id (FK)
   - seat_id (FK)
   - seat_class (BUSINESS, ECONOMY)
   - seat_number
   - status (AVAILABLE, BOOKED, LOCKED)

7. **orders** - Đơn đặt vé
   - order_id (PK)
   - user_id (FK)
   - full_name
   - email
   - phone
   - total_price
   - status (PENDING, CONFIRMED, CANCELLED, COMPLETED)

8. **tickets** - Vé máy bay
   - ticket_id (PK)
   - seat_class (BUSINESS, ECONOMY)
   - booking_date
   - status (BOOKED, CANCELLED, USED)
   - order_id (FK)
   - price

---

## 🔄 RESET DỮ LIỆU

### Cách 1: Sử dụng PowerShell Script (Khuyên dùng)
```powershell
.\reset-and-run.ps1
```

### Cách 2: Chạy SQL Script thủ công
```sql
-- Chạy file: src/main/resources/sql/reset_database.sql
-- Sau đó khởi động lại ứng dụng
```

### Cách 3: Xóa database và tạo lại
```sql
DROP DATABASE airplane;
CREATE DATABASE airplane;
-- Khởi động lại ứng dụng
```

---

## 📝 GHI CHÚ

- Tất cả dữ liệu được tự động khởi tạo thông qua `DataInitializer.java`
- Mật khẩu được mã hóa bằng BCrypt
- Chuyến bay được tạo từ ngày hiện tại + 1 ngày trở đi
- Giá vé tính bằng VNĐ (đơn vị: đồng)
- Thời gian bay được tính theo múi giờ địa phương

---

## 🚀 KHỞI ĐỘNG ỨNG DỤNG

```powershell
# Chạy ứng dụng
.\run.ps1

# Hoặc
.\mvnw.cmd spring-boot:run
```

**URL:** http://localhost:8080

---

## 📞 HỖ TRỢ

Nếu gặp vấn đề với dữ liệu:
1. Chạy `reset-and-run.ps1` để reset database
2. Kiểm tra log để xem lỗi
3. Đảm bảo SQL Server đang chạy
4. Kiểm tra connection string trong `application.properties`
