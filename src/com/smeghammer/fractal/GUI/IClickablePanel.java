package com.smeghammer.fractal.GUI;

import java.awt.image.BufferedImage;

public interface IClickablePanel {
	public BufferedImage getImageData() throws ClickablePanelException;
	public void setImageData(BufferedImage data) throws ClickablePanelException;
}
