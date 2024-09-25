package technical.task.card_order.client;

import technical.task.card_order.domain.ClientDTO;
import technical.task.card_order.domain.ClientProjection;

import java.util.List;

import static technical.task.card_order.util.Utils.getFLN;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
class Converter {
    public static List<ClientDTO> toClientDTOS(List<ClientProjection> clientProjections) {
        return clientProjections.stream()
                .map(Converter::toClientDto)
                .toList();
    }

    public static ClientDTO toClientDto(ClientProjection projection) {
        return new ClientDTO()
                .setId(projection.getId())
                .setFirstName(projection.getFirstName())
                .setLastName(projection.getLastName())
                .setFirstNameLat(projection.getFirstNameLat())
                .setLastName(projection.getLastNameLat())
                .setBirthday(projection.getBirthday())
                .setPhone(projection.getPhone())
                .setEmail(projection.getEmail())
                .setOperatorFLN(getFLN(projection.getOperatorFirstName(), projection.getOperatorLastName(), projection.getOperatorSurname()));
    }
}
