package com.gm.wj.New_All.utils;

import java.util.Date;

public class Chem {

    int chemicalid;
    double amount;
    Date time;


    public int getChemicalid() {
        return chemicalid;
    }

    public void setChemicalid(int chemicalid) {
        this.chemicalid = chemicalid;
    }


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Chem{" +
                "chemicalid=" + chemicalid +
                ", amount=" + amount +
                ", time=" + time +
                '}';
    }
}
