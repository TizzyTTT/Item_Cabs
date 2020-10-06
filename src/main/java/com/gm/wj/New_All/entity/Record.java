package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gm.wj.entity.AdminPermission;
import com.gm.wj.entity.Organization;
import com.gm.wj.entity.User;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author  wwhh
 * @Date 2020-08-11 15:12:34
 */

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="ordinary_record")
public class Record  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

	@OneToOne
	@JoinColumn(name = "cabid" )
	private Cabinets cabinets;

	@OneToOne
	@JoinColumn(name = "userid")
	private User user;

	@OneToOne
	@JoinColumn(name = "aduserid" )
	private User aduser;

   	@Column(name = "time" )
	private Date time;

	@OneToOne
	@JoinColumn(name = "orgid")
	private Organization organization;

	@Transient
	private List<RecordDetail> recordDetails;

	public List<RecordDetail> getRecordDetails() {
		return recordDetails;
	}

	public void setRecordDetails(List<RecordDetail> recordDetails) {
		this.recordDetails = recordDetails;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public User getAduser() {
		return aduser;
	}

	public void setAduser(User aduser) {
		this.aduser = aduser;
	}

	public User getUser() { return user; }

	public void setUser(User user) {this.user = user;}

	public Cabinets getCabinets() {
		return cabinets;
	}

	public void setCabinets(Cabinets cabinets) {
		this.cabinets = cabinets;
	}
}
