package technical.task.card_order.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import technical.task.card_order.domain.model.CardBinProjectionDTO;
import technical.task.card_order.domain.model.OrderProjection;

import java.util.List;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-24
 */
@Repository
interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query(
            value = """
                    SELECT o.id,
                           o.created_at as createdAt,
                           o.card_number as cardNumber,
                           u.first_name as operatorFirstName,
                           u.last_name as operatorLastName,
                           u.surname as operatorSurname,
                           cl.first_name as firstName,
                           cl.last_name as lastName,
                           cl.first_name_lat as firstNameLat,
                           cl.last_name_lat as lastNameLat,
                           c.name as cardName
                    FROM `order` o
                    INNER JOIN `user` u ON o.created_by = u.id
                    INNER JOIN card c ON o.card_id = c.id
                    INNER JOIN client cl ON o.client_id = cl.id
                    ORDER BY o.id DESC
                    LIMIT :limit
                    OFFSET :offset
                    """, nativeQuery = true)
    List<OrderProjection> getOrders(@Param("offset") int offset,
                                    @Param("limit") int limit);

    @Query(
            value = """
                    SELECT o.id,
                           o.created_at as createdAt,
                           o.card_number as cardNumber,
                           u.first_name as operatorFirstName,
                           u.last_name as operatorLastName,
                           u.surname as operatorSurname,
                           cl.first_name as firstName,
                           cl.last_name as lastName,
                           cl.first_name_lat as firstNameLat,
                           cl.last_name_lat as lastNameLat,
                           c.name as cardName
                    FROM `order` o
                    INNER JOIN `user` u ON o.created_by = u.id
                    INNER JOIN card c ON o.card_id = c.id
                    INNER JOIN client cl ON o.client_id = cl.id
                    WHERE o.id = :orderId
                    """, nativeQuery = true)
    OrderProjection getOrder(@Param("orderId") Long orderId);

    @Query("""
            SELECT new technical.task.card_order.domain.model.CardBinProjectionDTO(o.id, c.bin)
            FROM OrderEntity o
            INNER JOIN CardEntity c ON o.cardId = c.id
            WHERE o.cardNumber IS NULL
            """)
    Page<CardBinProjectionDTO> findOrdersWithoutCardNumber(Pageable pageable);

    @Query("""
            SELECT new technical.task.card_order.domain.model.CardBinProjectionDTO(o.id, c.bin)
            FROM OrderEntity o
            INNER JOIN CardEntity c ON o.cardId = c.id
            WHERE o.cardNumber IS NULL
            """)
    List<CardBinProjectionDTO> findOrdersWithoutCardNumber();

    @Query("""
            SELECT COUNT (o.id)
            FROM OrderEntity o
            WHERE o.cardNumber IS NULL
            """)
    long countOrdersWithoutCardNumber();

    @Modifying
    @Query(value = """
            UPDATE IGNORE `order`
            SET card_number = :cardNumber
            WHERE `order`.id = :orderId
            """, nativeQuery = true)
    int updateCardNumber(@Param("cardNumber") String cardNumber,
                         @Param("orderId") Long orderId);
}
