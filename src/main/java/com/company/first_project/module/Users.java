package com.company.first_project.module;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "users")
public class Users extends BaseEntity {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Integer age;
    @OneToMany(
            mappedBy = "userId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    protected List<Card> cardList;
    @OneToMany(
            mappedBy = "userId",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Products> productsList;


}
