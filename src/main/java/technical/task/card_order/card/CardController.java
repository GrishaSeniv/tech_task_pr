package technical.task.card_order.card;

import jakarta.validation.Valid;
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
import technical.task.card_order.domain.model.CardDTO;
import technical.task.card_order.domain.model.CreateCardRequest;
import technical.task.card_order.domain.model.UpdateCardRequest;
import technical.task.card_order.user.UserInfoDetails;

import java.util.List;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
@RestController
@RequestMapping("/api/v1/cards")
@PreAuthorize("hasAnyAuthority('ADMIN')")
class CardController {
    private static final Logger logger = LoggerFactory.getLogger(CardController.class);
    private final CardService service;

    CardController(CardService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CardDTO>> getCards(@RequestParam(defaultValue = "0") int offset,
                                                  @RequestParam(defaultValue = "20") int limit,
                                                  @RequestParam(required = false) Boolean isActive) {
        List<CardDTO> cards = service.getCards(offset, limit, isActive);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> getCard(@PathVariable Long id) {
        CardDTO cardDTO = service.getCard(id);
        return ResponseEntity.ok(cardDTO);
    }

    @PostMapping
    public ResponseEntity<CardDTO> addCard(@RequestBody @Valid CreateCardRequest request,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        try {
            UserInfoDetails userInfoDetails = (UserInfoDetails) userDetails;
            CardDTO cardDTO = service.addCard(request, userInfoDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(cardDTO);
        } catch (Exception e) {
            logger.error("Something went wrong, while adding new card", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CardDTO> updateCard(@PathVariable Long id,
                                              @RequestBody @Valid UpdateCardRequest request,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        try {
            UserInfoDetails userInfoDetails = (UserInfoDetails) userDetails;
            CardDTO card = service.updateCard(id, request, userInfoDetails);
            return ResponseEntity.status(HttpStatus.OK).body(card);
        } catch (Exception e) {
            logger.error("Something went wrong, while updating card", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
