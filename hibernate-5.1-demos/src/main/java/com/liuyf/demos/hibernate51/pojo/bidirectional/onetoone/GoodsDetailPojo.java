package com.liuyf.demos.hibernate51.pojo.bidirectional.onetoone;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "goods_detail")
public class GoodsDetailPojo implements Serializable {

	private static final long serialVersionUID = 4718993468518078831L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer height;
	private Integer length;
	private Integer width;
	@OneToOne
	@JoinColumn(name = "goods_id")
	private GoodsPojo goods;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public GoodsPojo getGoods() {
		return goods;
	}
	public void setGoods(GoodsPojo goods) {
		this.goods = goods;
	}
	
}
