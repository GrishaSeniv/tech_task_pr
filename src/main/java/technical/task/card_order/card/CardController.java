package technical.task.card_order.card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import technical.task.card_order.domain.model.CardDTO;
import technical.task.card_order.domain.model.ClientDTO;

import java.util.List;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
@RestController
@RequestMapping("/api/v1/cards")
class CardController {
    private static final Logger logger = LoggerFactory.getLogger(CardController.class);
    private final CardService service;

    CardController(CardService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<CardDTO>> getCards(@RequestParam int offset,
                                                  @RequestParam int limit) {
        List<CardDTO> cards = service.getCards(offset, limit);
        return ResponseEntity.ok(cards);
    }
}
