package br.ufrn.shopminer.framework.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import br.ufrn.shopminer.framework.service.core.Attribute;
import br.ufrn.shopminer.framework.service.core.PersistStrategy;
import br.ufrn.shopminer.framework.service.core.QueryFactory;
import br.ufrn.shopminer.framework.spec.MinerinConfig;

import br.ufrn.shopminer.model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import br.ufrn.shopminer.framework.model.Config;
import br.ufrn.shopminer.framework.model.Site;
import br.ufrn.shopminer.framework.model.Tag;
import br.ufrn.shopminer.repository.ExtendedSiteRepository;
import br.ufrn.shopminer.service.PriceService;
import br.ufrn.shopminer.service.ProductService;
import br.ufrn.shopminer.service.SiteProductPriceService;

import java.sql.Timestamp;

@Service
@Transactional(readOnly = true)
public class WebScrapingService {


	private QueryFactory queryFactory;
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
