package technical.task.card_order.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technical.task.card_order.domain.OrderServiceAware;
import technical.task.card_order.domain.model.CardBinProjectionDTO;
import technical.task.card_order.domain.model.CreateOrderRequest;
import technical.task.card_order.domain.model.OrderDTO;
import technical.task.card_order.domain.model.OrderProjection;
import technical.task.card_order.user.UserInfoDetails;
import technical.task.card_order.util.CardNumberGenerator;

import java.util.List;

import static technical.task.card_order.order.Converter.toEntity;
import static technical.task.card_order.order.Converter.toOrderDTOS;
import static technical.task.card_order.order.Converter.toOrderDto;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-24
 */
@Service
class OrderService implements OrderServiceAware {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository repository;
    private final CardNumberGenerator cardNumberGenerator;

    OrderService(OrderRepository repository,
                 CardNumberGenerator cardNumberGenerator) {
        this.repository = repository;
        this.cardNumberGenerator = cardNumberGenerator;
    }

    @Override
    public long getOrdersCount() {
        return repository.count();
    }

    public List<OrderDTO> getOrders(int from, int limit) {
        logger.info("Get orders, from: {}, limit: {}", from, limit);
        List<OrderProjection> projections = repository.getOrders(from, limit);
        List<OrderDTO> orders = toOrderDTOS(projections);
        logger.info("Retrieved orders: {}", orders.size());
        return orders;
    }

    @Transactional
    public OrderDTO createOrder(CreateOrderRequest request, UserInfoDetails operator) {
        Long operatorId = operator.getId();
        logger.info("Creating order by req: {}, by operator with id: {}", request, operatorId);
        OrderEntity entity = toEntity(request, operatorId);
        repository.save(entity);
        logger.info("Order created: {}", entity);
        OrderProjection projection = repository.getOrder(entity.getId());
        return toOrderDto(projection);
    }

    public long getOrdersCountWithoutCardNumber() {
        return repository.countOrdersWithoutCardNumber();
    }

    @Transactional
    public void processOrdersWithoutCardNumber() {
        List<CardBinProjectionDTO> orders = repository.findOrdersWithoutCardNumber();
        if (orders.isEmpty()) {
            return;
        }
        processOrdersWithoutCardNumberRetry(orders);
        processOrdersWithoutCardNumber();
    }

    private void processOrdersWithoutCardNumberRetry(List<CardBinProjectionDTO> orders) {
        List<CardBinProjectionDTO> updatedOrders = orders.stream()
                .peek(order -> {
                    String bin = order.getCardBin();
                    String cardNumber = cardNumberGenerator.generateUniqueCardNumber(bin);
                    order.setCardNumber(cardNumber);
                })
                .toList();

        updateCardNumber(updatedOrders);
    }

    private void updateCardNumber(List<CardBinProjectionDTO> updatedOrders) {
        updatedOrders.forEach(order -> repository.updateCardNumber(order.getCardNumber(), order.getOrderId()));
    }
}
