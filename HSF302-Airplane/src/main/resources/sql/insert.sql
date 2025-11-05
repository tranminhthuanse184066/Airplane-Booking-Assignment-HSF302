-- Script SQL để insert dữ liệu mẫu
-- Lưu ý: Script này chỉ mang tính tham khảo
-- Dữ liệu thực tế được insert tự động thông qua DataInitializer.java

USE airplane;
GO

-- Thông tin đăng nhập:
-- Admin:   admin@admin / admin
-- Manager: manager1@manager, manager2@manager, manager3@manager / manager  
-- User:    user1@user, user2@user, user3@user, user4@user, user5@user / user

-- Dữ liệu được tự động tạo khi khởi động ứng dụng:
-- - 3 Roles: ADMIN, MANAGER, USER
-- - 1 Admin account
-- - 3 Manager accounts  
-- - 5 User accounts
-- - 15 Airports (sân bay Việt Nam)
-- - 32 Flights (các chuyến bay nội địa)
-- - 116 Seats (20 Business Class + 96 Economy Class)

PRINT 'Data is automatically initialized by DataInitializer.java';
PRINT 'Please start the application to load all data.';
GO
