package com.gm.wj.New_All.utils;


import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gm.wj.New_All.entity.Cabinets;
import com.gm.wj.entity.User;

/**
 * @Description
 * @Author  wwhh
 * @Date 2020-08-14 15:23:31
 */

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="cab_user")
public class CabUser2 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private int id;

    @Column(name = "cabid" )
    private int cabid;

    @Column(name = "userid" )
    private int userid;

    @Column(name = "p_open" )
    private int pOpen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCabid() {
        return cabid;
    }

    public void setCabid(int cabid) {
        this.cabid = cabid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getpOpen() {
        return pOpen;
    }

    public void setpOpen(int pOpen) {
        this.pOpen = pOpen;
    }

    public int getpOut() {
        return pOut;
    }

    public void setpOut(int pOut) {
        this.pOut = pOut;
    }

    public int getpIn() {
        return pIn;
    }

    public void setpIn(int pIn) {
        this.pIn = pIn;
    }

    @Column(name = "p_out" )
    private int pOut;

    @Column(name = "p_in" )
    private int pIn;

}
