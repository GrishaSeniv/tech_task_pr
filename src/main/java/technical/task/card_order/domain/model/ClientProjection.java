package technical.task.card_order.domain.model;

import java.time.LocalDate;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-24
 */
public interface ClientProjection {
    Long getId();

    String getFirstName();

    String getLastName();

    String getFirstNameLat();

    String getLastNameLat();

    LocalDate getBirthday();

    String getPhone();

    String getEmail();

    String getOperatorFirstName();

    String getOperatorLastName();

    String getOperatorSurname();
}
