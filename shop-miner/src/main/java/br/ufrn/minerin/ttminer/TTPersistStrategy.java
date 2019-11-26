package br.ufrn.minerin.ttminer;

import br.ufrn.minerin.framework.service.core.PersistStrategy;
import br.ufrn.minerin.ttminer.model.TrendingTopics;
import br.ufrn.minerin.ttminer.service.TrendingTopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class TTPersistStrategy implements PersistStrategy {

	@Autowired
	private TrendingTopicsService tts;

	@Override
	public void persistQueries(List<Serializable> queries) {

		try {
			for(Serializable query : queries){
				tts.save((TrendingTopics) query);
			}
		}catch(Exception e) {
			System.out.println("Operação Inválida");
			e.printStackTrace();
		}

	}

}
