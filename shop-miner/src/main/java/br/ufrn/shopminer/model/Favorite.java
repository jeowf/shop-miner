package br.ufrn.shopminer.model;

import java.io.Serializable;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;



@Entity
@Table(name = "favorite")
public class Favorite implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	

	@ManyToOne
	@JoinColumn(name="config_id")
	private Config config;

	@Column(name="value")
	private String value;

	@Column(name="rate")
	private Integer rateInteger;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getRateInteger() {
		return rateInteger;
	}

	public void setRateInteger(Integer rateInteger) {
		this.rateInteger = rateInteger;
	}

	
	
}
