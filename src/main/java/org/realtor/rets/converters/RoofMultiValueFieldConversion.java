/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/RoofMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  RoofMultiValueFieldConversion.java Created Jul 31, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class RoofMultiValueFieldConversion extends BaseMultiValueFieldConversion {
    /* (non-Javadoc)
     * @see org.realtor.rets.converters.BaseMultiValueFieldConversion#initializeMappings()
     */
    public void initializeMappings() {
        addMapping("1", "Asphalt/Fiberglass");
        addMapping("D", "Slate");
        addMapping("E", "Tile");
        addMapping("F", "Shake");
        addMapping("I", "Rubber");
        addMapping("C", "Wood Shingles");
        addMapping("Y", "Other");
    }
}
