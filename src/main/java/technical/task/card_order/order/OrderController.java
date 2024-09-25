package technical.task.card_order.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import technical.task.card_order.domain.OrderDTO;

import java.util.List;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
@RestController
@RequestMapping("/api/v1/orders")
class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService service;

    OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<OrderDTO>> getOrders(@RequestParam int offset,
                                                    @RequestParam int limit) {
        List<OrderDTO> orders = service.getOrders(offset, limit);
        return ResponseEntity.ok(orders);
    }
}
