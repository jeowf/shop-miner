package br.ufrn.shopminer.framework.service;

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

import br.ufrn.shopminer.framework.model.Config;
import br.ufrn.shopminer.framework.model.Site;
import br.ufrn.shopminer.model.ExtendedSite;
import br.ufrn.shopminer.model.Favorite;
import br.ufrn.shopminer.model.Price;
import br.ufrn.shopminer.model.Product;
import br.ufrn.shopminer.model.SiteProductPrice;
import br.ufrn.shopminer.repository.ExtendedSiteRepository;
import br.ufrn.shopminer.service.PriceService;
import br.ufrn.shopminer.service.ProductService;
import br.ufrn.shopminer.service.SiteProductPriceService;

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
	
	
	@Autowired
	private ExtendedSiteRepository esr;
	
	@Autowired
	private SiteService siteService;
	
	private boolean started = false;
	
	@Transactional(readOnly = false)
	public List<SiteProductPrice> search(Config config, String query) throws IOException{
        ArrayList<SiteProductPrice> products = new ArrayList<SiteProductPrice>();
        //List<Site> sites = config.getSites();
    
        List<ExtendedSite> esites = esr.findAllByConfig(config.getId());
        
        Product product = findProduct(query);

        if (esites != null) {
        	
        	for (ExtendedSite esite : esites) {
        		
        		query = processQuery(query);
        		
        		Site site = siteService.findOne(esite.getSite()).get();
        		
        		Document doc = Jsoup.connect(site.getUrl().replace("{}", query)).get();
                Elements values = doc.getElementsByClass(esite.getTagClass());
                
                esite.setProductLink(doc.getElementsByClass(esite.getProductClass()).get(0).attr("href"));
                                        
                String value = values.get(0).text();
                
                searchProduct(product, esite.getProductLink() ,esite);
                
                Timestamp ts = new Timestamp(System.currentTimeMillis());  
                Date date=new Date(ts.getTime());  
                
                Price price = registerPrice(value, date);
                
                SiteProductPrice spp = new SiteProductPrice(esite, product, price);
                siteProductPriceService.save(spp); 
                products.add(spp);
                
        	}
        }
    	
		return products;
	}
	
	@Transactional(readOnly = false)
	public Product searchProduct(Product product, String query, ExtendedSite esite) throws IOException{
		
		    product = findProduct(product.getName());
        		
        	Document doc = Jsoup.connect(query).get();                        
            
            product.setImg(doc.getElementsByClass(esite.getImgClass()).get(0).attr("href"));
                        
            product.setDescription(doc.getElementsByClass(esite.getDescriptionClass()).get(0).text());
			
    	
		return product;
	}
	
	
	/*
	
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
	
	*/
	
	
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
