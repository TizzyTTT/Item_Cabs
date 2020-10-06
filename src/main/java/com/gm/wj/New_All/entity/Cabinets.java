package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gm.wj.entity.Organization;

/**
 * @Description
 * @Author  wwhh
 * @Date 2020-08-10 22:36:29
 */

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="cabinets")
public class Cabinets  implements Serializable {

	private static final long serialVersionUID =  3693100932554223215L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id = -1;

   	@Column(name = "location" )
	private String location;

   	@Column(name = "cabno" )
	private String cabno;

	@OneToOne
   	@JoinColumn(name = "orgid")
	private Organization organization;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCabname() {
		return this.cabno;
	}

	public void setCabname(String cabname) {
		this.cabno = cabname;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"location='" + location + '\'' +
					"cabno='" + cabno + '\'' +
				'}';
	}

}
