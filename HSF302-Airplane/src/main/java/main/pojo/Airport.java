package main.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "airports")
public class Airport {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id")
    private Integer airportId;
    
    @Column(name = "code", nullable = false, unique = true, length = 10)
    private String code;
    
    @Column(name = "name", nullable = false, length = 200, columnDefinition = "NVARCHAR(200)")
    private String name;
    
    @Column(name = "city", length = 100, columnDefinition = "NVARCHAR(100)")
    private String city;
    
    @Column(name = "country", length = 100, columnDefinition = "NVARCHAR(100)")
    private String country;
    
    // Constructors
    public Airport() {
    }
    
    public Airport(String code, String name, String city, String country) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.country = country;
    }
    
    // Getters and Setters
    public Integer getAirportId() {
        return airportId;
    }
    
    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
}
