package br.ufrn.minerin.shopminer.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "site_product_price")
public class SiteProductPrice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "extendedsite_id")
    private ExtendedSite extendedsite;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "price_id")
    private Price price;

    
    public ExtendedSite getExtendedSite() {
		return extendedsite;
	}

	public void setExtendedSite(ExtendedSite extendedSite) {
		this.extendedsite = extendedSite;
	}

	public SiteProductPrice() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ExtendedSite getSite() {
		return extendedsite;
	}

	public void setSite(ExtendedSite site) {
		this.extendedsite = site;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public SiteProductPrice(ExtendedSite site, Product product, Price price) {
		super();
		this.extendedsite = site;
		this.product = product;
		this.price = price;
	}
	
	
	
}
