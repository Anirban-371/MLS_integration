/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/LockBoxMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  LockBoxMultiValueFieldConversion.java Created Jul 31, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class LockBoxMultiValueFieldConversion
    extends BaseMultiValueFieldConversion {
    public LockBoxMultiValueFieldConversion() {
        super();
        this.setMultiValue(false);
    }

    /* (non-Javadoc)
     * @see org.realtor.rets.converters.BaseMultiValueFieldConversion#initializeMappings()
     */
    public void initializeMappings() {
        addMapping("C", "Combination");
        addMapping("E", "Electronic");
        addMapping("K", "Key");
        addMapping(" ", "N/A");
    }
}
