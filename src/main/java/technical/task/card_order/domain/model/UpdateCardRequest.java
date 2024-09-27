package technical.task.card_order.domain.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
public class UpdateCardRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    @Size(min = 6, max = 6)
    private String bin;
    private boolean isActive;

    public String getName() {
        return name;
    }

    public UpdateCardRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getBin() {
        return bin;
    }

    public UpdateCardRequest setBin(String bin) {
        this.bin = bin;
        return this;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public UpdateCardRequest setIsActive(boolean active) {
        isActive = active;
        return this;
    }

    @Override
    public String toString() {
        return "UpdateCardRequest{" +
                "name='" + name + '\'' +
                ", bin='" + bin + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
