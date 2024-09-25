package technical.task.card_order.domain;

import java.util.Objects;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-24
 */
public class CardDTO {
    private Long id;
    private String name;
    private String bin;
    private boolean isActive;
    private String operatorFLN;

    public Long getId() {
        return id;
    }

    public CardDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CardDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getBin() {
        return bin;
    }

    public CardDTO setBin(String bin) {
        this.bin = bin;
        return this;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public CardDTO setIsActive(boolean active) {
        isActive = active;
        return this;
    }

    public String getOperatorFLN() {
        return operatorFLN;
    }

    public CardDTO setOperatorFLN(String operatorFLN) {
        this.operatorFLN = operatorFLN;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDTO cardDTO = (CardDTO) o;
        return Objects.equals(id, cardDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CardDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bin='" + bin + '\'' +
                ", isActive=" + isActive +
                ", operatorFLN='" + operatorFLN + '\'' +
                '}';
    }
}
