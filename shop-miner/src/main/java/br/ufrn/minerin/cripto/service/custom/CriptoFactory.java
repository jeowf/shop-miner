package br.ufrn.minerin.cripto.service.custom;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.netlib.util.floatW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.ufrn.minerin.cripto.model.Coin;
import br.ufrn.minerin.cripto.model.RTPrice;
import br.ufrn.minerin.cripto.service.CoinService;
import br.ufrn.minerin.cripto.service.MPriceService;
import br.ufrn.minerin.cripto.service.RTPriceService;
import br.ufrn.minerin.framework.model.Site;
import br.ufrn.minerin.framework.service.core.Attribute;
import br.ufrn.minerin.framework.service.core.QueryFactory;
import net.bytebuddy.asm.Advice.Return;

@Component
@Primary
public class CriptoFactory implements QueryFactory {

	@Autowired
	private CoinService coinService;
	
	/*
	@Autowired
	private MPriceService mPriceService;
	 
	@Autowired
	private RTPriceService rtPriceService;

*/
    @Override
    public List<Serializable> constructResult(List<Attribute<Elements>> attributes, Site site, String query) {

		List<Serializable> rtps = new ArrayList<Serializable>();

		//System.out.println( attributes.get(1).getValue().get(0).text() );
		
		//ArrayList<Element> els = attributes.get(0).getValue();
		
		List<Element> names = attributes.get(0).getValue();
		List<Element> codes = attributes.get(1).getValue();
		List<Element> prices = attributes.get(2).getValue();
		
		//System.out.println(names.size() + " " + codes.size() + " " + prices.size());
		
		for (int i = 0; i < names.size(); i++) {
			
			String cod = codes.get(i).text();
			Coin c = coinService.findByCode(cod);
			
			if (c == null) {
				c = new Coin();
				c.setCod(cod);
				
				String name = names.get(i).text();
				c.setName(name);
				
				
				coinService.save(c);
			}
			
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			Calendar ca = Calendar.getInstance();
			Date date=new Date(ts.getTime());
			ca.setTime(date);
			
			RTPrice rtp = new RTPrice();
			rtp.setCoin(c);
			rtp.setTimestamp(ca.getTime());
			
			String v = prices.get(i).text().replace(".", "").replace(",", ".");
			float value = Float.parseFloat(v);
			
			rtp.setValue(value);
			
			rtps.add(rtp);
			
			//System.out.println(code);
			
			
		}
		
/*
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

			site.getTags().get(0).getName();
			trendingTopics.setTopics(topics);

			tts.add(trendingTopics);
			String hours = ts.toString().replaceAll("T",""); // 2019-12-03T03:51:43.478+0000
			c.add(Calendar.HOUR_OF_DAY, -1);
		}
    
		return tts;
		
		*/
		return rtps;
    }


}
