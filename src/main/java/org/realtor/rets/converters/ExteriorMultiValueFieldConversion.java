/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/ExteriorMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  ExteriorMultiValueFieldConversion.java Created Jul 31, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class ExteriorMultiValueFieldConversion
    extends BaseMultiValueFieldConversion {
    /* (non-Javadoc)
     * @see org.realtor.rets.converters.BaseMultiValueFieldConversion#initializeMappings()
     */
    public void initializeMappings() {
        addMapping("A", "Porch");
        addMapping("B", "Patio");
        addMapping("C", "Deck");
        addMapping("D", "Balcony");
        addMapping("H", "Inground Pool");
        addMapping("I", "Aboveground Pool");
        addMapping("L", "Storage Shed/Out Building");
        addMapping("O", "Gazebo");
        addMapping("1", "Enclosed Patio/Porch");
        addMapping("N", "Landscaped");
        addMapping("Y", "Other");
    }
}
