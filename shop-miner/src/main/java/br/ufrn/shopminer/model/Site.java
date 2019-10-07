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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Column(name="class")
	private String tagClass;
	
	@ManyToOne
	@JoinColumn(name="config_id")
	private Config config;
	
	@Column(name="pclass")
	private String productClass;
	
	@Column(name="dclass")
	private String descriptionClass;
	
	@Column(name="productLink")
	private String productLink;
	
	public String getProductLink() {
		return productLink;
	}

	public void setProductLink(String productLink) {
		this.productLink = productLink;
	}

	public String getProductClass() {
		return productClass;
	}

	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}

	public String getDescriptionClass() {
		return descriptionClass;
	}

	public void setDescriptionClass(String descriptionClass) {
		this.descriptionClass = descriptionClass;
	}

	public String getImgClass() {
		return imgClass;
	}

	public void setImgClass(String imgClass) {
		this.imgClass = imgClass;
	}

	@Column(name="iclass")
	private String imgClass;

	@OneToMany(mappedBy = "site", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIgnore
	private List<SiteProductPrice> siteProductPrices;
	
	public List<SiteProductPrice> getSiteProductPrices() {
		return siteProductPrices;
	}

	public void setSiteProductPrices(List<SiteProductPrice> siteProductPrices) {
		this.siteProductPrices = siteProductPrices;
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
