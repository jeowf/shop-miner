package br.ufrn.shopminer.service.custom;

import br.ufrn.shopminer.framework.service.core.PersistStrategy;
import br.ufrn.shopminer.model.SiteProductPrice;
import br.ufrn.shopminer.model.TrendingTopics;
import br.ufrn.shopminer.repository.TrendingTopicsRepository;
import br.ufrn.shopminer.service.SiteProductPriceService;
import br.ufrn.shopminer.service.TrendingTopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class TTPersistStrategy implements PersistStrategy {
	
	@Autowired
    private TrendingTopicsService tts;

	@Autowired
	TrendingTopicsRepository ttr;

	@Override
	public void persistQueries(List<Serializable> queries) {

		try {
			for(Serializable query : queries){
				if (ttr != null) {
					ttr.save((TrendingTopics) query);
					System.out.println("Salvando Trending");
				}
				else {
					System.out.println("Não está inicializando o Repository nem o Service");
				}
	        }
		}catch(Exception e) {
			System.out.println("Operação Inválida");
			e.printStackTrace();
		}
		
	}

	//@Bean
	//TTPersistStrategy TTPersistStrategy(){
	//	return new TTPersistStrategy();
	//}

}
