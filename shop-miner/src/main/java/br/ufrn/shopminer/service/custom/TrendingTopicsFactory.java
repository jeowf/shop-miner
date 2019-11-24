package br.ufrn.shopminer.service.custom;

import br.ufrn.shopminer.framework.model.Site;
import br.ufrn.shopminer.framework.service.SiteService;
import br.ufrn.shopminer.framework.service.core.Attribute;
import br.ufrn.shopminer.framework.service.core.QueryFactory;
import br.ufrn.shopminer.model.*;
import br.ufrn.shopminer.repository.ExtendedSiteRepository;
import br.ufrn.shopminer.service.PriceService;
import br.ufrn.shopminer.service.ProductService;
import br.ufrn.shopminer.service.SiteProductPriceService;
import br.ufrn.shopminer.service.TrendingTopicsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TrendingTopicsFactory implements QueryFactory {

    @Autowired
	TrendingTopicsService trendingTopicsService;

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

        //ExtendedSite esite = esr.findAllBySiteId(site.getId()).get(0);
        
        //Product product = findProduct(query);
   
        //SiteProductPrice  spp = new SiteProductPrice();
        
        //spp.setProduct(product);
        //products.add(spp);
        //spp.setExtendedSite(esite);

        //if (esite != null) {
        //
        //	for ( Attribute a : attributes ) {
        //
        //		Elements e = (Elements) a.getValue();
        //
        //		if (a.getName().equals("TagClass")) {
        //			Timestamp ts = new Timestamp(System.currentTimeMillis());
        //            Date date=new Date(ts.getTime());
        //			Price price = registerPrice(e.get(0).text(), date);
        //
        //			spp.setPrice(price);
        //		} else if (a.getName().equals("ProductClass")) {
        //			esite.setProductLink(e.get(0).attr("href"));
        //		}

        //

        //    }
        //
        //	products.add((SiteProductPrice) spp);
        //}

		List<Serializable> tts = new ArrayList<Serializable>();

		ArrayList<Element> els = attributes.get(0).getValue();

		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Date date=new Date(ts.getTime());

		for (Element e : els) {
			Timestamp ts_new = new Timestamp(ts.getTime());
			TrendingTopics trendingTopics = new TrendingTopics();

			ArrayList<Topic> topics = new ArrayList<>();

		    //e.getElementsByTag("li");

			int i = 0;
			for (Element e2 : e.getElementsByTag("li") ){
				Topic topic = new Topic();
				String subject = e2.text().replaceAll("[0-9]+K$","");
				topic.setSubject(subject);
				topic.setTrendingtopics(trendingTopics);
				topics.add(topic);
			}

			trendingTopics.setTimestamp(ts_new);
			trendingTopics.setLocation("worldwide");
			trendingTopics.setTopics(topics);

			tts.add(trendingTopics);
			ts.setHours(ts.getHours() - 1);
		}
		//return null;
		return tts;

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
