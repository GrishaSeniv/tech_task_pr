package technical.task.card_order.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import technical.task.card_order.domain.ClientServiceAware;
import technical.task.card_order.domain.model.ClientDTO;
import technical.task.card_order.domain.model.ClientProjection;
import technical.task.card_order.domain.model.CreateClientRequest;
import technical.task.card_order.domain.model.UpdateClientRequest;
import technical.task.card_order.user.UserInfoDetails;

import java.util.List;

import static technical.task.card_order.client.Converter.toClientDTOS;
import static technical.task.card_order.client.Converter.toClientDto;
import static technical.task.card_order.client.Converter.toEntity;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
@Service
class ClientService implements ClientServiceAware {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository repository;

    ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public long getClientCount() {
        return repository.count();
    }

    public List<ClientDTO> getClients(int from, int limit) {
        logger.info("Getting client list, from: {}, limit: {}", from, limit);
        List<ClientProjection> projections = repository.getClients(from, limit);
        List<ClientDTO> clients = toClientDTOS(projections);
        logger.info("Retrieved clients: {}", clients.size());
        return clients;
    }

    public ClientDTO getClient(Long id) {
        logger.info("Getting client by id: {}", id);
        ClientProjection projection = repository.getClient(id);
        if (projection == null) {
            String msg = String.format("Client with id: %s not found", id);
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }
        ClientDTO client = toClientDto(projection);
        logger.info("Retrieved client: {}", client);
        return client;
    }

    public ClientDTO addClient(CreateClientRequest request, UserInfoDetails operator) {
        logger.info("Adding client: {}", request);
        ClientEntity entity = toEntity(request, operator.getId());
        repository.save(entity);
        logger.info("Client added: {}", entity);
        return toClientDto(entity, operator);
    }

    @Transactional
    public ClientDTO updateClient(Long clientId, UpdateClientRequest request) {
        logger.info("Updating client: {}", request);
        repository.updateClient(request.getFirstName(), request.getLastName(), request.getFirstNameLat(),
                request.getLastNameLat(), request.getBirthday(), request.getPhone(), request.getEmail(), clientId);
        ClientDTO dto = toClientDto(request, clientId);
        logger.info("Client updated: {}", dto);
        return dto;
    }
}
