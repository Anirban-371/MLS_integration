/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/GarageMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  GarageMultiValueFieldConversion.java Created Jul 31, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class GarageMultiValueFieldConversion
    extends BaseMultiValueFieldConversion {
    /* (non-Javadoc)
     * @see org.realtor.rets.converters.BaseMultiValueFieldConversion#initializeMappings()
     */
    public void initializeMappings() {
        addMapping("1", "Paved");
        addMapping("A", "Attached");
        addMapping("B", "Detached");
        addMapping("C", "One Car");
        addMapping("D", "Two Car");
        addMapping("E", "Three or More Car");
        addMapping("G", "Carports");
        addMapping("I", "RV/ Boat Pad");
        addMapping("N", "Unit Garage");
        addMapping("L", "Assigned/Reserved");
        addMapping("7", "Heated");
        addMapping("5", "Access From Unit");
        addMapping("R", "Garage Door Opener");
        addMapping("Z", "None");
        addMapping("Y", "Other");
    }
}
