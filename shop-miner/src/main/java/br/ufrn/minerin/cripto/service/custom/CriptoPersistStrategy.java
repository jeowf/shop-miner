package br.ufrn.minerin.cripto.service.custom;

import br.ufrn.minerin.cripto.model.Coin;
import br.ufrn.minerin.cripto.model.MPrice;
import br.ufrn.minerin.cripto.model.Notification;
import br.ufrn.minerin.cripto.model.RTPrice;
import br.ufrn.minerin.cripto.service.CoinService;
import br.ufrn.minerin.cripto.service.MPriceService;
import br.ufrn.minerin.cripto.service.NotificationService;
import br.ufrn.minerin.cripto.service.RTPriceService;
import br.ufrn.minerin.framework.service.core.PersistStrategy;

import org.netlib.util.floatW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Primary
public class CriptoPersistStrategy implements PersistStrategy {

	@Autowired
	private RTPriceService rtPriceService;
	
	@Autowired
	private MPriceService mPriceService;
	
	@Autowired
	private CoinService coinService;
	
	@Autowired
	private NotificationService notificationService;

	@Override
	public void persistQueries(List<Serializable> queries) {

		int ce = 10;
		
		try {
			List<RTPrice> rtps = rtPriceService.findAll();
			
			if (rtps != null && rtps.size() > 5*10) {
				
				float mean = 0;
				int qtt = 0;
				
				Map<Coin, Float> means = new HashMap<Coin, Float>();
				
				for (RTPrice r : rtps) {
					if (!means.containsKey(r.getCoin())) {
						means.put(r.getCoin(), (float) 0);
					} 
					means.put(r.getCoin(), (means.get(r.getCoin()) + r.getValue())/ce );
					rtPriceService.delete(r);
				}
				
				List<Coin> coins = coinService.findAll();
				
				for (Coin coin : coins) {
					float v = means.get(coin);
					MPrice m = new MPrice();
					m.setCoin(coin);
					
					Timestamp ts = new Timestamp(System.currentTimeMillis());
					Calendar ca = Calendar.getInstance();
					Date date=new Date(ts.getTime());
					ca.setTime(date);
					
					m.setTimestamp(ca.getTime());
					m.setValue(v);
					
					mPriceService.save(m);
				}
				

				
				mean /= qtt;
			} else {
				for(Serializable query : queries){
					rtPriceService.save((RTPrice) query);
					RTPrice rtPrice = (RTPrice) query;
					Coin coin = rtPrice.getCoin();
					if (coin.getMin() != 0 || coin.getMax() != 0) {
						if (rtPrice.getValue() > coin.getMin() && rtPrice.getValue() < coin.getMax()) {
							Notification notification = new Notification();
							notification.setCoin(coin);
							notification.setTimestamp(rtPrice.getTimestamp());
							notification.setValue(rtPrice.getValue());
							
							notificationService.save(notification);
						}
					}
				}
			}
			
			
		}catch(Exception e) {
			System.out.println("Operação Inválida");
			e.printStackTrace();
		}

	}

}
