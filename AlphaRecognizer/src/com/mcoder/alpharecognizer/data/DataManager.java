package com.mcoder.alpharecognizer.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DataManager {
	private final String path;
	
	public DataManager(String path) {
		this.path = path;
	}
	
	/**
	 * Reads all the images in the folder
	 * @return The array of BufferedImage containing the read images
	 * @throws IOException When the I/O operation fails
	 */
	public BufferedImage[] readAll() throws IOException {
		File dir = new File(path);
		File[] files = dir.listFiles();
		BufferedImage[] readData = null;
		if (files != null) {
			readData = new BufferedImage[files.length];
			for (int i = 0; i < files.length; i++)
				readData[i] = read(files[i].getName());
		}
		
		return readData;
	}
	
	/**
	 * Reads a single image from the given file path
	 * @param fileName The file path from where loading the image
	 * @return The BufferedImage object associated to the loaded image
	 * @throws IOException When the I/O operation fails
	 */
	public BufferedImage read(String fileName) throws IOException {
		File file = new File(path + File.separator + fileName);
		if (file.exists())
			return ImageIO.read(file);
		return null;
	}
	
	/**
	 * Saves the given image into the default folder 
	 * of the file system and with the given name
	 * @param data Image to save
	 * @param name Name of the resulting file
	 * @throws IOException When the I/O operation fails
	 */
	public void save(BufferedImage data, String name) throws IOException {
		File f = new File(path + File.separator + name);
		ImageIO.write(data, "png", f);
	}
}