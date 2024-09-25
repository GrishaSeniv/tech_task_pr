package technical.task.card_order.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import technical.task.card_order.domain.ClientDTO;
import technical.task.card_order.domain.ClientProjection;

import java.util.List;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
@Service
class ClientService {
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository repository;

    ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<ClientDTO> getClients(int from, int limit) {
        logger.info("Getting client list, from: {}, limit: {}", from, limit);
        List<ClientProjection> projections = repository.getClients(from, limit);
        List<ClientDTO> clients = Converter.toClientDTOS(projections);
        logger.info("Retrieved clients: {}", clients.size());
        return clients;
    }
}
