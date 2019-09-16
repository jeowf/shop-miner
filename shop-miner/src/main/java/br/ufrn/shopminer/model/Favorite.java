package br.ufrn.shopminer.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "favorite")
public class Favorite implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="config_id", referencedColumnName = "id")
	private Config config;
	
	@OneToMany(mappedBy="favorite", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Query> queries;

	
	
	
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

	public List<Query> getQueries() {
		return queries;
	}

	public void setQueries(List<Query> queries) {
		this.queries = queries;
	}

	public Favorite(Integer id, Config config, List<Query> queries) {
		super();
		this.id = id;
		this.config = config;
		this.queries = queries;
	}
	
	
	
	
	
	
	
	
}
