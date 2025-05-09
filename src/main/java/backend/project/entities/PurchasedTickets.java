package backend.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchased_tickets")
public class PurchasedTickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double purchasePrice;
    private LocalDateTime purchaseDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ticket_type_id")
    private TicketType ticketType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
}
