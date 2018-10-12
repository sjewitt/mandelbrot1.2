/**
 * 
 */
package com.smeghammer.image;

import java.awt.Color;

/**
 * @author Silas
 *
 */
public class Palette implements IPalette {

	private Color[] palette;
	
	public Palette(){
		palette = new Color[255];
		
		int indexR = 255/7;
		int indexG = 255/6;
		int indexB = 255/6;
		
		int counter = 0;
		for(int i=0;i<7;i++)
		{
			for(int j=0;j<6;j++)
			{
				for(int k=0;k<6;k++)
				{
					this.palette[counter] = new Color(indexR*i,indexG*j,indexB*k);
					counter++;
				}
			}
		}
		System.out.println(counter);
	}
	
	@Override
	public Color getPaletteEntry(int index) throws PaletteException {
		return this.palette[index];
	}

}
