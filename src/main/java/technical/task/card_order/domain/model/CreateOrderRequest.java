package technical.task.card_order.domain.model;

import jakarta.validation.constraints.NotNull;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
public class CreateOrderRequest {
    @NotNull
    private Long cardId;
    @NotNull
    private Long clientId;

    public Long getCardId() {
        return cardId;
    }

    public CreateOrderRequest setCardId(Long cardId) {
        this.cardId = cardId;
        return this;
    }

    public Long getClientId() {
        return clientId;
    }

    public CreateOrderRequest setClientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                "cardId=" + cardId +
                ", clientId=" + clientId +
                '}';
    }
}
