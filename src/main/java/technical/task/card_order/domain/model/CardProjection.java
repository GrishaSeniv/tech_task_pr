package technical.task.card_order.domain.model;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-24
 */
public interface CardProjection {
    Long getId();

    String getName();

    String getBin();

    boolean getIsActive();

    String getOperatorFirstName();

    String getOperatorLastName();

    String getOperatorSurname();
}
