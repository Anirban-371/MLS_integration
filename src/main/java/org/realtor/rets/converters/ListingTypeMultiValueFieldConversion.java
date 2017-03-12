/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/ListingTypeMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  ListingTypeMultiValueFieldConversion.java Created Jul 31, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class ListingTypeMultiValueFieldConversion
    extends BaseMultiValueFieldConversion {
    /**
     *
     */
    public ListingTypeMultiValueFieldConversion() {
        super();
        this.setMultiValue(false);
    }

    /* (non-Javadoc)
     * @see org.realtor.rets.converters.BaseMultiValueFieldConversion#initializeMappings()
     */
    public void initializeMappings() {
        addMapping("EA", "Exclusive Agency");
        addMapping("ERS", "Exclusive Right");
        addMapping("RP", "Exclusive w/Reserved Prospect");
        addMapping("OTH", "Other");
    }
}
