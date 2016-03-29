package com.liuyf.demos.hibernate51.pojo.bidirectional.onetoone;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "goods")
public class GoodsPojo implements Serializable {

	private static final long serialVersionUID = 2838222940216936740L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToOne(mappedBy = "goods", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private GoodsDetailPojo goodsDetail;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GoodsDetailPojo getGoodsDetail() {
		return goodsDetail;
	}
	public void addGoodsDetail(GoodsDetailPojo goodsDetail) {
		goodsDetail.setGoods(this);
		this.goodsDetail = goodsDetail;
	}
	public void removeGoodsDetail() {
		if (goodsDetail != null) {
			goodsDetail.setGoods(null);
			this.goodsDetail = null;
		}
	}
	
}
