package main.enumerators;

/**
 * Enum định nghĩa các hạng vé máy bay
 * - ECONOMY: Vé phổ thông (rẻ nhất)
 * - PREMIUM_ECONOMY: Vé phổ thông đặc biệt (trung bình)
 * - BUSINESS: Vé thương gia (đắt nhất)
 */
public enum TicketClass {
    ECONOMY("Phổ thông", 1.0),
    PREMIUM_ECONOMY("Phổ thông Đặc biệt", 1.5),
    BUSINESS("Thương gia", 2.5);
    
    private final String displayName;
    private final double priceMultiplier; // Hệ số nhân giá
    
    TicketClass(String displayName, double priceMultiplier) {
        this.displayName = displayName;
        this.priceMultiplier = priceMultiplier;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public double getPriceMultiplier() {
        return priceMultiplier;
    }
}
