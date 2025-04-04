package com.demo.black_list_check.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "black_list")
public class BlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "citizen_identification_card")
    private String citizen_identification_card;
    @Column(name = "identity_card")
    private String identity_card;
    @Column(name = "passport")
    private String passport;
    @Column(name = "dob")
    private String dob;
    @Column(name = "address")
    private String address;
    @Column(name = "list")
    private String list;
    @Column(name = "source")
    private String source;
}
