package technical.task.card_order.domain.model;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-26
 */
public class CardNumberGenerateResponse {
    private long cardNumberGenerated;

    public CardNumberGenerateResponse(long cardNumberGenerated) {
        this.cardNumberGenerated = cardNumberGenerated;
    }

    public long getCardNumberGenerated() {
        return cardNumberGenerated;
    }

    public CardNumberGenerateResponse setCardNumberGenerated(long cardNumberGenerated) {
        this.cardNumberGenerated = cardNumberGenerated;
        return this;
    }

    @Override
    public String toString() {
        return "CardNumberGenerateResponse{" +
                "cardNumberGenerated='" + cardNumberGenerated + '\'' +
                '}';
    }
}
