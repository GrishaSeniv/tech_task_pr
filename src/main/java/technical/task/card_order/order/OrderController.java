package technical.task.card_order.order;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import technical.task.card_order.domain.model.CardNumberGenerateResponse;
import technical.task.card_order.domain.model.CreateOrderRequest;
import technical.task.card_order.domain.model.OrderDTO;
import technical.task.card_order.user.UserInfoDetails;

import java.util.List;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
@RestController
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
@RequestMapping("/api/v1/orders")
class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService service;

    OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders(@RequestParam(defaultValue = "0") int offset,
                                                    @RequestParam(defaultValue = "20") int limit) {
        List<OrderDTO> orders = service.getOrders(offset, limit);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid CreateOrderRequest request,
                                                @AuthenticationPrincipal UserDetails userDetails) {
        try {
            UserInfoDetails userInfoDetails = (UserInfoDetails) userDetails;
            OrderDTO order = service.createOrder(request, userInfoDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        } catch (Exception e) {
            logger.error("Something went wrong, while creating new order", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/generate-card-numbers")
    public ResponseEntity<CardNumberGenerateResponse> generateCardNumbers() {
        logger.info("Processing orders without card number");
        long generatedCards = service.getOrdersCountWithoutCardNumber();
        service.processOrdersWithoutCardNumber();
        logger.info("Processing orders without card number completed. Generated cards: {}", generatedCards);
        return ResponseEntity.ok(new CardNumberGenerateResponse(generatedCards));
    }
}
