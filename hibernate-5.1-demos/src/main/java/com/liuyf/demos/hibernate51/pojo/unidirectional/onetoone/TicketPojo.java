package com.liuyf.demos.hibernate51.pojo.unidirectional.onetoone;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class TicketPojo implements Serializable {

	private static final long serialVersionUID = -5527556727124963216L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String programme;
	private Integer row;
	private Integer col;
	@Column(name = "show_time")
	private LocalDateTime showTime;
	private LocalDateTime created;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProgramme() {
		return programme;
	}
	public void setProgramme(String programme) {
		this.programme = programme;
	}
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
	public Integer getCol() {
		return col;
	}
	public void setCol(Integer col) {
		this.col = col;
	}
	public LocalDateTime getShowTime() {
		return showTime;
	}
	public void setShowTime(LocalDateTime showTime) {
		this.showTime = showTime;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
}
