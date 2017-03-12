/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/BaseMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;

import org.apache.commons.collections.DoubleOrderedMap;

import org.apache.ojb.broker.accesslayer.conversions.ConversionException;

import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;


/**
 *  BaseMultiValueFieldConversion.java Created Jul 29, 2003
 *  Base class For multi value mappings. By default, this object will map each value and return a Vector, however
 *  you can change this behavior if you only have one value in the database by calling setMultiValue(false) in your subclass's
 *  constructor. This will make this object deal with strings instead of Vectors.
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public abstract class BaseMultiValueFieldConversion
    implements MultiValueFieldConversion {
    private DoubleOrderedMap mappings;
    protected String delimiter = ",";
    protected boolean multiValue = true;

    public BaseMultiValueFieldConversion() {
        mappings = new DoubleOrderedMap();
        initializeMappings();
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.broker.accesslayer.conversions.FieldConversion#javaToSql(java.lang.Object)
     */
    public Object javaToSql(Object java) throws ConversionException {
        if (java != null) {
            if (java instanceof Collection) {
                Collection coll = (Collection) java;
                Iterator iter = coll.iterator();
                StringBuffer sb = new StringBuffer();

                while (iter.hasNext()) {
                    String next = (String) iter.next();
                    String mapping = (String) mappings.getKeyForValue(next);

                    if (mapping != null) {
                        sb.append(mapping);
                    } else {
                        if (mappings.get(next) != null) {
                            sb.append(next);
                        } else {
                            throw new ConversionException("Value " + next +
                                " is not a valid coded value");
                        }
                    }

                    if (iter.hasNext()) {
                        sb.append(delimiter);
                    }
                }

                return sb.toString();
            } else {
                return mappings.getKeyForValue(java);
            }
        } else {
            return null;
        }
    }

    /* (non-Javadoc)
     * @see org.apache.ojb.broker.accesslayer.conversions.FieldConversion#sqlToJava(java.lang.Object)
     */
    public Object sqlToJava(Object sql) throws ConversionException {
        if (sql instanceof String) {
            String sqlStr = (String) sql;

            if (multiValue) {
                // we have several options in the database. map each one, and return a vector.
                Vector v = new Vector();

                // we need to account for the horrible data in the database, which might or might 
                // not have commas to separate the features. It also might have duplicate features
                // as well.
                if (sqlStr.indexOf(",") > 0) {
                    v = getCommaSeparatedValues(sqlStr);
                } else {
                    v = getNonCommaSeparatedValues(sqlStr);
                }

                return v;
            }

            return mappings.get(sql);
        } else {
            return null;
        }
    }

    private Vector getCommaSeparatedValues(String sqlStr) {
        Vector v = new Vector();
        StringTokenizer st = new StringTokenizer(sqlStr.replace('"', ' '), ",",
                false);

        // if we're a multi value option, return a vector, otherwise, return the first string from the DB
        // if there is more than one value in the database, either the data is wrong, 
        // or this needs to be a multi value option.
        while (st.hasMoreTokens()) {
            String next = st.nextToken();
            next = next.trim();

            if (next.trim().length() > 0) {
                if (mappings.get(next) != null) {
                    v.add(mappings.get(next));
                } else {
                    v.add(next);
                }
            }
        }

        return v;
    }

    private Vector getNonCommaSeparatedValues(String sqlStr) {
        Vector v = new Vector();

        for (int i = 0; i < sqlStr.length(); i++) {
            String featureSet = String.valueOf(sqlStr.charAt(i));
            String feature = (String) mappings.get(featureSet);

            if ((feature != null) && !v.contains(feature)) {
                v.add(feature);
            }
        }

        return v;
    }

    /**
     * To implement this method, call addMapping for each mapping you would
     * like to add.
     *
     */
    public abstract void initializeMappings();

    /**
     * @return
     */
    public boolean isMultiValue() {
        return multiValue;
    }

    /**
     * @param b
     */
    public void setMultiValue(boolean b) {
        multiValue = b;
    }

    /**
     *  Add a mapping to this object
     * @param dbValue The value as it appears in the database.
     * @param humanValue The Human readable value that the DB value maps to.
     */
    public void addMapping(String dbValue, String humanValue) {
        mappings.put(dbValue, humanValue);
    }

    protected DoubleOrderedMap getMappings() {
        return mappings;
    }

    /* (non-Javadoc)
     * @see org.realtor.rets.converters.MultiValueFieldConversion#getAllOptions()
     */
    public Vector getAllOptions() {
        Vector v = new Vector();
        v.addAll(mappings.keySet());

        return v;
    }
}
