/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/AppliancesMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  AppliancesMultiValueFieldConversion.java Created Jul 31, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class AppliancesMultiValueFieldConversion
    extends BaseMultiValueFieldConversion {
    /* (non-Javadoc)
     * @see org.realtor.rets.converters.BaseMultiValueFieldConversion#initializeMappings()
     */
    public void initializeMappings() {
        addMapping("A", "Range");
        addMapping("B", "Countertop Range");
        addMapping("C", "Wall Oven");
        addMapping("D", "Indoor Grill");
        addMapping("E", "Microwave");
        addMapping("F", "Dishwasher");
        addMapping("G", "Disposal");
        addMapping("I", "Refrigerator");
        addMapping("J", "Freezer");
        addMapping("L", "Washer");
        addMapping("M", "Dryer");
        addMapping("N", "Water Softener");
        addMapping("O", "Cable Electric Link");
        addMapping("2", "Central Vacuum");
        addMapping("X", "Dehumidifier");
        addMapping("W", "Electric Air Cleaner");
        addMapping("7", "Humidifier");
        addMapping("1", "Satellite Dish");
        addMapping("6", "Sauna/Steam Room");
        addMapping("5", "Security System");
        addMapping("3", "Smoke Detector");
        addMapping("R", "Sump Pump");
        addMapping("P", "Wet Bar");
        addMapping("4", "Whirlpool/Hot Tub");
        addMapping("S", "Gas Stove Connection");
        addMapping("U", "Electric Stove Connection");
        addMapping("Q", "Washer Connection");
        addMapping("T", "Gas Dryer Connection");
        addMapping("V", "Electric Dryer Connection");
        addMapping("8", "Carbon Monoxide Detector");
        addMapping("Z", "None");
        addMapping("Y", "Other");
    }
}
