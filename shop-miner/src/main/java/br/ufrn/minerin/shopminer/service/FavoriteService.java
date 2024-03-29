package br.ufrn.minerin.shopminer.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.minerin.framework.model.Site;
import br.ufrn.minerin.framework.service.ScheduleService;
import br.ufrn.minerin.framework.service.SiteService;
import br.ufrn.minerin.framework.spec.MinerinConfig;
import br.ufrn.minerin.model.Favorite;
import br.ufrn.minerin.shopminer.model.Price;
import br.ufrn.minerin.shopminer.model.Product;
import br.ufrn.minerin.shopminer.model.SiteProductPrice;
import br.ufrn.minerin.repository.FavoriteRepository;
import br.ufrn.minerin.shopminer.ShopMinerStrategy;

@Service
@Transactional(readOnly = true)
public class FavoriteService {
	
	@Autowired
	private FavoriteRepository favoriteRepository;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FavoriteService favoriteService;
	
	@Autowired
	private SiteProductPriceService sppService;
	
	public List<Favorite> findAll() {
		return favoriteRepository.findAll();
	}
	
	public Optional<Favorite> findOne(Integer id) {
		return favoriteRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Favorite save(Favorite entity) {
		//MinerinConfig
		ShopMinerStrategy ssMinerStrategy = (ShopMinerStrategy)  MinerinConfig.getInstance().getSearchStrategy();
		ssMinerStrategy.addTask(entity);
		return favoriteRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Favorite entity) {
		favoriteRepository.delete(entity);
	}
	
	@Transactional(readOnly = false)
	public List<Price> findPrices(Integer id, Integer id_site){

		List<Price> prices =  new ArrayList<Price>();
		
		Site site = siteService.findOne(id_site).get();
		
		Favorite favorite = findOne(id).get();

		
		String product_name = favorite.getValue();

		Product product =  productService.findByName(product_name);

		
		List<SiteProductPrice> sppList = sppService.findAll();

		System.out.println(sppList.size());
		
		for (SiteProductPrice spp : sppList) {
			if (spp.getProduct().getId() == product.getId()
				&& spp.getSite().getId() == site.getId()) {
				
				prices.add(spp.getPrice());					
			}
		}
		
		Collections.sort(prices);
		
		return prices;
	}

}