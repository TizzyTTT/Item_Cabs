package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;

/**
 * @Description  
 * @Author  wwhh
 * @Date 2020-07-29 21:34:44 
 */

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="chemicalsregister")
public class Chemicalsregister  implements Serializable {

	private static final long serialVersionUID =  4109995474530696637L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

   	@Column(name = "chemicalno" )
	private String chemicalno;

   	@Column(name = "chemicalname" )
	private String chemicalname;

   	@Column(name = "totalweight" )
	private BigDecimal totalweight;

   	@Column(name = "inweight" )
	private BigDecimal inweight;

   	@Column(name = "chemicalid" )
	private int chemicalid;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChemicalno() {
		return this.chemicalno;
	}

	public void setChemicalno(String chemicalno) {
		this.chemicalno = chemicalno;
	}

	public String getChemicalname() {
		return this.chemicalname;
	}

	public void setChemicalname(String chemicalname) {
		this.chemicalname = chemicalname;
	}

	public BigDecimal getTotalweight() {
		return this.totalweight;
	}

	public void setTotalweight(BigDecimal totalweight) {
		this.totalweight = totalweight;
	}

	public BigDecimal getInweight() {
		return this.inweight;
	}

	public void setInweight(BigDecimal inweight) {
		this.inweight = inweight;
	}

	public int getChemicalid() {
		return this.chemicalid;
	}

	public void setChemicalid(int chemicalid) {
		this.chemicalid = chemicalid;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"chemicalno='" + chemicalno + '\'' +
					"chemicalname='" + chemicalname + '\'' +
					"totalweight='" + totalweight + '\'' +
					"inweight='" + inweight + '\'' +
					"chemicalid='" + chemicalid + '\'' +
				'}';
	}

}
