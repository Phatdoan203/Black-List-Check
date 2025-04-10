package com.demo.black_list_check.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class BlackListRequest {
    @NotBlank(message = "fullName is required")
    @Size(min = 4, max = 50, message = "fullName must be between 4 and 50 characters")
    public String fullname;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    public String address;

    @NotBlank(message = "Cid is required")
    @Size(min = 10, message = "Cid must be at least 10 characters")
    public String citizen_identification_card;

    public BlackListRequest(String fullname, String address, String citizen_identification_card) {
        this.fullname = fullname;
        this.address = address;
        this.citizen_identification_card = citizen_identification_card;
    }
}
