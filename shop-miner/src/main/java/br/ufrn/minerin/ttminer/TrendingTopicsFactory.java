package br.ufrn.minerin.ttminer;

import br.ufrn.minerin.framework.model.Site;
import br.ufrn.minerin.framework.service.SiteService;
import br.ufrn.minerin.framework.service.core.Attribute;
import br.ufrn.minerin.framework.service.core.QueryFactory;
import br.ufrn.minerin.shopminer.repository.ExtendedSiteRepository;
import br.ufrn.minerin.shopminer.service.PriceService;
import br.ufrn.minerin.shopminer.service.ProductService;
import br.ufrn.minerin.shopminer.service.SiteProductPriceService;
import br.ufrn.minerin.ttminer.model.Topic;
import br.ufrn.minerin.ttminer.model.TrendingTopics;
import br.ufrn.minerin.ttminer.service.TrendingTopicsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
//@Primary
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
		Calendar c = Calendar.getInstance();
		Date date=new Date(ts.getTime());
		c.setTime(date);

		for (Element e : els) {
			TrendingTopics trendingTopics = new TrendingTopics();
			trendingTopics.setTimestamp(c.getTime());

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

			trendingTopics.setLocation(site.getTags().get(0).getName().replaceAll(".*/",""));
			site.getTags().get(0).getName();
			trendingTopics.setTopics(topics);

			tts.add(trendingTopics);
			String hours = ts.toString().replaceAll("T",""); // 2019-12-03T03:51:43.478+0000
			c.add(Calendar.HOUR_OF_DAY, -1);
		}
		return tts;

    }

	private String processQuery(String query) {
		return query.replace(" ", "-");
	}
	
}
