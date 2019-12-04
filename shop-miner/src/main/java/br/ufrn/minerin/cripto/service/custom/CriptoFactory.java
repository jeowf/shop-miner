package br.ufrn.minerin.cripto.service.custom;

import java.io.Serializable;
import java.util.List;

import org.jsoup.select.Elements;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.ufrn.minerin.framework.model.Site;
import br.ufrn.minerin.framework.service.core.Attribute;
import br.ufrn.minerin.framework.service.core.QueryFactory;

@Component
@Primary
public class CriptoFactory implements QueryFactory {

	@Override
	public List<Serializable> constructResult(List<Attribute<Elements>> attributes, Site site, String query) {
		
		
		
		
		return null;
	}

}
