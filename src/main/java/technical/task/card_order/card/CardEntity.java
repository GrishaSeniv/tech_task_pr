package technical.task.card_order.card;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "card")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String bin;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(name = "edited_by")
    private Long editedBy;

    public Long getId() {
        return id;
    }

    public CardEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CardEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getBin() {
        return bin;
    }

    public CardEntity setBin(String bin) {
        this.bin = bin;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public CardEntity setActive(boolean active) {
        isActive = active;
        return this;
    }

    public Long getEditedBy() {
        return editedBy;
    }

    public CardEntity setEditedBy(Long editedBy) {
        this.editedBy = editedBy;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardEntity that = (CardEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CardEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bin='" + bin + '\'' +
                ", isActive=" + isActive +
                ", editedBy=" + editedBy +
                '}';
    }
}
