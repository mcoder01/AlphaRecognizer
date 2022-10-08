package com.mcoder.alpharecognizer;

import javax.swing.JFrame;

public class Window {
	public final static int width = 600, height = 500;
	private static JFrame frame;

	public static void main(String[] args) {
		frame = new JFrame("AlphaRecognizer");
		frame.add(new Application(frame));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static JFrame getFrame() {
		return frame;
	}
}