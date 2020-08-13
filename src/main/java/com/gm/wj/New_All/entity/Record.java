package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gm.wj.entity.AdminPermission;
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
@Table ( name ="record")
public class Record  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

   	@Column(name = "cabid" )
	private int cabid;

	@OneToOne
	@JoinColumn(name = "userid")
	private User user;

	@OneToOne
	@JoinColumn(name = "aduserid" )
	private User aduser;

   	@Column(name = "time" )
	private Date time;

	public List<RecordDetail> getRecordDetails() {
		return recordDetails;
	}

	public void setRecordDetails(List<RecordDetail> recordDetails) {
		this.recordDetails = recordDetails;
	}

	/**
	 * Transient property for storing permissions owned by current role.
	 */
	@Transient
	private List<RecordDetail> recordDetails;

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

	@Override
	public String toString() {
		return "Record{" +
				"id=" + id +
				", cabid=" + cabid +
				", user=" + user +
				", aduser=" + aduser +
				", time=" + time +
				", recordDetails=" + recordDetails +
				'}';
	}
}
