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
@Table ( name ="chemicals")
public class Chemicals  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

   	@Column(name = "chemicalno" )
	private String chemicalno;

   	@Column(name = "chemicalname" )
	private String chemicalname;

//   	@Column(name = "categoryid" )
//	private int categoryid;

	@ManyToOne
	@JoinColumn(name="categoryid")
	private Chemicalcategory chemicalcategory;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChemicalno() {
		return this.chemicalno;
	}

	public Chemicalcategory getChemicalcategory() {
		return chemicalcategory;
	}

	public void setChemicalcategory(Chemicalcategory chemicalcategory) {
		this.chemicalcategory = chemicalcategory;
	}

	public void setChemicalno(String chemicalno) {
		this.chemicalno = chemicalno;
	}

	public String getChemicalname() {
		return this.chemicalname;
	}

	public void setChemicalname(String chemicalname) {
		this.chemicalname = chemicalname;
	}


	@Override
	public String toString() {
		return "Chemicals{" +
				"id=" + id +
				", chemicalno='" + chemicalno + '\'' +
				", chemicalname='" + chemicalname + '\'' +
				", chemicalcategory=" + chemicalcategory +
				'}';
	}
}
