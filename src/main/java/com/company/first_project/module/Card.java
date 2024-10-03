package com.company.first_project.module;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "cards")
public class Card extends BaseEntity {

    private String cardType;
    private String cardHolderName;
    @Column(unique = true, nullable = false)
    private String cardNumber;
    private String cardCode;
    private LocalDate expiredDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false)
    private Users users;
    @Column(name = "user_id")
    private Integer userId;






}
