package com.liuyf.demos.hibernate51.pojo.unidirectional.onetomany;

import java.io.Serializable;
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
@Table(name = "writer")
public class WriterPojo implements Serializable {
	
	private static final long serialVersionUID = 1429609547950572763L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BookPojo> books = new ArrayList<>();
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
	public List<BookPojo> getBooks() {
		return books;
	}
	public void setBooks(List<BookPojo> books) {
		this.books = books;
	}

}
