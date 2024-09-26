package technical.task.card_order.order;

import technical.task.card_order.domain.model.CreateOrderRequest;
import technical.task.card_order.domain.model.OrderDTO;
import technical.task.card_order.domain.model.OrderProjection;

import java.time.Instant;
import java.util.List;

import static technical.task.card_order.util.Utils.getFLN;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-24
 */
class Converter {
    public static List<OrderDTO> toOrderDTOS(List<OrderProjection> orderProjections) {
        return orderProjections.stream()
                .map(Converter::toOrderDto)
                .toList();
    }

    public static OrderDTO toOrderDto(OrderProjection projection) {
        return new OrderDTO()
                .setId(projection.getId())
                .setCreatedAt(projection.getCreatedAt())
                .setFirstName(projection.getFirstName())
                .setLastName(projection.getLastName())
                .setFirstNameLat(projection.getFirstNameLat())
                .setLastNameLat(projection.getLastNameLat())
                .setOperatorFLN(getFLN(projection.getOperatorFirstName(), projection.getOperatorLastName(), projection.getOperatorSurname()))
                .setCardName(projection.getCardName())
                .setCardNumber(projection.getCardNumber());
    }

    public static OrderEntity toEntity(CreateOrderRequest request, Long operatorId) {
        return new OrderEntity()
                .setCreatedAt(Instant.now())
                .setClientId(request.getClientId())
                .setCardId(request.getCardId())
                .setCreatedBy(operatorId);
    }
}
