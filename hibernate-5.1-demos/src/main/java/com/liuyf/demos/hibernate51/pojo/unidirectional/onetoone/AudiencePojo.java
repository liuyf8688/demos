package com.liuyf.demos.hibernate51.pojo.unidirectional.onetoone;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "audience")
public class AudiencePojo implements Serializable {

	private static final long serialVersionUID = -1944063012251240765L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	/**
	 * If there is no cascade operation, it will trigger a exception.
	 * org.hibernate.TransientObjectException: object references an unsaved transient instance - save the transient instance before flushing: XXX
	 */
//	@OneToOne
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ticket_id")
	private TicketPojo ticket;
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
	public TicketPojo getTicket() {
		return ticket;
	}
	public void setTicket(TicketPojo ticket) {
		this.ticket = ticket;
	}
	
}
