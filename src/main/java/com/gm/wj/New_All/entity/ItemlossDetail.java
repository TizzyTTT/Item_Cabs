package com.gm.wj.New_All.entity;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author  wwhh
 * @Date 2020-09-28 13:53:37
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Table ( name ="itemloss_detail")
public class ItemlossDetail  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private int id;

	@OneToOne
   	@JoinColumn(name = "Lossid" )
	private Loss loss;

	@OneToOne
   	@JoinColumn(name = "itemid" )
	private Item itemid;

   	@Column(name = "reason" )
	private String reason;

   	@Column(name = "except" )
	private int except;
}
