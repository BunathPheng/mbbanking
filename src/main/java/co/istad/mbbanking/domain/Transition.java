package co.istad.mbbanking.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "transitions")
public class Transition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Account owner;
    @ManyToOne
    private Account receiver;
    private String  paymentReceiver;  // STUDENTCARDID
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private  Boolean status;
    @Column(nullable = false)
    private LocalDateTime transitionDate;
    @Column(nullable = false , length = 30)
    private String  transitionType;  //PAYMENT , TRANSFER
    private Boolean isDeleted;
}
