package br.ufrn.minerin.shopminer.model;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;

//usar esse se der erro, ainda n testei
//import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "price")
public class Price implements Serializable, Comparable<Price> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="value")
	private String value;
	
	@Column(name="date")
	@Temporal(TemporalType.TIMESTAMP)
    private Date date;

	@OneToMany(mappedBy = "price", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int compareTo(Price o) {
		/*
		Double v1 = Double.parseDouble(value
                			.replaceAll("R|\\$| |\\.", "")
                			.replaceAll(",","."));
		Double v2 =  Double.parseDouble(o.value
    						.replaceAll("R|\\$| |\\.", "")
    						.replaceAll(",","."));
    						*/
		// TODO Auto-generated method stub
		if (date.equals(o.date))
			return 0;
		else if (date.after(o.date))
			return 1;
		else
			return -1;
	}
	
	
	
	
}
