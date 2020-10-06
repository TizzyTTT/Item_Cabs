package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gm.wj.entity.Organization;

import com.gm.wj.entity.Organization;
/**
 * @Description
 * @Author  wwhh
 * @Date 2020-09-08 12:03:25
 */

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="itemcategory")
public class Itemcategory implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

   	@Column(name = "name" )
	private String name;

	@OneToOne
	@JoinColumn(name = "orgid")
	private Organization organization;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "Itemcategory{" +
				"id=" + id +
				", name='" + name + '\'' +
				", organization=" + organization +
				'}';
	}
}
