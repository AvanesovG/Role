package com.example.roles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public class ResultDTO {
    boolean success;
    List<String> errors;

    public ResultDTO() {
    }

    public ResultDTO(boolean success, List<String> errors) {
        this.success = success;
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public static ResultDTO successTrue() {
        return new ResultDTO(true, null);
    }

    public static ResultDTO successFalse(List<String> errors) {
        return new ResultDTO(false, errors);
    }

}
