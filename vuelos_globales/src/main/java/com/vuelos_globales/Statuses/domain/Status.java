package com.vuelos_globales.Statuses.domain;

public class Status {
    private String id;
    private String status;

    public Status(){}

    public Status(String id, String status) {
        this.id = id;
        this.status = status;
    }

    // Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}