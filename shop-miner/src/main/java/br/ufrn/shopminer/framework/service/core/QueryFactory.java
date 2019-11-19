package br.ufrn.shopminer.framework.service.core;

import java.io.Serializable;
import java.util.List;

public abstract class QueryFactory {
    protected List<Attribute> attributes;
    public QueryFactory(List<Attribute> attributes) {
        this.attributes= attributes;
    }
    public abstract Serializable constructResult();
}
