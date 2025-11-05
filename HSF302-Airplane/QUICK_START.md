# 🚀 HƯỚNG DẪN NHANH

## Cách 1: Sử dụng Script PowerShell (Khuyến nghị)

### Chạy ứng dụng
```powershell
.\run.ps1
```

### Reset database và chạy lại (nếu gặp lỗi đăng nhập)
```powershell
.\reset-and-run.ps1
```

---

## Cách 2: Chạy thủ công

### Bước 1: Reset Database (nếu cần)
1. Mở SQL Server Management Studio
2. Mở file: `src\main\resources\sql\reset_database.sql`
3. Chọn database `hsf302_airplane`
4. Chạy script (F5)

### Bước 2: Chạy ứng dụng
```powershell
.\mvnw.cmd spring-boot:run
```

### Bước 3: Truy cập
Mở trình duyệt: **http://localhost:8080**

---

## 🔐 ĐĂNG NHẬP

| Vai trò | Email | Mật khẩu |
|---------|-------|----------|
| Admin | admin@gmail.com | admin1 |
| Manager | manager@gmail.com | manager1 |
| User | user@gmail.com | user1 |

---

## ❌ XỬ LÝ LỖI

### Lỗi: "Login failed" hoặc "Bad credentials"
**Nguyên nhân:** Dữ liệu trong database chưa được tạo hoặc bị lỗi

**Giải pháp:**
```powershell
.\reset-and-run.ps1
```
Chọn Y để reset database

### Lỗi: "Access Denied"
**Nguyên nhân:** Đăng nhập sai role hoặc truy cập sai URL

**Giải pháp:**
- Admin → Truy cập: http://localhost:8080/admin
- Manager → Truy cập: http://localhost:8080/manager
- User → Truy cập: http://localhost:8080/user

### Lỗi: "Cannot connect to database"
**Nguyên nhân:** SQL Server chưa chạy

**Giải pháp:**
1. Mở "Services" (services.msc)
2. Tìm "SQL Server (MSSQLSERVER)" hoặc "SQL Server (SQLEXPRESS)"
3. Click phải → Start

---

## 📚 TÀI LIỆU CHI TIẾT
- **Hướng dẫn chi tiết:** [HUONG_DAN_SU_DUNG.md](HUONG_DAN_SU_DUNG.md)
- **README đầy đủ:** [README.md](README.md)

---

✈️ **Chúc bạn sử dụng thành công!**
