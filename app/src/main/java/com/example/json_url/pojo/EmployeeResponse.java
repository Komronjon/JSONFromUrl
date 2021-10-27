package com.example.json_url.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeResponse {
    @SerializedName("response")
    @Expose
    private List<Emploee> response = null;

    public List<Emploee> getResponse() {
        return response;
    }

    public void setResponse(List<Emploee> response) {
        this.response = response;
    }
}
