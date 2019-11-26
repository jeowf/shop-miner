package br.ufrn.minerin.framework.service.core;

import java.io.Serializable;
import java.util.List;

import org.jsoup.select.Elements;

import br.ufrn.minerin.framework.model.Site;

public interface QueryFactory {
	
    public abstract List<Serializable> constructResult(List<Attribute<Elements>> attributes, Site site, String query);
}
