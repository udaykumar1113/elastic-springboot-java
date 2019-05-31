package com.uday.indexsearch.rest.domain;

public class ErrorResponse {
    private String message;
    private Long timestamp;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, Long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
