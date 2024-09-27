package technical.task.card_order.client;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-24
 */
@Entity
@Table(name = "client")
class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name_lat")
    private String firstNameLat;

    @Column(name = "last_name_lat")
    private String lastNameLat;

    private LocalDate birthday;

    private String phone;

    private String email;

    @Column(name = "created_by")
    private Long createdBy;

    public Long getId() {
        return id;
    }

    public ClientEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ClientEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ClientEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstNameLat() {
        return firstNameLat;
    }

    public ClientEntity setFirstNameLat(String firstNameLat) {
        this.firstNameLat = firstNameLat;
        return this;
    }

    public String getLastNameLat() {
        return lastNameLat;
    }

    public ClientEntity setLastNameLat(String lastNameLat) {
        this.lastNameLat = lastNameLat;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public ClientEntity setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public ClientEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ClientEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public ClientEntity setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstNameLat='" + firstNameLat + '\'' +
                ", lastNameLat='" + lastNameLat + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", createdBy=" + createdBy +
                '}';
    }
}
