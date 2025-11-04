package main.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Integer seatId;
    
    @Column(name = "seat_number", nullable = false, length = 10)
    private String seatNumber;
    
    // Constructors
    public Seat() {
    }
    
    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }
    
    // Getters and Setters
    public Integer getSeatId() {
        return seatId;
    }
    
    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }
    
    public String getSeatNumber() {
        return seatNumber;
    }
    
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
