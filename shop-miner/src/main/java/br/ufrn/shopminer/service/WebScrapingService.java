package br.ufrn.shopminer.service;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import br.ufrn.shopminer.model.Config;
import br.ufrn.shopminer.model.Product;
import br.ufrn.shopminer.model.Site;

@Service
@Transactional(readOnly = true)
public class WebScrapingService {

	//@Autowired
	//private ConfigService configService;
	
	public List<Product> search(Config config, String query) throws IOException{
		
//		List<Entry<String,String>> pairList= new java.util.ArrayList<>();
//    	Entry<String,String> pair1=new AbstractMap.SimpleEntry<>("https://www.submarino.com.br/busca/{}","PriceUI-bwhjk3-11");
//    	Entry<String,String> pair3=new AbstractMap.SimpleEntry<>("https://lista.mercadolivre.com.br/{}#D[A:{}","item__price");
//    	pairList.add(pair1);
//    	pairList.add(pair3);
//    	
        ArrayList<Product> products = new ArrayList<Product>();
        List<Site> sites = config.getSites();
    
        
    	for (Site site : sites) {
    		
    		query = processQuery(query);
    		
    		Document doc = Jsoup.connect(site.getUrl().replace("{}", query)).get();

            Elements values = doc.getElementsByClass(site.getTagClass());
            
            String name = site.getName();
            
            String price = values.get(0).text();
            Product product = new Product(name,price);
            
            products.add(product);
    	}
		return products;
	}
	
	private String processQuery(String query) {
		return query.replace(" ", "-");
	}
	
}
