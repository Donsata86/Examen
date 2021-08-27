package com.miguel.examen.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tvShows")
public class TvShow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min = 1, max = 64)
	private String name;
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "network_id")
	private Network network;

	public TvShow() {

	}

	public TvShow(Long id, @Size(min = 1, max = 64) String name, Date createdAt, Date updatedAt, Network network) {
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.network = network;
	}

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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}
