package br.ufrn.minerin.cripto.service.custom;

import br.ufrn.minerin.cripto.model.RTPrice;
import br.ufrn.minerin.cripto.service.RTPriceService;
import br.ufrn.minerin.framework.service.core.PersistStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Primary
public class CriptoPersistStrategy implements PersistStrategy {

	@Autowired
	private RTPriceService rtPriceService;

	@Override
	public void persistQueries(List<Serializable> queries) {

		try {
			for(Serializable query : queries){
				rtPriceService.save((RTPrice) query);
			}
		}catch(Exception e) {
			System.out.println("Operação Inválida");
			e.printStackTrace();
		}

	}

}
