package technical.task.card_order.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.Objects;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-24
 */
// order-card MANY-to-ONE
// order-client MANY-to-ONE
// order-user MANY-to-ONE (user means operator, who creates this order)
// client-user MANY-to-ONE (user means operator, who added this client to the system)
// card-user MANY-to-ONE (user means operator, who added this card to the system)
@Entity
@Table(name = "`order`")
class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "card_number")
    private String cardNumber;

    public Long getId() {
        return id;
    }

    public OrderEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public OrderEntity setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Long getClientId() {
        return clientId;
    }

    public OrderEntity setClientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public OrderEntity setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Long getCardId() {
        return cardId;
    }

    public OrderEntity setCardId(Long cardId) {
        this.cardId = cardId;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public OrderEntity setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", clientId=" + clientId +
                ", createdBy=" + createdBy +
                ", cardId=" + cardId +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
