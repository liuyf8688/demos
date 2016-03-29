package com.liuyf.demos.hibernate51.pojo.bidirectional.onetomany;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class CarPojo implements Serializable {

	private static final long serialVersionUID = -8123381591446046672L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String brand;
	private String model;
	/** 
	 * When remove the element from the collections, it triggers a update statue.
	 */
	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
	/**
	 * If you uncomment the line below and comment the previous line.
	 * remove the element, it triggers a delete status.
	 * More details, please refer to orphanRemoval in @OneToMany
	 */
//	@OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PartPojo> parts = new ArrayList<>();
	private LocalDateTime created;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public List<PartPojo> getParts() {
		return parts;
	}
	public void addPart(PartPojo part) {
		parts.add(part);
		part.setCar(this);
	}
	public void removePart(PartPojo part) {
		parts.remove(part);
		part.setCar(null);
	}
}
