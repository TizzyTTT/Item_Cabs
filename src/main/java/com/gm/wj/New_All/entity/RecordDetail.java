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
@Table ( name ="ordinaryrecord_detail")
public class RecordDetail  implements Serializable {

	private static final long serialVersionUID =  3827896852141656103L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

	@OneToOne
	@JoinColumn(name = "recordid" )
	private Record record;

	@OneToOne
   	@JoinColumn(name = "chemicalid" )
	private Chemicals chemicals;

	@OneToOne
	@JoinColumn(name = "optype" )
	private OperationType optype;

	@Column(name = "account" )
	private double account;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Chemicals getChemicals() {
		return chemicals;
	}

	public void setChemicals(Chemicals chemicals) {
		this.chemicals = chemicals;
	}

	public OperationType getOptype() {
		return optype;
	}

	public void setOptype(OperationType optype) {
		this.optype = optype;
	}

	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

}
