package technical.task.card_order.domain.model;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
public class CardBinProjectionDTO {
    private final Long orderId;
    private final String cardBin;
    private String cardNumber;

    public CardBinProjectionDTO(Long orderId, String cardBin) {
        this.orderId = orderId;
        this.cardBin = cardBin;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCardBin() {
        return cardBin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public CardBinProjectionDTO setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    @Override
    public String toString() {
        return "CardBinProjectionDTO{" +
                "orderId=" + orderId +
                ", cardBin='" + cardBin + '\'' +
                '}';
    }
}
