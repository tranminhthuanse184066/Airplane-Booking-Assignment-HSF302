package main.pojo;

import jakarta.persistence.*;
import main.enumerators.SeatStatus;
import main.enumerators.TicketClass;
import java.math.BigDecimal;

@Entity
@Table(name = "flight_seats")
public class FlightSeat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flightseat_id")
    private Long flightSeatId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;
    
    @Column(name = "seat_class", length = 20)
    private String seatClass;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_class", length = 20)
    private TicketClass ticketClass;
    
    @Column(name = "seat_number", length = 10)
    private String seatNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private SeatStatus status;
    
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
    
    // Constructors
    public FlightSeat() {
    }
    
    public FlightSeat(Flight flight, Seat seat, String seatClass, String seatNumber, SeatStatus status) {
        this.flight = flight;
        this.seat = seat;
        this.seatClass = seatClass;
        this.seatNumber = seatNumber;
        this.status = status;
    }
    
    public FlightSeat(Flight flight, Seat seat, TicketClass ticketClass, String seatNumber, SeatStatus status, BigDecimal price) {
        this.flight = flight;
        this.seat = seat;
        this.ticketClass = ticketClass;
        this.seatClass = ticketClass.name();
        this.seatNumber = seatNumber;
        this.status = status;
        this.price = price;
    }
    
    // Getters and Setters
    public Long getFlightSeatId() {
        return flightSeatId;
    }
    
    public void setFlightSeatId(Long flightSeatId) {
        this.flightSeatId = flightSeatId;
    }
    
    public Flight getFlight() {
        return flight;
    }
    
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    
    public Seat getSeat() {
        return seat;
    }
    
    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    
    public String getSeatClass() {
        return seatClass;
    }
    
    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }
    
    public String getSeatNumber() {
        return seatNumber;
    }
    
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
    
    public SeatStatus getStatus() {
        return status;
    }
    
    public void setStatus(SeatStatus status) {
        this.status = status;
    }
    
    public Ticket getTicket() {
        return ticket;
    }
    
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    
    public TicketClass getTicketClass() {
        return ticketClass;
    }
    
    public void setTicketClass(TicketClass ticketClass) {
        this.ticketClass = ticketClass;
        this.seatClass = ticketClass.name();
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
