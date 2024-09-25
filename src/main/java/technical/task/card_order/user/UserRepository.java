package technical.task.card_order.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technical.task.card_order.domain.model.UserEntity;

import java.util.Optional;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
@Repository
interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByLogin(String login);
}
