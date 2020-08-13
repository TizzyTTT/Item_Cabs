package com.gm.wj.New_All.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gm.wj.New_All.entity.Chemicals;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description
 * @Author  wwhh
 * @Date 2020-08-10 22:35:33
 */

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="record_detail")
public class RecordDetail2 implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

   	@Column(name = "recordid" )
	private int recordid;

   	@Column(name = "chemicalid" )
	private int chemicalid;

   	@Column(name = "optype" )
	private int optype;

   	@Column(name = "account" )
	private double account;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRecordid() {
		return this.recordid;
	}

	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}

	public int getChemicalid() {
		return chemicalid;
	}

	public void setChemicalid(int chemicalid) {
		this.chemicalid = chemicalid;
	}

	@Override
	public String toString() {
		return "RecordDetail2{" +
				"id=" + id +
				", recordid=" + recordid +
				", chemicalid=" + chemicalid +
				", optype=" + optype +
				", account=" + account +
				'}';
	}

	public int getOptype() {
		return this.optype;
	}

	public void setOptype(int optype) {
		this.optype = optype;
	}

	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}

}
