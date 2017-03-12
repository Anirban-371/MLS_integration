/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/WaterMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  WaterMultiValueFieldConverter.java Created Jul 31, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class WaterMultiValueFieldConversion
    extends BaseMultiValueFieldConversion {
    /* (non-Javadoc)
     * @see org.realtor.rets.converters.BaseMultiValueFieldConversion#initializeMappings()
     */
    public void initializeMappings() {
        addMapping("E", "Public Water");
        addMapping("F", "Private Water");
        addMapping("G", "Well");
        addMapping("H", "Water Treatment");
        addMapping("A", "Public Sewer");
        addMapping("B", "Private Sewer");
        addMapping("D", "Septic");
        addMapping("1", "Storm Sewer");
        addMapping("2", "Sanitary Sewer");
        addMapping("3", "Aeration System");
        addMapping("Y", "Other");
    }
}
