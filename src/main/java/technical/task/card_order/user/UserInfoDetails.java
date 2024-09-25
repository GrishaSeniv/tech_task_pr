package technical.task.card_order.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import technical.task.card_order.domain.model.UserEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static technical.task.card_order.util.JsonUtils.fromJson;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
class UserInfoDetails implements UserDetails {
    private final Long id;
    private final String login;
    private final String password;
    private List<GrantedAuthority> authorities = Collections.emptyList();

    public UserInfoDetails(UserEntity entity) {
        id = entity.getId();
        login = entity.getLogin();
        password = entity.getPassword();
        String[] roles = fromJson(entity.getRoles(), String[].class);
        if (roles == null) {
            return;
        }
        authorities = Arrays.stream(roles)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    public Long getId() {
        return id;
    }
}
