package com.javaschool.logistic.models;


import java.io.Serializable;

public class OutgoingMessage implements Serializable {

    public enum Status implements Serializable{
        REJECTED,
        OK,
        SHIPPED,
        DELIVERED
    }

    private int id;
    private Status status;

    public OutgoingMessage(int id, Status status) {
        this.id = id;
        this.status = status;
    }

    public OutgoingMessage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OutgoingMessage{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}
