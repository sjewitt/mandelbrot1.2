/**
 * 
 */
package com.smeghammer.image;

import java.awt.Color;

/**
 * @author Silas
 *
 */
public interface IPalette {
	public Color getPaletteEntry(int index) throws PaletteException;
}
