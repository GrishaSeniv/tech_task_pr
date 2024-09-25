package technical.task.card_order.domain;

import java.time.Instant;
import java.util.Objects;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-24
 */
public class OrderDTO {
    private Long id;
    private Instant createdAt;
    private String firstName;
    private String lastName;
    private String firstNameLat;
    private String lastNameLat;
    private String operatorFLN;
    private String cardName;
    private String cardNumber;

    public Long getId() {
        return id;
    }

    public OrderDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public OrderDTO setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public OrderDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public OrderDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstNameLat() {
        return firstNameLat;
    }

    public OrderDTO setFirstNameLat(String firstNameLat) {
        this.firstNameLat = firstNameLat;
        return this;
    }

    public String getLastNameLat() {
        return lastNameLat;
    }

    public OrderDTO setLastNameLat(String lastNameLat) {
        this.lastNameLat = lastNameLat;
        return this;
    }

    public String getOperatorFLN() {
        return operatorFLN;
    }

    public OrderDTO setOperatorFLN(String operatorFLN) {
        this.operatorFLN = operatorFLN;
        return this;
    }

    public String getCardName() {
        return cardName;
    }

    public OrderDTO setCardName(String cardName) {
        this.cardName = cardName;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public OrderDTO setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(id, orderDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstNameLat='" + firstNameLat + '\'' +
                ", secondNameLat='" + lastNameLat + '\'' +
                ", operatorFLN='" + operatorFLN + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
