# Script PowerShell để chạy ứng dụng Vietnam Airlines

Write-Host ""
Write-Host "=====================================" -ForegroundColor Cyan
Write-Host "  VIETNAM AIRLINES BOOKING SYSTEM" -ForegroundColor Cyan
Write-Host "=====================================" -ForegroundColor Cyan
Write-Host ""
Write-Host "Đang khởi động ứng dụng..." -ForegroundColor Yellow
Write-Host ""
Write-Host "THÔNG TIN ĐĂNG NHẬP:" -ForegroundColor Green
Write-Host "  Admin:   admin@admin / admin" -ForegroundColor White
Write-Host "  Manager: manager1@manager / manager" -ForegroundColor White
Write-Host "  Manager: manager2@manager / manager" -ForegroundColor White
Write-Host "  Manager: manager3@manager / manager" -ForegroundColor White
Write-Host "  User:    user1@user / user" -ForegroundColor White
Write-Host "  User:    user2@user / user" -ForegroundColor White
Write-Host "  User:    user3@user / user" -ForegroundColor White
Write-Host "  User:    user4@user / user" -ForegroundColor White
Write-Host "  User:    user5@user / user" -ForegroundColor White
Write-Host ""
Write-Host "URL: http://localhost:8080" -ForegroundColor Yellow
Write-Host ""
Write-Host "Nhấn Ctrl+C để dừng ứng dụng" -ForegroundColor Red
Write-Host "=====================================" -ForegroundColor Cyan
Write-Host ""

# Chạy ứng dụng
.\mvnw.cmd spring-boot:run
