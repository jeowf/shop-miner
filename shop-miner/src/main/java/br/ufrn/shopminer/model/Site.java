package br.ufrn.shopminer.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "site")
public class Site implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="site_sequence")
	@SequenceGenerator(name="site_sequence", sequenceName="site_seq")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="url")
	private String url;
	
	@Column(name="class")
	private String tagClass;
	
	@ManyToOne
	@JoinColumn(name="config_id")
	private Config config;

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

	public String getTagClass() {
		return tagClass;
	}

	public void setTagClass(String tagClass) {
		this.tagClass = tagClass;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	} 
}
