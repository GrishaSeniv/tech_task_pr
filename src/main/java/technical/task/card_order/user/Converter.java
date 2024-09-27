package technical.task.card_order.user;

import technical.task.card_order.domain.model.UserDTO;
import technical.task.card_order.domain.model.UserEntity;
import technical.task.card_order.domain.model.UserRegisterRequest;
import technical.task.card_order.domain.type.Role;

import java.util.Arrays;
import java.util.List;

import static technical.task.card_order.util.JsonUtils.fromJson;
import static technical.task.card_order.util.JsonUtils.toJson;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
class Converter {
    static UserEntity toEntity(UserRegisterRequest req, List<Role> roles) {
        return new UserEntity()
                .setLogin(req.getLogin())
                .setPassword(req.getPassword())
                .setFirstName(req.getFirstName())
                .setLastName(req.getLastName())
                .setSurname(req.getSurname())
                .setRoles(toRolesStrJson(roles));
    }

    static UserDTO toUserDTO(UserEntity entity) {
        String[] roleArray = fromJson(entity.getRoles(), String[].class);
        return new UserDTO()
                .setId(entity.getId())
                .setLogin(entity.getLogin())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setSurname(entity.getSurname())
                .setRoles(roleArray == null ? null : Arrays.asList(roleArray));
    }

    private static String toRolesStrJson(List<Role> roles) {
        List<String> rolesStr = roles.stream()
                .map(Role::name)
                .toList();
        return toJson(rolesStr);
    }
}
