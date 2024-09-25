package technical.task.card_order.domain.model;

import jakarta.validation.constraints.NotEmpty;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
public class UserRegisterRequest {
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String surname;

    public @NotEmpty String getLogin() {
        return login;
    }

    public UserRegisterRequest setLogin(@NotEmpty String login) {
        this.login = login;
        return this;
    }

    public @NotEmpty String getPassword() {
        return password;
    }

    public UserRegisterRequest setPassword(@NotEmpty String password) {
        this.password = password;
        return this;
    }

    public @NotEmpty String getFirstName() {
        return firstName;
    }

    public UserRegisterRequest setFirstName(@NotEmpty String firstName) {
        this.firstName = firstName;
        return this;
    }

    public @NotEmpty String getLastName() {
        return lastName;
    }

    public UserRegisterRequest setLastName(@NotEmpty String lastName) {
        this.lastName = lastName;
        return this;
    }

    public @NotEmpty String getSurname() {
        return surname;
    }

    public UserRegisterRequest setSurname(@NotEmpty String surname) {
        this.surname = surname;
        return this;
    }

    @Override
    public String toString() {
        return "UserRegisterRequest{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
