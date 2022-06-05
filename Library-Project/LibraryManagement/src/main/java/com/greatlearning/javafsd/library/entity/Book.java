package com.greatlearning.javafsd.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

	// define fields

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "category")
	private String category;

	@Column(name = "author")
	private String author;

	// define constructors

	public Book() {

	}

	public Book(String name, String category, String author) {
		super();

		this.name = name;
		this.category = category;
		this.author = author;
	}

	// define getter/setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	// define tostring
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", category=" + category + ", author=" + author + "]";
	}

}
