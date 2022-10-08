package com.mcoder.alpharecognizer.util;

import java.awt.image.BufferedImage;

public class ImageUtil {
	/**
	 * Converts the given image into an array of double
	 * containing RGB values of each pixel of the image
	 * @param image Image to convert
	 * @return Array of double containing RGB values
	 */
	public static double[] toDoubleArray(BufferedImage image) {
		int size = image.getWidth()*image.getHeight();
		double[] output = new double[size];
		for (int i = 0; i < size; i++) {
			int x = i%image.getWidth();
			int y = i/image.getWidth();
			output[i] = (double) image.getRGB(x, y);
		}
		return output;
	}
}