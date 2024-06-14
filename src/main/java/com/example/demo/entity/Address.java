package com.example.demo.entity;


import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = -8805252829034831986L;
    private String street;
    private String suite;
    private String city;
    private String zipcode;

}
