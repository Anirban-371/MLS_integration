/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/PublicTransportationMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  PublicTransportationMultiValueFieldConversion.java Created Jul 31, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class PublicTransportationMultiValueFieldConversion
    extends BaseMultiValueFieldConversion {
    /* (non-Javadoc)
     * @see org.realtor.rets.converters.BaseMultiValueFieldConversion#initializeMappings()
     */
    public void initializeMappings() {
        addMapping("A", "Less than 1 Block");
        addMapping("B", "1-4 Blocks");
        addMapping("D", "Less than 1 Mile");
        addMapping("F", "Public Bus");
        addMapping("G", "Rapid Transit");
    }
}
