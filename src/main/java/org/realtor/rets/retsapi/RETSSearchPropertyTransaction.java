package org.realtor.rets.retsapi;

import org.apache.log4j.Category;


/**
 *        RETSSearchPropertyBatchTransaction.java
 *
 *        @author        jbrush
 *        @version 1.0
 */
public class RETSSearchPropertyTransaction extends RETSSearchTransaction {
    static Category cat = Category.getInstance(RETSSearchPropertyTransaction.class);

    public RETSSearchPropertyTransaction() {
        super();
        setSearchType("Property");
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return super.toString();
    }
}
