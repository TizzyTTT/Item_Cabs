package com.gm.wj.New_All.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gm.wj.entity.User;

import javax.persistence.*;
import java.io.Serializable;
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
public class Record2 implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

   	@Column(name = "cabid" )
	private int cabid;

	@Column(name = "userid")
	private int userid;


	@Column(name = "aduserid" )
	private int aduserid;

   	@Column(name = "time" )
	private Date time;

	/**
	 * Transient property for storing permissions owned by current role.
	 */
	@Transient
	private List<RecordDetail2> recordDetails;



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

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getAduserid() {
		return aduserid;
	}

	public void setAduserid(int aduserid) {
		this.aduserid = aduserid;
	}

	public List<RecordDetail2> getRecordDetails() {
		return recordDetails;
	}

	public void setRecordDetails(List<RecordDetail2> recordDetails) {
		this.recordDetails = recordDetails;
	}

	@Override
	public String toString() {
		return "Record2{" +
				"id=" + id +
				", cabid=" + cabid +
				", userid=" + userid +
				", aduserid=" + aduserid +
				", time=" + time +
				", recordDetails=" + recordDetails +
				'}';
	}
}
