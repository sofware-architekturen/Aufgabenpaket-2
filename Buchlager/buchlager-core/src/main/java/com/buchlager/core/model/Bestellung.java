package com.buchlager.core.model;

import java.io.Serializable;
import java.util.Objects;

public class Bestellung implements Serializable {

    private static final long serialVersionUID = 1L;

    private int buchId;
    private int orderAmount;

    public Bestellung(int buchId, int orderAmount) {
        this.buchId = buchId;
        this.orderAmount = orderAmount;
    }

    public int getBuchId() {
        return buchId;
    }

    public void setBuchId(int buchId) {
        this.buchId = buchId;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bestellung that = (Bestellung) o;
        return buchId == that.buchId && orderAmount == that.orderAmount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buchId, orderAmount);
    }

    @Override
    public String toString() {
        return "Bestellung{" +
                "Buch-Id: " + buchId +
                ", Bestellanzahl: " + orderAmount +
                '}';
    }
}
