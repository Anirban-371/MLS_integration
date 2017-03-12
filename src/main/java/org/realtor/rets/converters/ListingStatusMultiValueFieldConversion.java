/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/ListingStatusMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  ListingStatusMultiValueFieldConversion.java Created Jul 31, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class ListingStatusMultiValueFieldConversion
    extends BaseMultiValueFieldConversion {
    public ListingStatusMultiValueFieldConversion() {
        super();
        this.setMultiValue(false);
    }

    /* (non-Javadoc)
     * @see org.realtor.rets.converters.BaseMultiValueFieldConversion#initializeMappings()
     */
    public void initializeMappings() {
        addMapping("ACT", "Active");
        addMapping("PEND", "Pending");
        addMapping("CLOSD", "Closed");
        addMapping("EXP", "Expired");
        addMapping("CONT", "Contingency");
        addMapping("WITH", "Withdrawn");
        addMapping("OFMKT", "Off Market");
        addMapping("RENT", "Rented");
    }
}
