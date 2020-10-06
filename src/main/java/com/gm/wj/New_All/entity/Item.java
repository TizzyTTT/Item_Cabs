package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Description
 * @Author  wwhh
 * @Date 2020-09-08 12:03:25
 */

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="item")
public class Item  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

   	@Column(name = "itemno" )
	private String itemno;

   	@Column(name = "itemname" )
	private String itemname;

   	@JoinColumn(name = "cid" )
   	@OneToOne
	private Itemcategory itemcategory;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemno() {
		return this.itemno;
	}

	public void setItemno(String itemno) {
		this.itemno = itemno;
	}

	public String getItemname() {
		return this.itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public Itemcategory getItemcategory() {
		return itemcategory;
	}

	public void setItemcategory(Itemcategory itemcategory) {
		this.itemcategory = itemcategory;
	}

	@Override
	public String toString() {
		return "Item{" +
				"id=" + id +
				", itemno='" + itemno + '\'' +
				", itemname='" + itemname + '\'' +
				", itemcategory=" + itemcategory +
				'}';
	}

}
