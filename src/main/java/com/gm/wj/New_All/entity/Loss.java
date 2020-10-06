package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gm.wj.entity.User;

import java.util.Date;

/**
 * @Description
 * @Author  wwhh
 * @Date 2020-08-23 12:13:13
 */

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="lossrecord")
public class Loss  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

	@OneToOne
   	@JoinColumn(name = "resp_userid" )
	private User respUser;

   	@Column(name = "time" )
	private Date time;

   	@OneToOne
   	@JoinColumn(name = "register_userid" )
	private User registerUser;

   	@OneToOne
   	@JoinColumn(name = "losstype" )
	private OperationType operationType;


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getRespUser() {
		return respUser;
	}

	public void setRespUser(User respUser) {
		this.respUser = respUser;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public User getRegisterUser() {
		return registerUser;
	}

	public void setRegisterUser(User registerUser) {
		this.registerUser = registerUser;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

}
