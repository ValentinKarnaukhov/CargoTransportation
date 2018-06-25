package com.tsystems.fury.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Goods implements Serializable {

    private static final long serialVersionUID = 3322186557067294220L;

    private int orderId;
    private int weight;
    private String cityTo;

}
