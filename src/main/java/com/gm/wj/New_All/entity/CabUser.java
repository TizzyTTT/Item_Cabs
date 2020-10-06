package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gm.wj.New_All.utils.SimpleUser;
import com.gm.wj.New_All.entity.Cabinets;
/**
 * @Description
 * @Author  wwhh
 * @Date 2020-08-14 15:23:31
 */

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="cab_user")
public class CabUser  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

	@OneToOne
   	@JoinColumn(name = "cabid" )
	private Cabinets cabinets;

	@OneToOne
   	@JoinColumn(name = "userid" )
	private SimpleUser user;

   	@Column(name = "p_open" )
	private int pOpen;

   	@Column(name = "p_out" )
	private int pOut;

	@Column(name = "p_in" )
	private int pIn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cabinets getCabinets() {
		return cabinets;
	}

	public void setCabinets(Cabinets cabinets) {
		this.cabinets = cabinets;
	}

	public SimpleUser getUser() {
		return user;
	}

	public void setUser(SimpleUser user) {
		this.user = user;
	}

	public int getpOpen() {
		return pOpen;
	}

	public void setpOpen(int pOpen) {
		this.pOpen = pOpen;
	}

	public int getpOut() {
		return pOut;
	}

	public void setpOut(int pOut) {
		this.pOut = pOut;
	}

	public int getpIn() {
		return pIn;
	}

	public void setpIn(int pIn) {
		this.pIn = pIn;
	}


}
