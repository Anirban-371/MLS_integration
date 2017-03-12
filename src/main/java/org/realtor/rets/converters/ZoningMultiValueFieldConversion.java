/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/ZoningMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  ZoningMultiValueFieldConversion.java Created Jul 31, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class ZoningMultiValueFieldConversion
    extends BaseMultiValueFieldConversion {
    /* (non-Javadoc)
     * @see org.realtor.rets.converters.BaseMultiValueFieldConversion#initializeMappings()
     */
    public void initializeMappings() {
        addMapping("C", "Commercial");
        addMapping("D", "Industrial");
        addMapping("A", "Residential");
        addMapping("B", "Multi-Family");
        addMapping("F", "Agricultural");
        addMapping("G", "Apartments");
        addMapping("H", "Condo");
        addMapping("I", "Offices");
        addMapping("J", "Retail");
        addMapping("E", "Other");
    }
}
