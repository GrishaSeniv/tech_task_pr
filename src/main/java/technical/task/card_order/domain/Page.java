package technical.task.card_order.domain;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-24
 */
public class Page {
    private int from;
    private int limit;

    public int getFrom() {
        return from;
    }

    public Page setFrom(int from) {
        this.from = from;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public Page setLimit(int limit) {
        this.limit = limit;
        return this;
    }
}
