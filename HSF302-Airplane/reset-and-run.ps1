# Script PowerShell để reset database và chạy lại ứng dụng
# Chạy script này nếu gặp lỗi đăng nhập

Write-Host "=====================================" -ForegroundColor Cyan
Write-Host "VIETNAM AIRLINES - DATABASE RESET" -ForegroundColor Cyan
Write-Host "=====================================" -ForegroundColor Cyan
Write-Host ""

# Kiểm tra xem SQL Server có đang chạy không
Write-Host "Đang kiểm tra SQL Server..." -ForegroundColor Yellow

$sqlServiceStatus = Get-Service -Name "MSSQLSERVER" -ErrorAction SilentlyContinue
if ($null -eq $sqlServiceStatus) {
    # Nếu không có MSSQLSERVER, thử SQL Server Express
    $sqlServiceStatus = Get-Service -Name "MSSQL`$SQLEXPRESS" -ErrorAction SilentlyContinue
}

if ($null -eq $sqlServiceStatus) {
    Write-Host "❌ Không tìm thấy SQL Server!" -ForegroundColor Red
    Write-Host "Vui lòng cài đặt SQL Server hoặc SQL Server Express" -ForegroundColor Red
    exit
}

if ($sqlServiceStatus.Status -ne "Running") {
    Write-Host "❌ SQL Server chưa chạy! Vui lòng khởi động SQL Server" -ForegroundColor Red
    exit
}

Write-Host "✅ SQL Server đang chạy" -ForegroundColor Green
Write-Host ""

# Reset database
Write-Host "Bạn muốn reset database không? (Y/N)" -ForegroundColor Yellow
$confirm = Read-Host

if ($confirm -eq "Y" -or $confirm -eq "y") {
    Write-Host ""
    Write-Host "Đang reset database..." -ForegroundColor Yellow
    Write-Host ""
    
    # Chạy script SQL
    $sqlScript = Get-Content ".\src\main\resources\sql\reset_database.sql" -Raw
    
    try {
        # Sử dụng sqlcmd để chạy script
        $result = sqlcmd -S localhost -d hsf302_airplane -E -Q $sqlScript 2>&1
        
        if ($LASTEXITCODE -eq 0) {
            Write-Host "✅ Reset database thành công!" -ForegroundColor Green
        } else {
            Write-Host "⚠️ Có lỗi khi reset database. Vui lòng chạy script SQL thủ công." -ForegroundColor Yellow
            Write-Host "Script location: .\src\main\resources\sql\reset_database.sql" -ForegroundColor Yellow
        }
    } catch {
        Write-Host "⚠️ Không thể chạy sqlcmd. Vui lòng chạy script SQL thủ công:" -ForegroundColor Yellow
        Write-Host ".\src\main\resources\sql\reset_database.sql" -ForegroundColor Yellow
    }
    
    Write-Host ""
}

# Hỏi có muốn chạy ứng dụng không
Write-Host "Bạn muốn khởi động ứng dụng không? (Y/N)" -ForegroundColor Yellow
$runApp = Read-Host

if ($runApp -eq "Y" -or $runApp -eq "y") {
    Write-Host ""
    Write-Host "Đang khởi động ứng dụng..." -ForegroundColor Yellow
    Write-Host ""
    Write-Host "=====================================" -ForegroundColor Cyan
    Write-Host "THÔNG TIN ĐĂNG NHẬP" -ForegroundColor Cyan
    Write-Host "=====================================" -ForegroundColor Cyan
    Write-Host "Admin:   admin@admin / admin" -ForegroundColor Green
    Write-Host "Manager: manager1@manager / manager" -ForegroundColor Green
    Write-Host "Manager: manager2@manager / manager" -ForegroundColor Green
    Write-Host "Manager: manager3@manager / manager" -ForegroundColor Green
    Write-Host "User:    user1@user / user" -ForegroundColor Green
    Write-Host "User:    user2@user / user" -ForegroundColor Green
    Write-Host "User:    user3@user / user" -ForegroundColor Green
    Write-Host "User:    user4@user / user" -ForegroundColor Green
    Write-Host "User:    user5@user / user" -ForegroundColor Green
    Write-Host "=====================================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "Ứng dụng sẽ chạy tại: http://localhost:8080" -ForegroundColor Yellow
    Write-Host "Nhấn Ctrl+C để dừng ứng dụng" -ForegroundColor Yellow
    Write-Host ""
    
    # Chạy ứng dụng
    .\mvnw.cmd spring-boot:run
} else {
    Write-Host ""
    Write-Host "Để chạy ứng dụng sau, sử dụng lệnh:" -ForegroundColor Yellow
    Write-Host ".\mvnw.cmd spring-boot:run" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "=====================================" -ForegroundColor Cyan
    Write-Host "THÔNG TIN ĐĂNG NHẬP" -ForegroundColor Cyan
    Write-Host "=====================================" -ForegroundColor Cyan
    Write-Host "Admin:   admin@admin / admin" -ForegroundColor Green
    Write-Host "Manager: manager1@manager / manager" -ForegroundColor Green
    Write-Host "Manager: manager2@manager / manager" -ForegroundColor Green
    Write-Host "Manager: manager3@manager / manager" -ForegroundColor Green
    Write-Host "User:    user1@user / user" -ForegroundColor Green
    Write-Host "User:    user2@user / user" -ForegroundColor Green
    Write-Host "User:    user3@user / user" -ForegroundColor Green
    Write-Host "User:    user4@user / user" -ForegroundColor Green
    Write-Host "User:    user5@user / user" -ForegroundColor Green
    Write-Host "=====================================" -ForegroundColor Cyan
}
