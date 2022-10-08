package com.mcoder.alpharecognizer.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import com.mcoder.alpharecognizer.Application;
import com.mcoder.alpharecognizer.util.ImageUtil;

public class TrainingData implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String path = "resources" + File.separator + "training";
	private static final DataManager manager = new DataManager(path);
	
	private BufferedImage image;
	private double[] inputs, targets;
	
	public TrainingData(BufferedImage image, char c) {
		this(fromImage(image, c));
	}
	
	public TrainingData(char c) throws ClassNotFoundException, IOException {
		this(manager.read(c + ".png"), c);
	}
	
	public TrainingData(TrainingData data) {
		this(data.image, data.inputs, data.targets);
	}
	
	public TrainingData(BufferedImage image, double[] inputs, double[] targets) {
		this.image = image;
		this.inputs = inputs;
		this.targets = targets;
	}
	
	public static TrainingData[] readAll() throws IOException {
		BufferedImage[] readData = manager.readAll();
		TrainingData[] trainSets = null;
		if (readData != null) {
			trainSets = new TrainingData[readData.length];
			for (int i = 0; i < readData.length; i++)
				trainSets[i] = fromImage(readData[i], Application.alfabeto.charAt(i));
		}
		
		return trainSets;
	}
	
	public static TrainingData fromImage(BufferedImage image, char c) {
		if (image != null) {
			double[] inputs = ImageUtil.toDoubleArray(image);
			double[] targets = new double[Application.alfabeto.length()];
			int index = Application.alfabeto.indexOf(c);
			targets[index] = 1;
			return new TrainingData(image, inputs, targets);
		}
		
		return null;
	}
	
	public void save(char c) throws IOException {
		save(image, c);
	}
	
	public static void save(BufferedImage image, char c) throws IOException {
		manager.save(image, c + ".png");
	}

	public double[] getInputs() {
		return inputs;
	}

	public double[] getTargets() {
		return targets;
	}
}