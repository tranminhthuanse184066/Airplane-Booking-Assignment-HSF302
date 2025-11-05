# 📝 DANH SÁCH THAY ĐỔI

## ✅ Những gì đã được sửa

### 1. Đơn giản hóa thông tin đăng nhập
**Trước:**
- Admin: admin@vietnamairlines.com / admin123
- Manager: manager@vietnamairlines.com / manager123  
- User: user@example.com / user123

**Sau:**
- Admin: admin@gmail.com / admin1
- Manager: manager@gmail.com / manager1
- User: user@gmail.com / user1

### 2. Cập nhật file dữ liệu
- ✅ `DataInitializer.java` - Thay đổi email, password, và tên người dùng
- ✅ `insert.sql` - Cập nhật dữ liệu khởi tạo

### 3. Cải thiện SecurityConfig.java
- ✅ Thêm logging để debug
- ✅ Thêm failure handler để xử lý lỗi đăng nhập
- ✅ Tắt CSRF tạm thời để test dễ hơn
- ✅ Redirect tự động đến trang dashboard tương ứng với role

### 4. Tạo trang Access Denied
- ✅ `access-denied.html` - Trang hiển thị khi user không có quyền
- ✅ Thêm route `/access-denied` trong PublicController

### 5. Tạo script và file hướng dẫn
**Scripts:**
- ✅ `reset_database.sql` - Script reset database nhanh
- ✅ `run.ps1` - Script PowerShell chạy ứng dụng
- ✅ `reset-and-run.ps1` - Script reset và chạy ứng dụng

**Tài liệu:**
- ✅ `HUONG_DAN_SU_DUNG.md` - Hướng dẫn chi tiết đầy đủ
- ✅ `QUICK_START.md` - Hướng dẫn nhanh
- ✅ `README.md` - Cập nhật thông tin đăng nhập mới
- ✅ `CHANGELOG.md` - File này

---

## 🔧 Cách sử dụng

### Bước 1: Reset Database (BẮT BUỘC nếu đã chạy ứng dụng trước đó)
```sql
-- Chạy file này trong SQL Server Management Studio
src\main\resources\sql\reset_database.sql
```

### Bước 2: Chạy ứng dụng
```powershell
# Cách 1: Dùng script (Khuyến nghị)
.\run.ps1

# Cách 2: Chạy thủ công
.\mvnw.cmd spring-boot:run
```

### Bước 3: Đăng nhập
Truy cập: http://localhost:8080/login

**Tài khoản:**
- Admin: admin@gmail.com / admin1
- Manager: manager@gmail.com / manager1
- User: user@gmail.com / user1

---

## 🎯 Chức năng theo Role

### Admin (admin@gmail.com)
✅ CRUD tài khoản người dùng
- Tạo user mới
- Sửa thông tin user
- Xóa user
- Xem danh sách tất cả user
- Phân quyền

**URL:** http://localhost:8080/admin

### Manager (manager@gmail.com)
✅ CRUD chuyến bay
- Tạo chuyến bay mới
- Sửa thông tin chuyến bay
- Xóa chuyến bay
- Xem danh sách chuyến bay

✅ CRUD sân bay
- Tạo sân bay mới
- Sửa thông tin sân bay
- Xóa sân bay
- Xem danh sách sân bay

✅ Quản lý chỗ ngồi
- Xem danh sách ghế
- Cập nhật trạng thái ghế

**URL:** http://localhost:8080/manager

### User (user@gmail.com)
✅ Tìm kiếm và đặt vé
- Tìm kiếm chuyến bay
- Chọn ghế ngồi
- Đặt vé máy bay

✅ Quản lý đơn hàng
- Xem danh sách đơn hàng
- Xem chi tiết đơn hàng
- Theo dõi trạng thái

✅ Thông tin cá nhân
- Cập nhật profile
- Đổi mật khẩu

**URL:** http://localhost:8080/user

---

## 🐛 Sửa lỗi

### Lỗi đã được khắc phục:
1. ✅ Không đăng nhập được với các role
2. ✅ Redirect sai sau khi login
3. ✅ Dữ liệu khởi tạo quá phức tạp
4. ✅ Thiếu trang error handling

### Cải thiện:
1. ✅ Thêm logging để debug
2. ✅ Tạo script tự động
3. ✅ Viết tài liệu chi tiết
4. ✅ Đơn giản hóa dữ liệu test

---

## 📋 Checklist kiểm tra

Sau khi chạy ứng dụng, kiểm tra:

- [ ] SQL Server đang chạy
- [ ] Database `hsf302_airplane` đã được tạo
- [ ] Đã chạy script reset_database.sql (nếu chạy lần đầu hoặc có lỗi)
- [ ] Ứng dụng chạy thành công tại http://localhost:8080
- [ ] Console log hiển thị:
  ```
  ✅ Roles initialized
  ✅ Users initialized
  📧 Admin: admin@gmail.com / admin1
  📧 Manager: manager@gmail.com / manager1
  📧 User: user@gmail.com / user1
  ```
- [ ] Đăng nhập thành công với admin@gmail.com
- [ ] Redirect đến /admin sau khi login
- [ ] Có thể CRUD user
- [ ] Đăng nhập thành công với manager@gmail.com
- [ ] Redirect đến /manager sau khi login
- [ ] Có thể CRUD chuyến bay và sân bay
- [ ] Đăng nhập thành công với user@gmail.com
- [ ] Redirect đến /user sau khi login
- [ ] Có thể tìm kiếm và xem chuyến bay

---

## 📞 Liên hệ và hỗ trợ

Nếu gặp vấn đề:
1. Kiểm tra console log của ứng dụng
2. Kiểm tra SQL Server log
3. Xem file `HUONG_DAN_SU_DUNG.md` để biết chi tiết
4. Chạy lại script reset_database.sql và khởi động lại ứng dụng

---

**Ngày cập nhật:** 2025-11-05
**Phiên bản:** 2.0 (Simplified)
