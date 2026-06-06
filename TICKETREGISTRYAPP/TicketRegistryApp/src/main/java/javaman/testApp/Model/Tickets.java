package javaman.testApp.Model;



import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Tickets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;

    @Column(name = "ticket_number", nullable = false, unique = true)
    private String ticketNumber;

    @Column(nullable = false)
    private String name;

    @Column
    private String contact;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column
    private String solicitor;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // =========================
    // Getters and Setters
    // =========================

   
    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSolicitor() {
        return solicitor;
    }

    public void setSolicitor(String solicitor) {
        this.solicitor = solicitor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}