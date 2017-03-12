/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/OccupiedByMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  OccupiedByMultiValueFieldConversion.java Created Jul 31, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class OccupiedByMultiValueFieldConversion
    extends BaseMultiValueFieldConversion {
    public OccupiedByMultiValueFieldConversion() {
        super();
        this.setMultiValue(false);
    }

    public void initializeMappings() {
        addMapping("O", "Owner");
        addMapping("T", "Tenant");
        addMapping("V", "Vacant");
    }
}
