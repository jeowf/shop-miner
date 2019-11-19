package br.ufrn.shopminer.framework.model;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.ufrn.shopminer.framework.model.Config;

@Entity
@Table(name = "site")
public class Site implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="url")
	private String url;
	
	
	
	@ManyToOne
	@JoinColumn(name="config_id")
	private Config config;
	
	@OneToMany(mappedBy="site", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Tag> tags;
	
	
	
	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	} 
}
