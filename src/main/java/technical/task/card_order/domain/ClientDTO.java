package technical.task.card_order.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-24
 */
public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String firstNameLat;
    private String lastNameLat;
    private LocalDate birthday;
    private String phone;
    private String email;
    private String operatorFLN;

    public Long getId() {
        return id;
    }

    public ClientDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ClientDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ClientDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstNameLat() {
        return firstNameLat;
    }

    public ClientDTO setFirstNameLat(String firstNameLat) {
        this.firstNameLat = firstNameLat;
        return this;
    }

    public String getLastNameLat() {
        return lastNameLat;
    }

    public ClientDTO setLastNameLat(String lastNameLat) {
        this.lastNameLat = lastNameLat;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public ClientDTO setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public ClientDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ClientDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getOperatorFLN() {
        return operatorFLN;
    }

    public ClientDTO setOperatorFLN(String operatorFLN) {
        this.operatorFLN = operatorFLN;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDTO clientDTO = (ClientDTO) o;
        return Objects.equals(id, clientDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstNameLat='" + firstNameLat + '\'' +
                ", lastNameLat='" + lastNameLat + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", operatorFLN='" + operatorFLN + '\'' +
                '}';
    }
}
