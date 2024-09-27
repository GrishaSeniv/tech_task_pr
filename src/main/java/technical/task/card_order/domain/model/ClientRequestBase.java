package technical.task.card_order.domain.model;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public abstract class ClientRequestBase {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String firstNameLat;
    @NotEmpty
    private String lastNameLat;
    private LocalDate birthday;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String email;

    public @NotEmpty String getFirstName() {
        return firstName;
    }

    public ClientRequestBase setFirstName(@NotEmpty String firstName) {
        this.firstName = firstName;
        return this;
    }

    public @NotEmpty String getLastName() {
        return lastName;
    }

    public ClientRequestBase setLastName(@NotEmpty String lastName) {
        this.lastName = lastName;
        return this;
    }

    public @NotEmpty String getFirstNameLat() {
        return firstNameLat;
    }

    public ClientRequestBase setFirstNameLat(@NotEmpty String firstNameLat) {
        this.firstNameLat = firstNameLat;
        return this;
    }

    public @NotEmpty String getLastNameLat() {
        return lastNameLat;
    }

    public ClientRequestBase setLastNameLat(@NotEmpty String lastNameLat) {
        this.lastNameLat = lastNameLat;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public ClientRequestBase setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public @NotEmpty String getPhone() {
        return phone;
    }

    public ClientRequestBase setPhone(@NotEmpty String phone) {
        this.phone = phone;
        return this;
    }

    public @NotEmpty String getEmail() {
        return email;
    }

    public ClientRequestBase setEmail(@NotEmpty String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "ClientRequestBase{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstNameLat='" + firstNameLat + '\'' +
                ", lastNameLat='" + lastNameLat + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
