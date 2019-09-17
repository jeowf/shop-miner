package br.ufrn.shopminer.service;

import java.io.IOException;
import java.sql.Date;
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
import br.ufrn.shopminer.model.Price;
import br.ufrn.shopminer.model.Product;
import br.ufrn.shopminer.model.Site;
import br.ufrn.shopminer.model.SiteProductPrice;

import java.sql.Timestamp;

@Service
@Transactional(readOnly = true)
public class WebScrapingService {

	//@Autowired
	//private ConfigService configService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PriceService priceService;
	
	@Autowired
	private SiteProductPriceService siteProductPriceService;
	
	@Transactional(readOnly = false)
	public List<SiteProductPrice> search(Config config, String query) throws IOException{
		
//		List<Entry<String,String>> pairList= new java.util.ArrayList<>();
//    	Entry<String,String> pair1=new AbstractMap.SimpleEntry<>("https://www.submarino.com.br/busca/{}","PriceUI-bwhjk3-11");
//    	Entry<String,String> pair3=new AbstractMap.SimpleEntry<>("https://lista.mercadolivre.com.br/{}#D[A:{}","item__price");
//    	pairList.add(pair1);
//    	pairList.add(pair3);
//    	
        ArrayList<SiteProductPrice> products = new ArrayList<SiteProductPrice>();
        List<Site> sites = config.getSites();
    
        Product product = findProduct(query);

        
        //Product product = findProduct(query);
        
    	for (Site site : sites) {
    		
    		query = processQuery(query);
    		
    		Document doc = Jsoup.connect(site.getUrl().replace("{}", query)).get();

            Elements values = doc.getElementsByClass(site.getTagClass());
            
            //String name = site.getName();
            String value = values.get(0).text();
            
            Timestamp ts = new Timestamp(System.currentTimeMillis());  
            Date date=new Date(ts.getTime());  
            
            Price price = registerPrice(value, date);
            
            SiteProductPrice spp = new SiteProductPrice(site, product, price);
            
            siteProductPriceService.save(spp); 
            
            products.add(spp);
            
    	}
    	
		return products;
	}
	
	private String processQuery(String query) {
		return query.replace(" ", "-");
	}
	
	@Transactional(readOnly = false)
	private Product findProduct(String name) {
		
		Product product = null;
		
		product = productService.findByName(name);

		if (product == null) {
			Product p = new Product();
			p.setName(name);
			System.out.println("cu");
			
			product = productService.save(p);
		}
		
		return product;
	}
	
	@Transactional(readOnly = false)
	private Price registerPrice(String value, Date date) {
		Price price = new Price();
		
		price.setValue(value);
		price.setDate(date);
		
		priceService.save(price);
		
		return price;
	}
	
	
}
