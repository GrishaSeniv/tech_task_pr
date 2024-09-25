package technical.task.card_order.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import technical.task.card_order.domain.model.OrderDTO;
import technical.task.card_order.domain.model.OrderProjection;

import java.util.List;

import static technical.task.card_order.order.Converter.*;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-24
 */
@Service
class OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository repository;

    OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    // TODO add sorting
    public List<OrderDTO> getOrders(int from, int limit) {
        logger.info("Get orders, from: {}, limit: {}", from, limit);
        List<OrderProjection> projections = repository.getOrders(from, limit);
        List<OrderDTO> orders = toOrderDTOS(projections);
        logger.info("Retrieved orders: {}", orders.size());
        return orders;
    }
}
