package technical.task.card_order.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import technical.task.card_order.domain.model.CardProjection;

import java.util.List;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
@Repository
interface CardRepository extends JpaRepository<CardEntity, Long> {
    @Query(
            value = """
                    SELECT c.id,
                           c.name,
                           c.bin,
                           c.is_active as isActive,
                           u.first_name as operatorFirstName,
                           u.last_name as operatorLastName,
                           u.surname as operatorSurname
                    FROM card c
                    INNER JOIN `user` u ON c.edited_by = u.id
                    ORDER BY c.id DESC
                    LIMIT :limit
                    OFFSET :offset
                    """, nativeQuery = true)
    List<CardProjection> getCards(@Param("offset") int offset,
                                  @Param("limit") int limit);
}
