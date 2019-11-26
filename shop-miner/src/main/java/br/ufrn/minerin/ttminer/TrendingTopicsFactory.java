package br.ufrn.minerin.ttminer;

import br.ufrn.minerin.framework.model.Site;
import br.ufrn.minerin.framework.service.SiteService;
import br.ufrn.minerin.framework.service.core.Attribute;
import br.ufrn.minerin.framework.service.core.QueryFactory;
import br.ufrn.minerin.shopminer.repository.ExtendedSiteRepository;
import br.ufrn.minerin.shopminer.service.PriceService;
import br.ufrn.minerin.shopminer.service.ProductService;
import br.ufrn.minerin.shopminer.service.SiteProductPriceService;
import br.ufrn.minerin.shopminer.model.ExtendedSite;
import br.ufrn.minerin.shopminer.model.Price;
import br.ufrn.minerin.shopminer.model.Product;
import br.ufrn.minerin.ttminer.model.Topic;
import br.ufrn.minerin.ttminer.model.TrendingTopics;
import br.ufrn.minerin.ttminer.service.TrendingTopicsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
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

	//@Bean
	//TrendingTopicsFactory TrendingTopicsFactory(){
    //	return new TrendingTopicsFactory();
	//}
}
