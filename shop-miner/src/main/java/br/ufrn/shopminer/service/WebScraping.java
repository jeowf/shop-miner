package br.ufrn.shopminer.service;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import br.ufrn.shopminer.model.Config;
import br.ufrn.shopminer.model.Product;

@Service
@Transactional(readOnly = true)
public class WebScraping {

	public List<Product> search(Config config, String query) throws IOException{
		
		List<Entry<String,String>> pairList= new java.util.ArrayList<>();
    	Entry<String,String> pair1=new AbstractMap.SimpleEntry<>("https://www.submarino.com.br/busca/{}","PriceUI-bwhjk3-11");
    	Entry<String,String> pair3=new AbstractMap.SimpleEntry<>("https://lista.mercadolivre.com.br/{}#D[A:{}","item__price");
    	pairList.add(pair1);
    	pairList.add(pair3);
    	
        ArrayList<Product> listProducts = new ArrayList<Product>();

    	
    	for (int i = 0; i < pairList.size(); i++) {
    		
    		query = processQuery(query);
    		
    		Document doc = Jsoup.connect(pairList.get(i).getKey().replace("{}", query)).get();

            Elements sites = doc.getElementsByClass(pairList.get(i).getValue());
            
            String name = doc.getElementsByTag("title").get(0).toString();
            
            String price = sites.get(0).text();
            Product product = new Product(name,price);
            
            
            listProducts.add(product);
            
    	}
		return listProducts;
	}
	
	private String processQuery(String query) {
		return query.replace(" ", "-");
	}
	
}
