package technical.task.card_order.domain;

import org.springframework.security.core.userdetails.UserDetails;
import technical.task.card_order.domain.model.UserRegisterRequest;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
public interface UserServiceAware {
    UserDetails loadUserByUsername(String username);

    void addUser(UserRegisterRequest req);
}
