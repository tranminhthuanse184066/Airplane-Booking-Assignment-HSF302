-- Script SQL để reset toàn bộ database
-- Chạy script này khi muốn xóa toàn bộ dữ liệu và tạo lại từ đầu

USE airplane;
GO

-- Disable all constraints
EXEC sp_MSforeachtable 'ALTER TABLE ? NOCHECK CONSTRAINT ALL';
GO

-- Delete all data from tables (order matters due to foreign keys)
DELETE FROM tickets;
DELETE FROM orders;
DELETE FROM flight_seats;
DELETE FROM flights;
DELETE FROM seats;
DELETE FROM airports;
DELETE FROM users;
DELETE FROM roles;
GO

-- Re-enable all constraints
EXEC sp_MSforeachtable 'ALTER TABLE ? CHECK CONSTRAINT ALL';
GO

-- Reset identity columns
DBCC CHECKIDENT ('roles', RESEED, 0);
DBCC CHECKIDENT ('users', RESEED, 0);
DBCC CHECKIDENT ('airports', RESEED, 0);
DBCC CHECKIDENT ('seats', RESEED, 0);
DBCC CHECKIDENT ('flights', RESEED, 0);
DBCC CHECKIDENT ('flight_seats', RESEED, 0);
DBCC CHECKIDENT ('orders', RESEED, 0);
DBCC CHECKIDENT ('tickets', RESEED, 0);
GO

PRINT 'Database reset successfully! Please restart the application to reinitialize data.';
GO
