package technical.task.card_order.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import technical.task.card_order.domain.model.ClientDTO;
import technical.task.card_order.domain.model.CreateClientRequest;
import technical.task.card_order.domain.model.UpdateClientRequest;
import technical.task.card_order.user.UserInfoDetails;

import java.util.List;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
@RestController
@RequestMapping("/api/v1/clients")
@PreAuthorize("hasAnyAuthority('USER')")
class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientService service;

    ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getClients(@RequestParam(defaultValue = "0") int offset,
                                                      @RequestParam(defaultValue = "20") int limit) {
        List<ClientDTO> clients = service.getClients(offset, limit);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long id) {
        ClientDTO client = service.getClient(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> addClient(@RequestBody CreateClientRequest request,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        try {
            UserInfoDetails userInfoDetails = (UserInfoDetails) userDetails;
            ClientDTO client = service.addClient(request, userInfoDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(client);
        } catch (Exception e) {
            logger.error("Something went wrong, while adding new client", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id,
                                                  @RequestBody UpdateClientRequest request) {
        try {
            ClientDTO client = service.updateClient(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(client);
        } catch (Exception e) {
            logger.error("Something went wrong, while updating client", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
