package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;

/**
 * @Description
 * @Author  wwhh
 * @Date 2020-08-23 12:13:13
 */

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="chemicalloss_detail")
public class ChemicallossDetail  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

	@OneToOne
   	@JoinColumn(name = "Lossid" )
	private Loss loss;

	@OneToOne
	@JoinColumn(name = "chemicalid" )
	private Chemicals chemicals;

   	@Column(name = "account" )
	private double account;

   	@Column(name = "except" )
	private int except;

   	@Column(name = "reason" )
	private String reason;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Loss getLoss() {
		return loss;
	}

	public void setLoss(Loss loss) {
		this.loss = loss;
	}

	public Chemicals getChemicals() {
		return chemicals;
	}

	public void setChemicals(Chemicals chemicals) {
		this.chemicals = chemicals;
	}

	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}

	public int getExcept() {
		return this.except;
	}

	public void setExcept(int except) {
		this.except = except;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "ChemicallossDetail{" +
				"id=" + id +
				", loss=" + loss +
				", chemicals=" + chemicals +
				", account=" + account +
				", except=" + except +
				", reason='" + reason + '\'' +
				'}';
	}
}
