package br.ufrn.shopminer.framework.service.core;

import java.io.Serializable;
import java.util.List;

import org.jsoup.select.Elements;

import br.ufrn.shopminer.framework.model.Site;

public interface QueryFactory {
	
    public abstract List<Serializable> constructResult(List<Attribute<Elements>> attributes, Site site, String query);
}
