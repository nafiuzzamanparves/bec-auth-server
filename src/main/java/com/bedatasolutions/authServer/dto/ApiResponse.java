package com.bedatasolutions.authServer.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ApiResponse<T> {

    private String status;  // "success" or "error"
    private String message; // A message like "Operation successful" or error details
    private T data;         // The actual data (can be any type)
    private String timestamp; // Formatted timestamp field

    // Constructors
    public ApiResponse() {
        this.timestamp = getCurrentTimestamp();
    }

    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = getCurrentTimestamp();
    }

    // Utility method to get the current date formatted as dd/MM/yyyy
    private String getCurrentTimestamp() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return now.format(formatter);
    }

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // Utility methods to return common responses
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>("success", message, data);
    }

    public static <T> ApiResponse<T> success( String message) {
        return new ApiResponse<>("success", message, null);
    }

    public static <T> ApiResponse<T> failed(String message) {
        return new ApiResponse<>("failed", message, null);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>("error", message, null);
    }
}