package technical.task.card_order.client;

import technical.task.card_order.domain.model.ClientDTO;
import technical.task.card_order.domain.model.ClientProjection;
import technical.task.card_order.domain.model.ClientRequestBase;
import technical.task.card_order.domain.model.CreateClientRequest;
import technical.task.card_order.domain.model.UpdateClientRequest;
import technical.task.card_order.user.UserInfoDetails;

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
                .setLastNameLat(projection.getLastNameLat())
                .setBirthday(projection.getBirthday())
                .setPhone(projection.getPhone())
                .setEmail(projection.getEmail())
                .setOperatorFLN(getFLN(projection.getOperatorFirstName(), projection.getOperatorLastName(), projection.getOperatorSurname()));
    }

    public static ClientDTO toClientDto(ClientEntity entity, UserInfoDetails operator) {
        return toClientDto(entity)
                .setOperatorFLN(getFLN(operator.getFirstName(), operator.getLastName(), operator.getSurname()));
    }

    public static ClientDTO toClientDto(UpdateClientRequest request, Long clientId) {
        return new ClientDTO()
                .setId(clientId)
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setFirstNameLat(request.getFirstNameLat())
                .setLastNameLat(request.getLastNameLat())
                .setBirthday(request.getBirthday())
                .setPhone(request.getPhone())
                .setEmail(request.getEmail());
    }

    public static ClientDTO toClientDto(ClientEntity entity) {
        return new ClientDTO()
                .setId(entity.getId())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setFirstNameLat(entity.getFirstNameLat())
                .setLastNameLat(entity.getLastNameLat())
                .setBirthday(entity.getBirthday())
                .setPhone(entity.getPhone())
                .setEmail(entity.getEmail());
    }

    public static ClientEntity toEntity(CreateClientRequest clientRequest, Long operatorId) {
        return createClientEntity(clientRequest, operatorId);
    }

    public static ClientEntity toEntity(UpdateClientRequest clientRequest) {
        return createClientEntity(clientRequest);
    }

    private static ClientEntity createClientEntity(ClientRequestBase clientRequest, Long operatorId) {
        return createClientEntity(clientRequest)
                .setCreatedBy(operatorId);
    }

    private static ClientEntity createClientEntity(ClientRequestBase clientRequest) {
        return new ClientEntity()
                .setFirstName(clientRequest.getFirstName())
                .setLastName(clientRequest.getLastName())
                .setFirstNameLat(clientRequest.getFirstNameLat())
                .setLastNameLat(clientRequest.getLastNameLat())
                .setBirthday(clientRequest.getBirthday())
                .setPhone(clientRequest.getPhone())
                .setEmail(clientRequest.getEmail());
    }
}
