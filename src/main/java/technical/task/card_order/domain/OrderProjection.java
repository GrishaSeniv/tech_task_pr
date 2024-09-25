package technical.task.card_order.domain;

import java.time.Instant;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-24
 */
public interface OrderProjection {
    Long getId();

    Instant getCreatedAt();

    String getOperatorFirstName();

    String getOperatorLastName();

    String getOperatorSurname();

    String getFirstName();

    String getLastName();

    String getFirstNameLat();

    String getLastNameLat();

    String getCardName();

    String getCardNumber();
}
