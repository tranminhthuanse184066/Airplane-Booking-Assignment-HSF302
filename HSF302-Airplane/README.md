# HSF302 - Hệ thống bán vé máy bay Vietnam Airlines

## Giới thiệu
Đây là dự án web bán vé máy bay cho Vietnam Airlines với 3 phân quyền:
- **USER**: Người dùng có thể tìm kiếm và đặt vé máy bay
- **MANAGER**: Quản lý chuyến bay, CRUD vé máy bay và sân bay
- **ADMIN**: Quản lý tài khoản người dùng và manager

## Công nghệ sử dụng
- **Backend**: Java Spring Boot 3.2.0
- **Database**: Microsoft SQL Server
- **ORM**: Spring Data JPA (Hibernate)
- **Security**: Spring Security với phân quyền role-based
- **Template Engine**: Thymeleaf
- **Build Tool**: Maven

## Cấu trúc dự án
```
src/
├── main/
│   ├── java/main/
│   │   ├── common/              # Exception handlers
│   │   ├── config/              # Security configuration
│   │   ├── controller/          # Controllers (Public, Admin, Manager, User)
│   │   ├── enumerators/         # Enums (RoleEnum, FlightStatus, etc.)
│   │   ├── pojo/                # Entity classes
│   │   ├── repository/          # JPA Repositories
│   │   └── service/             # Service layer với implementation
│   │       ├── category/        # Airport services
│   │       ├── order/           # Order services
│   │       ├── produce/         # Flight services
│   │       └── user/            # User services
│   └── resources/
│       ├── templates/           # Thymeleaf HTML templates
│       │   ├── admin/           # Admin pages
│       │   ├── auth/            # Login/Register
│       │   ├── manager/         # Manager pages
│       │   ├── public/          # Public pages
│       │   └── user/            # User pages
│       ├── sql/                 # SQL scripts
│       └── application.properties
```

## Cài đặt và chạy

### 1. Yêu cầu hệ thống
- Java 17 trở lên
- Microsoft SQL Server 2019+ (hoặc SQL Server Express)
- Maven 3.6+

### 2. Cấu hình database
1. Tạo database SQL Server:
```sql
CREATE DATABASE hsf302_airplane;
GO
```

2. Cập nhật thông tin database trong `application.properties`:
```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=hsf302_airplane;encrypt=true;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=your_password
```

**Lưu ý**: 
- Thay `your_password` bằng mật khẩu SQL Server của bạn
- Port mặc định của SQL Server là `1433`
- Nếu dùng SQL Server Express, có thể cần thay `localhost:1433` thành `localhost\\SQLEXPRESS`

### 3. Build và chạy ứng dụng
```bash
# Build project
mvn clean install

# Run application
mvn spring-boot:run
```

Hoặc chạy file `Hsf302AirplaneApplication.java` từ IDE.

### 4. Truy cập ứng dụng
- **URL**: http://localhost:8080
- **Trang public**: http://localhost:8080 (không cần đăng nhập)
- **Trang login**: http://localhost:8080/login

## Tài khoản mặc định

### Admin
- **Email**: admin@vietnamairlines.com
- **Password**: admin123
- **URL**: http://localhost:8080/admin

### Manager
- **Email**: manager@vietnamairlines.com
- **Password**: manager123
- **URL**: http://localhost:8080/manager

### User
- **Email**: user@example.com
- **Password**: user123
- **URL**: http://localhost:8080/user

## Chức năng theo role

### 1. Public (Không đăng nhập)
- ✅ Xem trang chủ
- ✅ Tìm kiếm chuyến bay
- ✅ Xem thông tin chuyến bay
- ✅ Đăng ký tài khoản
- ✅ Đăng nhập

### 2. USER
- ✅ Tất cả chức năng Public
- ✅ Xem dashboard cá nhân
- ✅ Xem lịch sử đơn hàng
- ✅ Xem chi tiết đơn hàng
- ✅ Cập nhật thông tin cá nhân
- ✅ Đặt vé máy bay

### 3. MANAGER
- ✅ Xem dashboard quản lý
- ✅ **CRUD Chuyến bay**:
  - Thêm chuyến bay mới
  - Sửa thông tin chuyến bay
  - Xóa chuyến bay
  - Xem danh sách chuyến bay
- ✅ **CRUD Sân bay**:
  - Thêm sân bay mới
  - Sửa thông tin sân bay
  - Xóa sân bay
  - Xem danh sách sân bay

### 4. ADMIN
- ✅ Xem dashboard admin
- ✅ **CRUD Người dùng**:
  - Thêm người dùng mới (User/Manager/Admin)
  - Sửa thông tin người dùng
  - Xóa người dùng
  - Xem danh sách tất cả người dùng
- ✅ Xem danh sách Manager riêng
- ✅ Phân quyền cho người dùng

## Database Schema

### Bảng chính:
1. **roles** - Vai trò (USER, MANAGER, ADMIN)
2. **users** - Thông tin người dùng
3. **airports** - Sân bay
4. **flights** - Chuyến bay
5. **seats** - Ghế ngồi
6. **flight_seats** - Ghế của từng chuyến bay
7. **orders** - Đơn hàng
8. **tickets** - Vé máy bay

## API Endpoints

### Public
- `GET /` - Trang chủ
- `GET /search` - Tìm kiếm chuyến bay
- `GET /flight/{id}` - Chi tiết chuyến bay
- `GET /login` - Trang đăng nhập
- `GET /register` - Trang đăng ký

### Admin (Role: ADMIN)
- `GET /admin` - Admin dashboard
- `GET /admin/users` - Danh sách người dùng
- `GET /admin/users/create` - Form tạo user
- `POST /admin/users/create` - Tạo user mới
- `GET /admin/users/edit/{id}` - Form sửa user
- `POST /admin/users/edit/{id}` - Cập nhật user
- `GET /admin/users/delete/{id}` - Xóa user

### Manager (Role: MANAGER)
- `GET /manager` - Manager dashboard
- `GET /manager/flights` - Danh sách chuyến bay
- `GET /manager/flights/create` - Form tạo chuyến bay
- `POST /manager/flights/create` - Tạo chuyến bay mới
- `GET /manager/flights/edit/{id}` - Form sửa chuyến bay
- `POST /manager/flights/edit/{id}` - Cập nhật chuyến bay
- `GET /manager/flights/delete/{id}` - Xóa chuyến bay
- `GET /manager/airports` - Danh sách sân bay
- `GET /manager/airports/create` - Form tạo sân bay
- `POST /manager/airports/create` - Tạo sân bay mới
- `GET /manager/airports/edit/{id}` - Form sửa sân bay
- `POST /manager/airports/edit/{id}` - Cập nhật sân bay
- `GET /manager/airports/delete/{id}` - Xóa sân bay

### User (Role: USER)
- `GET /user` - User dashboard
- `GET /user/orders` - Danh sách đơn hàng
- `GET /user/orders/{id}` - Chi tiết đơn hàng
- `GET /user/profile` - Thông tin cá nhân
- `POST /user/profile` - Cập nhật thông tin

## Lưu ý bảo mật
- Tất cả mật khẩu được mã hóa bằng BCrypt
- Spring Security được cấu hình với role-based access control
- Mỗi role chỉ có thể truy cập các endpoint được phép
- Session-based authentication
- CSRF protection được bật

## Dữ liệu mẫu
Hệ thống đã có sẵn dữ liệu mẫu:
- 3 tài khoản (Admin, Manager, User)
- 10 sân bay tại Việt Nam
- 10 chuyến bay mẫu
- 80+ ghế ngồi (Business và Economy)

## Mở rộng
Có thể mở rộng thêm các tính năng:
- Thanh toán online
- Gửi email xác nhận
- Quản lý booking
- Báo cáo thống kê
- Export/Import data
- API REST cho mobile app

## Tác giả
HSF302 - Vietnam Airlines Flight Booking System

## License
Educational Project - HSF302
