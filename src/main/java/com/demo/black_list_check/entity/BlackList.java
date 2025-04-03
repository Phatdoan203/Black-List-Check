package com.demo.black_list_check.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "BlackList")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlackList {
    @Id
    private int id;
    @NotNull
    private String fullName;
    private String citizen_identification_card;
    private String identity_card;
    private String passport;
    private String dob;
    private String address;
    private String list;
    private String source;
}
