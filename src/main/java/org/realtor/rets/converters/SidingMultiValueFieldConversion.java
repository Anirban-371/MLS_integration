/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/SidingMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  SidingMultiValueFieldConversion.java Created Jul 31, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class SidingMultiValueFieldConversion
    extends BaseMultiValueFieldConversion {
    /* (non-Javadoc)
     * @see org.realtor.rets.converters.BaseMultiValueFieldConversion#initializeMappings()
     */
    public void initializeMappings() {
        addMapping("1", "Log");
        addMapping("A", "Aluminum");
        addMapping("D", "Brick");
        addMapping("E", "Cedar");
        addMapping("F", "Wood");
        addMapping("I", "Stucco");
        addMapping("J", "Vinyl");
        addMapping("K", "Stone");
        addMapping("Y", "Other");
    }
}
