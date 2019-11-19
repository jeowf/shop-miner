package br.ufrn.shopminer.framework.service.core;

import java.io.Serializable;
import java.util.List;

public interface PersistStrategy {

	public void persistQueries(List<Serializable> queries);
}
