package br.ufrn.shopminer.service.custom;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufrn.shopminer.framework.service.core.PersistStrategy;
import br.ufrn.shopminer.model.SiteProductPrice;
import br.ufrn.shopminer.service.SiteProductPriceService;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistStrategy implements PersistStrategy{
	
	@Autowired
	private SiteProductPriceService spps;

	@Override
	public void persistQueries(List<Serializable> queries) {
		
		try {
			for(Serializable query : queries){
	            spps.save((SiteProductPrice)query);
	        }
		}catch(Exception e) {
			System.out.println("Operação Inválida");
		}
		
	}

}
