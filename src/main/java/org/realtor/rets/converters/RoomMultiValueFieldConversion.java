/* $Header: /usr/local/cvsroot/rets/commons/src/org/realtor/rets/converters/RoomMultiValueFieldConversion.java,v 1.2 2003/12/04 15:27:03 rsegelman Exp $  */
package org.realtor.rets.converters;


/**
 *  RoomMultiValueFieldConversion.java Created Jul 31, 2003
 *
 *
 *  Copyright 2003, Avantia inc.
 *  @version $Revision: 1.2 $
 *  @author scohen
 */
public class RoomMultiValueFieldConversion extends BaseMultiValueFieldConversion {
    /* (non-Javadoc)
     * @see org.realtor.rets.converters.BaseMultiValueFieldConversion#initializeMappings()
     */
    public void initializeMappings() {
        addMapping("R", "Steam/Sauna");
        addMapping("A", "Fireplace");
        addMapping("C", "Skylight");
        addMapping("D", "CeilingFans");
        addMapping("E", "Walk-in Closet");
        addMapping("G", "Hard Wood Floor");
        addMapping("H", "Ceramic/Slate Tile Floor");
        addMapping("I", "Vinyl Flooring");
        addMapping("J", "Window Treatment");
        addMapping("K", "Wall to Wall Carpet");
        addMapping("N", "Balcony/Deck");
        addMapping("P", "Hot Tub/Spa");
        addMapping("S", "Beamed Ceiling");
        addMapping("T", "Country Kitchen");
        addMapping("U", "Vaulted Ceiling");
    }
}
