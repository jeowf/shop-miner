package br.ufrn.shopminer.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "extendedsite")
public class ExtendedSite implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="site")
	private Integer site;
	
	@Column(name="config")
	private Integer config;
	
	@Column(name="class")
	private String tagClass;
	
	@Column(name="pclass")
	private String productClass;
	
	@Column(name="dclass")
	private String descriptionClass;
	
	@Column(name="productLink")
	private String productLink;
	
	@Column(name="iclass")
	private String imgClass;

	@OneToMany(mappedBy = "extendedsite", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIgnore
	private List<SiteProductPrice> siteProductPrices;

	
	
	
	public Integer getConfig() {
		return config;
	}

	public void setConfig(Integer config) {
		this.config = config;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSite() {
		return site;
	}

	public void setSite(Integer site) {
		this.site = site;
	}

	public String getTagClass() {
		return tagClass;
	}

	public void setTagClass(String tagClass) {
		this.tagClass = tagClass;
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

	public String getProductLink() {
		return productLink;
	}

	public void setProductLink(String productLink) {
		this.productLink = productLink;
	}

	public String getImgClass() {
		return imgClass;
	}

	public void setImgClass(String imgClass) {
		this.imgClass = imgClass;
	}

	public List<SiteProductPrice> getSiteProductPrices() {
		return siteProductPrices;
	}

	public void setSiteProductPrices(List<SiteProductPrice> siteProductPrices) {
		this.siteProductPrices = siteProductPrices;
	}


	
	
	
}
