package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Description
 * @Author  wwhh
 * @Date 2020-08-20 15:38:55
 */

@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="operation_type")
public class OperationType  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "optype_id" )
	private int optypeId;

   	@Column(name = "optype_name" )
	private String optypeName;

   	@Column(name = "optype_zhname" )
	private String optypeZhname;

	public int getOptypeId() {
		return this.optypeId;
	}

	public void setOptypeId(int optypeId) {
		this.optypeId = optypeId;
	}

	public String getOptypeName() {
		return this.optypeName;
	}

	public void setOptypeName(String optypeName) {
		this.optypeName = optypeName;
	}

	public String getOptypeZhname() {
		return this.optypeZhname;
	}

	public void setOptypeZhname(String optypeZhname) {
		this.optypeZhname = optypeZhname;
	}

	@Override
	public String toString() {
		return "{" +
					"optypeId='" + optypeId + '\'' +
					"optypeName='" + optypeName + '\'' +
					"optypeZhname='" + optypeZhname + '\'' +
				'}';
	}

}
