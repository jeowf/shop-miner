package br.ufrn.shopminer.controller;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.shopminer.model.Product;
import br.ufrn.shopminer.service.ConfigService;
import br.ufrn.shopminer.service.SiteService;

@RestController
@RequestMapping("/api")
public class WebScrapingController {
	
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private ConfigService configService;
	
	@GetMapping("/search/{query}")
	public ArrayList<Product> a(@PathVariable("query") String query) throws IOException{
		
		List<Entry<String,String>> pairList= new java.util.ArrayList<>();
    	Entry<String,String> pair1=new AbstractMap.SimpleEntry<>("https://www.submarino.com.br/busca/{}","PriceUI-bwhjk3-11");
    	Entry<String,String> pair3=new AbstractMap.SimpleEntry<>("https://lista.mercadolivre.com.br/{}#D[A:{}","item__price");
    	pairList.add(pair1);
    	pairList.add(pair3);
    	
        ArrayList<Product> listProducts = new ArrayList<Product>();

    	
    	for (int i = 0; i < pairList.size(); i++) {
    		
    		String itemQuery =  "rx 550";
    		
    		itemQuery = itemQuery.replace(" ", "-");
    		
    		Document doc = Jsoup.connect(pairList.get(i).getKey().replace("{}", itemQuery)).get();

            Elements sites = doc.getElementsByClass(pairList.get(i).getValue());
            
            String name = doc.getElementsByTag("title").get(0).toString();
            
            String price = sites.get(0).text();
            Product product = new Product(name,price);
            
            
            listProducts.add(product);
            
            
//            System.out.println(doc.getElementsByTag("title").get(0));
//            
//            System.out.println(sites.get(0).text());
			
		}
		return listProducts;
	}
	

}
