package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;

/**
 * @Description
 * @Author  wwhh
 * @Date 2020-08-13 18:01:58
 */

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="cab_item")
public class CabItem  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

   	@Column(name = "cabid" )
	private int cabid;

   	@Column(name = "chemicalid" )
	private int chemicalid;

   	@Column(name = "level" )
	private int level;

   	@Column(name = "loc" )
	private int loc;

   	@Column(name = "amount" )
	private double amount;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCabid() {
		return this.cabid;
	}

	public void setCabid(int cabid) {
		this.cabid = cabid;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLoc() {
		return this.loc;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getChemicalid() {
		return chemicalid;
	}

	public void setChemicalid(int chemicalid) {
		this.chemicalid = chemicalid;
	}
}
