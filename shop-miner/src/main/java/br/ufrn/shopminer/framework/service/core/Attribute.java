package br.ufrn.shopminer.framework.service.core;

public class Attribute<T> {
    private String name;
    private T value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

	public Attribute(String name, T value) {
		super();
		this.name = name;
		this.value = value;
	}
    
    
}
