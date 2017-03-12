/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/ViewDescriptionMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  ViewDescriptionMultiValueFieldConversion.java Created Jul 31, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class ViewDescriptionMultiValueFieldConversion
    extends BaseMultiValueFieldConversion {
    /* (non-Javadoc)
     * @see org.realtor.rets.converters.BaseMultiValueFieldConversion#initializeMappings()
     */
    public void initializeMappings() {
        addMapping("1", "Lake Erie");
        addMapping("C", "Golf Course");
        addMapping("E", "Canyon/Valley");
        addMapping("G", "Panoramic");
        addMapping("H", "Lake or River");
        addMapping("F", "City View");
        addMapping("I", "Wooded");
        addMapping("Y", "Other");
    }
}
