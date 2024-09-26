package technical.task.card_order.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
                    LEFT JOIN `user` u ON c.edited_by = u.id
                    WHERE :noIsActiveFiltering OR c.is_active = :isActive
                    ORDER BY c.id
                    LIMIT :limit
                    OFFSET :offset
                    """, nativeQuery = true)
    List<CardProjection> getCards(@Param("offset") int offset,
                                  @Param("limit") int limit,
                                  @Param("noIsActiveFiltering") boolean noIsActiveFiltering,
                                  @Param("isActive") boolean isActive);

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
                    LEFT JOIN `user` u ON c.edited_by = u.id
                    WHERE c.id = :cardId
                    """, nativeQuery = true)
    CardProjection getCard(@Param("cardId") Long cardId);

    @Modifying
    @Query(value = """
            UPDATE card
            SET name = :name,
                bin = :bin,
                is_active = :isActive,
                edited_by = :operatorId
            WHERE id = :cardId
            """, nativeQuery = true)
    int updateCard(@Param("name") String name,
                   @Param("bin") String bin,
                   @Param("isActive") boolean isActive,
                   @Param("cardId") Long cardId,
                   @Param("operatorId") Long operatorId);
}
