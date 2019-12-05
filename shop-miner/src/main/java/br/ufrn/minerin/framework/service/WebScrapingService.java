package br.ufrn.minerin.framework.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.minerin.framework.service.core.Attribute;
import br.ufrn.minerin.framework.service.core.PersistStrategy;
import br.ufrn.minerin.framework.service.core.QueryFactory;
import br.ufrn.minerin.framework.spec.MinerinConfig;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufrn.minerin.framework.model.Config;
import br.ufrn.minerin.framework.model.Site;
import br.ufrn.minerin.framework.model.Tag;

@Service
@Transactional(readOnly = true)
public class WebScrapingService {


	@Autowired
	private QueryFactory queryFactory;
	@Autowired
	private PersistStrategy persistStrategy;
	
	
	
	@Transactional(readOnly = false)
	public List<Serializable> search(Config config, String query) throws IOException{

		if (queryFactory == null)
			queryFactory = MinerinConfig.getInstance().getQueryFactory();
		if (persistStrategy == null)
			persistStrategy = MinerinConfig.getInstance().getPersistStrategy();
		
		
		List<Site> sites = config.getSites();
		
		//List<Serializable> res = null;
		List<Serializable> res = new ArrayList<>();

		if (sites != null) {
			for (Site site : sites) {
				
				Document doc = Jsoup.connect(site.getUrl().replace("{}", query)).get();
				
				List<Attribute<Elements>> atrs = new ArrayList<Attribute<Elements>>();

				for (Tag tag : site.getTags()) {
					
					Elements els = doc.getElementsByClass(tag.getClass_name());
					
					//System.out.println(els.toString());
					
					Attribute<Elements> atr = new Attribute<Elements>(tag.getName(), els);
					
					atrs.add(atr);
				}
				
				List<Serializable> s = queryFactory.constructResult(atrs, site, query);
				
				if (s != null) {
					res.add( s.get(0));
				}
				
				persistStrategy.persistQueries(s);
              
				
			}
		}




		
		
    	
		return res;
	}
	
	
	
	
	
}
