/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/StyleMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  StyleMultiValueFieldConverter.java Created Jul 31, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class StyleMultiValueFieldConversion
    extends BaseMultiValueFieldConversion {
    /* (non-Javadoc)
     * @see org.realtor.rets.converters.BaseMultiValueFieldConversion#initializeMappings()
     */
    public void initializeMappings() {
        addMapping("K", "Spanish");
        addMapping("C", "Victorian");
        addMapping("D", "Tudor");
        addMapping("H", "Cape Cod");
        addMapping("P", "Dutch Colonial");
        addMapping("S", "Other");
        addMapping("1", "Ranch");
        addMapping("2", "Bungalow");
        addMapping("3", "Colonial");
        addMapping("4", "Split Level");
        addMapping("5", "Bi-Level");
    }
}
