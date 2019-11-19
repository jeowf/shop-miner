package br.ufrn.shopminer.framework.service.core;

import br.ufrn.shopminer.framework.model.Site;
import br.ufrn.shopminer.model.*;

import java.io.Serializable;
import java.util.List;

public class SiteProductPriceFactory extends QueryFactory {

    public SiteProductPriceFactory(List<Attribute> attributes) {
        super(attributes);
    }

    @Override
    public Serializable constructResult() {

        SiteProductPrice siteProductPrice = new SiteProductPrice();

        Site site = new Site();
        for ( Attribute a : attributes ) {
            if ( a.getName().equals("Site") || a.getValue().getClass() == ExtendedSite.class ){
                siteProductPrice.setSite( (ExtendedSite) a.getValue() );
            }

            else if ( a.getName().equals("Product") || a.getValue().getClass() == Product.class ){
                siteProductPrice.setProduct( (Product) a.getValue() );
            }

            else if ( a.getName().equals("Price") || a.getValue().getClass() == Price.class ){
                siteProductPrice.setPrice( (Price) a.getValue() );
            }

            //else if ( a.getName().equals("name") ) {
            //    site.setName( (String) a.getValue() );
            //}

            //else if ( a.getName().equals("url")) {
            //    site.setUrl( (String) a.getValue() );
            //}

            //else if ( a.getName().equals("class") ) {
            //    site.setTagClass( (String) a.getValue() );
            //}
            //
            //else if ( a.getName().equals("pclass") ) {
            //    site.setProductClass( (String) a.getValue() );
            //}

            //else if ( a.getName().equals("dclass") ) {
            //    site.setDescriptionClass( (String) a.getValue() );
            //}

            //else if ( a.getName().equals("productLink") ) {
            //    site.setProductLink ( (String) a.getValue() );
            //}

            //else if ( a.getName().equals("Config") || a.getValue().getClass() == Config.class ){
            //    site.setConfig( (Config) a.getValue() );
            //}

        }


        return siteProductPrice;
    }
}
