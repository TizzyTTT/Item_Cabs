package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gm.wj.entity.Organization;

/**
 * @Description
 * @Author  wwhh
 * @Date 2020-07-27 17:36:06
 */

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="chemicalcategory")
public class Chemicalcategory  implements Serializable {

	private static final long serialVersionUID =  7575234656243273974L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

   	@Column(name = "cname" )
	private String cname;

	@OneToOne
	@JoinColumn(name = "orgid")
	private Organization organization;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
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
					"cname='" + cname + '\'' +
				'}';
	}

}
