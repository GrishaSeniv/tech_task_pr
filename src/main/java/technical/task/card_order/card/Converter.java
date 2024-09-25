package technical.task.card_order.card;

import technical.task.card_order.domain.model.CardDTO;
import technical.task.card_order.domain.model.CardProjection;

import java.util.List;

import static technical.task.card_order.util.Utils.getFLN;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
class Converter {
    public static List<CardDTO> toCardDTOS(List<CardProjection> cardProjections) {
        return cardProjections.stream()
                .map(Converter::toCardDTO)
                .toList();
    }

    public static CardDTO toCardDTO(CardProjection projection) {
        return new CardDTO()
                .setId(projection.getId())
                .setName(projection.getName())
                .setBin(projection.getBin())
                .setIsActive(projection.getIsActive())
                .setOperatorFLN(getFLN(projection.getOperatorFirstName(), projection.getOperatorLastName(), projection.getOperatorSurname()));
    }
}
