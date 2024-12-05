package com.example.test.domain;

import java.io.Serializable;
import java.util.UUID;

public abstract class BaseEntity implements Serializable {
    protected Integer id;

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }



    public String getForDb() {
        return this.id.toString();
    }

    public void setId(String toString) {
        this.id = Integer.valueOf(toString);
    }
}
