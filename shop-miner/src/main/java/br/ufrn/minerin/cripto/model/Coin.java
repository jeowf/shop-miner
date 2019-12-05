package br.ufrn.minerin.cripto.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "coin")
public class Coin implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String name;
    private String cod;
    
    
    @OneToMany(mappedBy = "coin", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<RTPrice> rtPrices;
    
    @OneToMany(mappedBy = "coin", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<MPrice> mPrices;

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

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public List<RTPrice> getRtPrices() {
		return rtPrices;
	}

	public void setRtPrices(List<RTPrice> rtPrices) {
		this.rtPrices = rtPrices;
	}

	public List<MPrice> getmPrices() {
		return mPrices;
	}

	public void setmPrices(List<MPrice> mPrices) {
		this.mPrices = mPrices;
	}
    
    
    
    
    
}
