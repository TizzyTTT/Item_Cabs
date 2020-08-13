package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;

/**
 * @Description
 * @Author  wwhh
 * @Date 2020-08-10 22:35:33
 */

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="record_detail")
public class RecordDetail  implements Serializable {

	private static final long serialVersionUID =  3827896852141656103L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

   	@Column(name = "recordid" )
	private int recordid;

   	@ManyToOne
   	@JoinColumn(name = "chemicalid" )
	private Chemicals chemicals;

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

	public Chemicals getChemicals() {
		return chemicals;
	}

	public void setChemicals(Chemicals chemicals) {
		this.chemicals = chemicals;
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

	@Override
	public String toString() {
		return "RecordDetail{" +
				"id=" + id +
				", recordid=" + recordid +
				", chemicals=" + chemicals +
				", optype=" + optype +
				", account=" + account +
				'}';
	}

}
