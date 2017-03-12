/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/FenceMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  FenceMultiValueFieldConversion.java Created Jul 29, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class FenceMultiValueFieldConversion
    extends BaseMultiValueFieldConversion {
    public FenceMultiValueFieldConversion() {
        super();
        setMultiValue(false);
    }

    /* (non-Javadoc)
     * @see org.realtor.rets.converters.BaseMultiValueFieldConversion#initializeMappings()
     */
    public void initializeMappings() {
        addMapping("1", "Invisible Pet");
        addMapping("A", "Chain Link");
        addMapping("B", "Wood");
        addMapping("C", "Rail");
        addMapping("D", "Full");
        addMapping("E", "Partial");
        addMapping("F", "Privacy");
        addMapping("G", "Brick/Stone");
        addMapping("Y", "Other");
    }
}
