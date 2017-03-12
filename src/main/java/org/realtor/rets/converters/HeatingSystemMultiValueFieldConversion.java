/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/HeatingSystemMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  HeatingTypeMultiValueFieldConversion.java Created Jul 31, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class HeatingSystemMultiValueFieldConversion
    extends BaseMultiValueFieldConversion {
    /* (non-Javadoc)
     * @see org.realtor.rets.converters.BaseMultiValueFieldConversion#initializeMappings()
     */
    public void initializeMappings() {
        addMapping("A", "Heat Pump");
        addMapping("B", "Central Heat");
        addMapping("C", "Forced Air");
        addMapping("D", "Radiators");
        addMapping("F", "Baseboard");
        addMapping("G", "Space Heaters");
        addMapping("H", "Zoned");
        addMapping("I", "Gravity");
        addMapping("J", "Radiant");
        addMapping("L", "Geothermal");
        addMapping("Z", "None");
        addMapping("Y", "Other");
    }
}
