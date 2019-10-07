package br.ufrn.shopminer.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import br.ufrn.shopminer.model.Config;
import br.ufrn.shopminer.model.Favorite;
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
	
	private boolean started = false;
	
	@Transactional(readOnly = false)
	public List<SiteProductPrice> search(Config config, String query) throws IOException{
        ArrayList<SiteProductPrice> products = new ArrayList<SiteProductPrice>();
        List<Site> sites = config.getSites();
    
        Product product = findProduct(query);

        
    	for (Site site : sites) {
    		
    		query = processQuery(query);
    		Document doc = Jsoup.connect(site.getUrl().replace("{}", query)).get();
            Elements values = doc.getElementsByClass(site.getTagClass());
            
            site.setProductLink(doc.getElementsByClass(site.getProductClass()).get(0).attr("href"));
                                    
            String value = values.get(0).text();
            
            searchProduct(product, site.getProductLink() ,site);
            
            Timestamp ts = new Timestamp(System.currentTimeMillis());  
            Date date=new Date(ts.getTime());  
            
            Price price = registerPrice(value, date);
            
            SiteProductPrice spp = new SiteProductPrice(site, product, price);
            siteProductPriceService.save(spp); 
            products.add(spp);
            
    	}
		return products;
	}
	
	@Transactional(readOnly = false)
	public Product searchProduct(Product product, String query, Site site) throws IOException{
		
		    product = findProduct(product.getName());
        		
        	Document doc = Jsoup.connect(query).get();                        
            
            product.setImg(doc.getElementsByClass(site.getImgClass()).get(0).attr("href"));
                        
            product.setDescription(doc.getElementsByClass(site.getDescriptionClass()).get(0).text());
			
    	
		return product;
	}
	
	
	
	
	@Async
	@Transactional(readOnly = false)
	public void autoSearch(Config config) throws InterruptedException{
		if (!started) {
			started = true;
			
			while(started) {	
				try {
					
					//System.out.println(config.getFavorites().size());
					for (Favorite fav : config.getFavorites()) {
						System.out.println(fav.getValue());
						
						List<SiteProductPrice>  r = search(config, fav.getValue());
					 						
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			
					
				System.out.println("opa");
				Thread.sleep(5 * 1000L);
			}
		}
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
