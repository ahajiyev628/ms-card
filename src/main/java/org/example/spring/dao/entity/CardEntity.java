package org.example.spring.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.spring.model.enums.CardStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cards")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pan;

    @Column(name="expire_date")
    private LocalDate expireDate;

    private String cvv;

    private String cardHolder;

    @Enumerated(EnumType.STRING) // declare it is enum with string type so that it will be converted to String while writing to DB
    private CardStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /*
    equals and hashcode methods were overridden manually (without lombok @Data annotations)
    because id is unique and it can be used for comparison of the entity classes
    there is no need to check for other fields which will lead to unnecessary performance issues, otherwise
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardEntity that = (CardEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
