package technical.task.card_order.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import technical.task.card_order.domain.model.ClientDTO;

import java.util.List;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
@RestController
@RequestMapping("/api/v1/clients")
class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientService service;

    ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<List<ClientDTO>> getOrders(@RequestParam int offset,
                                                     @RequestParam int limit) {
        List<ClientDTO> clients = service.getClients(offset, limit);
        return ResponseEntity.ok(clients);
    }
}
