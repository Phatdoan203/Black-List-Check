package com.demo.black_list_check.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlackListDTO {
    private int id;
    private String fullName;
    private String citizenIdentificationCard;
    private String identityCard;
    private String passport;
    private String dob;
    private String address;
    private String list;
    private String source;
}
