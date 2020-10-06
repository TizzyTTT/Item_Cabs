package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Description
 * @Author  wwhh
 * @Date 2020-08-23 12:13:13
 */

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="containerloss_detail")
public class ContainerlossDetail  implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

	@OneToOne
	@JoinColumn(name = "Lossid" )
	private Loss loss;

	@OneToOne
	@JoinColumn(name = "containerid" )
	private Item container;

   	@Column(name = "reason" )
	private String reason;

   	@Column(name = "except" )
	private int except;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getExcept() {
		return this.except;
	}

	public Item getContainer() {
		return container;
	}

	public void setContainer(Item container) {
		this.container = container;
	}

	public void setExcept(int except) {
		this.except = except;
	}

	public Loss getLoss() {
		return loss;
	}

	public void setLoss(Loss loss) {
		this.loss = loss;
	}

}
