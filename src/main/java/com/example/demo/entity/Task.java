package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // カテゴリーID
	@Column(name = "user_id")
	private Integer userId;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category; // カテゴリー

	private String title;
	@Column(name = "closing_date")
	private LocalDate closingdate;

	private Integer progress;

	private String memo;

	public Task() {

	}

	public Task(Integer userId, Category category, String title, LocalDate closingdate, Integer progress, String memo) {

		this.userId = userId;
		this.category = category;
		this.title = title;
		this.closingdate = closingdate;
		this.progress = progress;
		this.memo = memo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getClosingdate() {
		return closingdate;
	}

	public void setClosingdate(LocalDate closingdate) {
		this.closingdate = closingdate;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
