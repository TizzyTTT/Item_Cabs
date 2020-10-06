package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description
 * @Author  wwhh
 * @Date 2020-07-29 21:34:44
 */
@Data
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="chemicalsregister")
public class Chemicalsregister{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

   	@Column(name = "totalweight" )
	private double totalweight;

   	@Column(name = "inweight" )
	private double inweight;

	@OneToOne
	@JoinColumn(name = "chemicalid")
	private Chemicals chemicals;

	@Column(name = "reg_date")
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotalweight() {
		return totalweight;
	}

	public void setTotalweight(double totalweight) {
		this.totalweight = totalweight;
	}

	public double getInweight() {
		return inweight;
	}

	public void setInweight(double inweight) {
		this.inweight = inweight;
	}

	public Chemicals getChemicals() {
		return chemicals;
	}

	public void setChemicals(Chemicals chemicals) {
		this.chemicals = chemicals;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
