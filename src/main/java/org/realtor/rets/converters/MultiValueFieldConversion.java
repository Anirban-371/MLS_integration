/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/MultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;

import org.apache.ojb.broker.accesslayer.conversions.FieldConversion;

import java.util.Vector;


/**
 *  MultiValueFieldConversion.java Created Aug 5, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public interface MultiValueFieldConversion extends FieldConversion {
    public Vector getAllOptions();
}
