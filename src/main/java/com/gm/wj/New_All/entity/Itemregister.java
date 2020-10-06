package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gm.wj.entity.Organization;

import java.util.Date;

/**
 * @Description
 * @Author  wwhh
 * @Date 2020-09-08 14:14:54
 */

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="itemregister")
public class Itemregister  implements Serializable {

	private static final long serialVersionUID =  9184704869194264734L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

	@JoinColumn(name = "itemid" )
   	@OneToOne
	private Item item;

   	@Column(name = "date" )
	private Date date;

   	@Column(name = "account" )
	private int account;

   	@OneToOne
	@JoinColumn(name = "orgid")
	private Organization organization;


	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getAccount() {
		return this.account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "Itemregister{" +
				"id=" + id +
				", item=" + item +
				", date=" + date +
				", account=" + account +
				'}';
	}

}
