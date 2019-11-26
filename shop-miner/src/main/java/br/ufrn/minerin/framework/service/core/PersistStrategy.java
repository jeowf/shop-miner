package br.ufrn.minerin.framework.service.core;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public interface PersistStrategy {

	public void persistQueries(List<Serializable> queries);

}
