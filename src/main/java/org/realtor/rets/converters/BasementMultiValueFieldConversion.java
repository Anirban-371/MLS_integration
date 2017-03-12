/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/BasementMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  BasementMultiValueFieldConversion.java Created Jul 31, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class BasementMultiValueFieldConversion
    extends BaseMultiValueFieldConversion {
    /* (non-Javadoc)
     * @see org.realtor.rets.converters.BaseMultiValueFieldConversion#initializeMappings()
     */
    public void initializeMappings() {
        addMapping("I", "Garage Access");
        addMapping("4", "Full Bath");
        addMapping("5", "Half Bath");
        addMapping("A", "Full");
        addMapping("B", "Partial");
        addMapping("C", "Crawl Space");
        addMapping("D", "Finished");
        addMapping("E", "Partially Finished");
        addMapping("F", "Unfinished");
        addMapping("G", "Walk-Out");
        addMapping("O", "Common");
        addMapping("Z", "None");
    }
}
