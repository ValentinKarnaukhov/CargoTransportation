package com.tsystems.fury.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {

    private static final long serialVersionUID = 3322186557067294220L;

    private int orderId;
    private int weight;
    private String cityTo;

}
