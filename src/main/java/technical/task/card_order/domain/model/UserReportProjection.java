package technical.task.card_order.domain.model;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-26
 */
public interface UserReportProjection {
    String getLogin();

    String getFullName();

    int getOrdersCount();
}
