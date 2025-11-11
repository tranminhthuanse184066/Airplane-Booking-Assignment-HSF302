package main.enumerators;

public enum OrderStatus {
    PENDING,
    CONFIRMED,
    PAID,
    CHECK_IN_PENDING,  // Đang chờ manager xác nhận check-in
    CANCELLED,
    REFUNDED
}
