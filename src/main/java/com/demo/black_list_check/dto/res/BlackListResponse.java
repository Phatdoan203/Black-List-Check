package com.demo.black_list_check.dto.res;

import com.demo.black_list_check.entity.BlackList;

import java.util.List;

public class BlackListResponse {
    private String message;
    private List<BlackList> data;
    public BlackListResponse(String message, List<BlackList> data) {
        this.message = message;
        this.data = data;
    }
    // Getters and Setters
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public List<BlackList> getData() {
        return data;
    }
    public void setData(List<BlackList> data) {
        this.data = data;
    }
}
