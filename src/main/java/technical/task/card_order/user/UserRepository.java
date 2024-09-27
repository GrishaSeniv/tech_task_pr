package technical.task.card_order.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import technical.task.card_order.domain.model.UserEntity;
import technical.task.card_order.domain.model.UserReportProjection;

import java.util.List;
import java.util.Optional;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
@Repository
interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String login);

    @Query(value = """
            SELECT u.login,
                   CONCAT(u.first_name, ' ', u.last_name, ' ', u.surname) AS fullName,
                   COUNT(o.id) as ordersCount
            FROM `user` u
                     LEFT JOIN
                 `order` o ON u.id = o.created_by
                     AND MONTH(o.created_at) = MONTH(CURRENT_DATE())
                     AND YEAR(o.created_at) = YEAR(CURRENT_DATE())
            GROUP BY u.id
            ORDER BY ordersCount DESC,
                     fullName;
            """, nativeQuery = true)
    List<UserReportProjection> getUserReportForCurrentMonth();
}
