/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/BooleanToVarcharFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;

import org.apache.ojb.broker.accesslayer.conversions.ConversionException;
import org.apache.ojb.broker.accesslayer.conversions.FieldConversion;


/**
 *  BooleanToVarcharFieldConversion.java Created Jul 24, 2003
 *  This object converts database values from their database values (either 'Y' , 'N' or null)
 *  to a Java Boolean object. Database values can be of any case, as long as they are a single character, which is
 *  a 'Y' or an 'N'. Null values (in the database) are interpreted as false.
 *  All values coming from java and going to the database will be represented by an upper case "Y" or
 *  an upper case "N".
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class BooleanToVarcharFieldConversion implements FieldConversion {
    /* (non-Javadoc)
     * @see org.apache.ojb.broker.accesslayer.conversions.FieldConversion#javaToSql(java.lang.Object)
     */
    public Object javaToSql(Object obj) throws ConversionException {
        if (obj instanceof Boolean) {
            Boolean column = (Boolean) obj;

            if (column.booleanValue()) {
                return "Y";
            } else {
                return "N";
            }
        } else {
            return obj;
        }
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.broker.accesslayer.conversions.FieldConversion#sqlToJava(java.lang.Object)
     */
    public Object sqlToJava(Object sql) throws ConversionException {
        if (sql instanceof String) {
            String boolString = (String) sql;

            if (boolString.toUpperCase().trim().equals("Y")) {
                return new Boolean(true);
            } else {
                return new Boolean(false);
            }
        } else {
            // it wasn't a string field, this is not correct.
            return sql;
        }
    }
}
