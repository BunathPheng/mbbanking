package co.istad.mbbanking.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cards")
public class Card {
    @Id
    private Integer id;
    @Column(nullable = false , unique = true)
    private Integer cvv;
    @Column(length = 50 , nullable = false , unique = true)
    private String number;
    @Column(length = 50 , nullable = false)
    private String holder;
    private LocalDate expiryAt;
    private LocalDate issueAt;
    private Boolean is_deleted;
    @ManyToOne
    private CardType cardType;
    @OneToOne(mappedBy = "card")
    private Account account;
}
