package main.pojo;

import jakarta.persistence.*;
import main.enumerators.FlightStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Integer flightId;
    
    @Column(name = "flight_number", length = 20)
    private String flightNumber;

    @Column(name = "departure_airport", nullable = false, length = 10)
    private String departureAirport;
    
    @Column(name = "arrival_airport", nullable = false, length = 10)
    private String arrivalAirport;
    
    @Column(name = "departure_time")
    private LocalDateTime departureTime;
    
    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;
    
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private FlightStatus status;
    
    @Column(name = "departure_date")
    private LocalDate departureDate;
    
    @Column(name = "arrival_date")
    private LocalDate arrivalDate;
    
    @Column(name = "has_multiple_classes")
    private Boolean hasMultipleClasses = false;
    
    public Flight() {
    }
    
    public Flight(String departureAirport, String arrivalAirport, LocalDateTime departureTime, 
                  LocalDateTime arrivalTime, BigDecimal price, FlightStatus status) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.status = status;
        if (departureTime != null) {
            this.departureDate = departureTime.toLocalDate();
        }
        if (arrivalTime != null) {
            this.arrivalDate = arrivalTime.toLocalDate();
        }
    }

    public Flight(String flightNumber, String departureAirport, String arrivalAirport,
                  LocalDateTime departureTime, LocalDateTime arrivalTime,
                  BigDecimal price, FlightStatus status) {
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.status = status;
        if (departureTime != null) {
            this.departureDate = departureTime.toLocalDate();
        }
        if (arrivalTime != null) {
            this.arrivalDate = arrivalTime.toLocalDate();
        }
    }

    public Integer getFlightId() {
        return flightId;
    }
    
    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }
    
    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }
    
    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }
    
    public String getArrivalAirport() {
        return arrivalAirport;
    }
    
    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }
    
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    
    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
        if (departureTime != null) {
            this.departureDate = departureTime.toLocalDate();
        }
    }
    
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
    
    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
        if (arrivalTime != null) {
            this.arrivalDate = arrivalTime.toLocalDate();
        }
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public FlightStatus getStatus() {
        return status;
    }
    
    public void setStatus(FlightStatus status) {
        this.status = status;
    }
    
    public LocalDate getDepartureDate() {
        return departureDate;
    }
    
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
    
    public LocalDate getArrivalDate() {
        return arrivalDate;
    }
    
    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
    
    public Boolean getHasMultipleClasses() {
        return hasMultipleClasses;
    }
    
    public void setHasMultipleClasses(Boolean hasMultipleClasses) {
        this.hasMultipleClasses = hasMultipleClasses;
    }
}
