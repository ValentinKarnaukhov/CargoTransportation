package com.tsystems.fury.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class GoodsInfo implements Serializable{

    private static final long serialVersionUID = -3147943338478953248L;

    public enum Status implements Serializable{
        REJECTED,
        OK,
        SHIPPED,
        DELIVERED
    }

    private int orderId;
    private Status status;

}
