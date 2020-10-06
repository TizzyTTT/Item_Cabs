package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

/**
 * @Description
 * @Author  wwhh
 * @Date 2020-07-29 22:30:26
 */

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="chemicalsregister")
public class Register  implements Serializable {

	private static final long serialVersionUID =  8161732968165455588L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

	@ManyToOne
   	@JoinColumn(name = "chemicalid" )
	private Chemicals chemicals;

   	@Column(name = "totalweight" )
	private double totalweight;

   	@Column(name = "inweight" )
	private double inweight;

   	@Column(name = "reg_date" )
	private Date regDate;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotalweight() {
		return this.totalweight;
	}

	public void setTotalweight(double totalweight) {
		this.totalweight = totalweight;
	}

	public double getInweight() {
		return this.inweight;
	}

	public void setInweight(double inweight) {
		this.inweight = inweight;
	}

	public Date getRegDate() {
		return this.regDate;
	}

	public Chemicals getChemicals() {
		return chemicals;
	}

	public void setChemicals(Chemicals chemicals) {
		this.chemicals = chemicals;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"chemicalid='" + chemicals.getChemicalno() + '\'' +
					"totalweight='" + totalweight + '\'' +
					"inweight='" + inweight + '\'' +
					"regDate='" + regDate + '\'' +
				'}';
	}

}
