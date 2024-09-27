package technical.task.card_order.card;

import technical.task.card_order.domain.model.CardDTO;
import technical.task.card_order.domain.model.CardProjection;
import technical.task.card_order.domain.model.CreateCardRequest;
import technical.task.card_order.domain.model.UpdateCardRequest;
import technical.task.card_order.user.UserInfoDetails;

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

    public static CardEntity toCardEntity(CreateCardRequest request, Long operatorId) {
        return new CardEntity()
                .setName(request.getName())
                .setBin(request.getBin())
                .setActive(request.getIsActive())
                .setEditedBy(operatorId);
    }

    public static CardEntity toCardEntity(UpdateCardRequest request, Long operatorId) {
        return new CardEntity()
                .setName(request.getName())
                .setBin(request.getBin())
                .setActive(request.getIsActive())
                .setEditedBy(operatorId);
    }

    public static CardDTO toCardDTO(CardEntity entity, UserInfoDetails operator) {
        return toCardDTO(entity)
                .setOperatorFLN(getFLN(operator.getFirstName(), operator.getLastName(), operator.getSurname()));
    }

    public static CardDTO toCardDTO(CardEntity entity) {
        return new CardDTO()
                .setId(entity.getId())
                .setName(entity.getName())
                .setBin(entity.getBin())
                .setIsActive(entity.isActive());
    }

    public static CardDTO toCardDTO(UpdateCardRequest request, Long cardId) {
        return new CardDTO()
                .setId(cardId)
                .setName(request.getName())
                .setBin(request.getBin())
                .setIsActive(request.getIsActive());
    }
}
