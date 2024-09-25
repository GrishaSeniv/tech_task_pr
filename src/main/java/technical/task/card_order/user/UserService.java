package technical.task.card_order.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import technical.task.card_order.domain.UserServiceAware;
import technical.task.card_order.domain.model.UserEntity;
import technical.task.card_order.domain.model.UserRegisterRequest;
import technical.task.card_order.domain.type.Role;

import java.util.List;
import java.util.Optional;

import static technical.task.card_order.user.Converter.toEntity;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
@Service
class UserService implements UserDetailsService, UserServiceAware {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final List<Role> DEFAULT_ROLES = List.of(Role.USER);
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository,
                       PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Get user by username: {}", username);
        Optional<UserEntity> user = repository.findByLogin(username);
        return user.map(UserInfoDetails::new)
                .orElseThrow(() -> {
                    String msg = "User not found " + username;
                    logger.error(msg);
                    return new UsernameNotFoundException(msg);
                });
    }

    public void addUser(UserRegisterRequest req) {
        logger.info("Saving user with login: {}", req.getLogin());
        req.setPassword(encoder.encode(req.getPassword()));
        repository.save(toEntity(req, DEFAULT_ROLES));
        logger.info("Successfully saved user with login: {}", req.getLogin());
    }
}
