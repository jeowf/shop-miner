package br.ufrn.minerin.shopminer;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufrn.minerin.framework.service.core.PersistStrategy;
import br.ufrn.minerin.shopminer.model.SiteProductPrice;
import br.ufrn.minerin.shopminer.service.SiteProductPriceService;
import org.springframework.context.annotation.Primary;
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
