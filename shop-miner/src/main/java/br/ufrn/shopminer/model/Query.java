package br.ufrn.shopminer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "query")
public class Query {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "value")
	private String value;
	
	@Column(name = "rate")
	private Integer rate;

	@ManyToOne
	@JoinColumn(name="favorite_id")
	private Favorite favorite;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Favorite getFavorite() {
		return favorite;
	}

	public void setFavorite(Favorite favorite) {
		this.favorite = favorite;
	}

	public Query(Integer id, String value, Integer rate, Favorite favorite) {
		super();
		this.id = id;
		this.value = value;
		this.rate = rate;
		this.favorite = favorite;
	}
	
	
	
	
}
