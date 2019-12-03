package br.ufrn.minerin.shopminer;

import br.ufrn.minerin.framework.model.Site;
import br.ufrn.minerin.framework.service.SiteService;
import br.ufrn.minerin.framework.service.core.Attribute;
import br.ufrn.minerin.framework.service.core.QueryFactory;
import br.ufrn.minerin.shopminer.repository.ExtendedSiteRepository;
import br.ufrn.minerin.shopminer.service.PriceService;
import br.ufrn.minerin.shopminer.service.ProductService;
import br.ufrn.minerin.shopminer.service.SiteProductPriceService;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.minerin.shopminer.model.ExtendedSite;
import br.ufrn.minerin.shopminer.model.Price;
import br.ufrn.minerin.shopminer.model.Product;
import br.ufrn.minerin.shopminer.model.SiteProductPrice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SiteProductPriceFactory implements QueryFactory {

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

    @Override
    public List<Serializable> constructResult(List<Attribute<Elements>> attributes, Site site, String query) {

        //SiteProductPrice siteProductPrice = new SiteProductPrice();

        List<Serializable> products = new ArrayList<Serializable>();
        
        
        ExtendedSite esite = esr.findAllBySiteId(site.getId()).get(0);
        
        Product product = findProduct(query);
   
        SiteProductPrice spp = new SiteProductPrice();
        
        spp.setProduct(product);
        spp.setExtendedSite(esite);

        if (esite != null) {
        	
        	for ( Attribute a : attributes ) {
            	
        		Elements e = (Elements) a.getValue();
        		
        		if (a.getName().equals("TagClass")) {
        			Timestamp ts = new Timestamp(System.currentTimeMillis());  
                    Date date=new Date(ts.getTime());  
        			Price price = registerPrice(e.get(0).text(), date);
        			
        			spp.setPrice(price);
        		} else if (a.getName().equals("ProductClass")) {
        			esite.setProductLink(e.get(0).attr("href"));
        		}

            }
        	
        	products.add((SiteProductPrice) spp);	        
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
	
	
	
	private String processQuery(String query) {
		return query.replace(" ", "-");
	}
	
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
	
	private Price registerPrice(String value, Date date) {
		Price price = new Price();
		
		price.setValue(value);
		price.setDate(date);
		
		priceService.save(price);
		
		return price;
	}
}
