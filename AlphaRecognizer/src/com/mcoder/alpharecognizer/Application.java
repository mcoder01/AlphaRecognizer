package com.mcoder.alpharecognizer;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mcoder.alpharecognizer.data.TrainingData;
import com.mcoder.alpharecognizer.util.ImageUtil;
import com.mcoder.alpharecognizer.view.Board;
import com.mcoder.alpharecognizer.view.UI;

import manuel.ai.neuralnetwork.MatrixNeuralNetwork;

public class Application extends JPanel {
	public static final String alfabeto = "abcdefghijklmnopqrstuvwxyz0123456789";
	
	private JFrame frame;
	private Board board;
	private UI ui;
	private JLabel guess;
	
	private MatrixNeuralNetwork network;
	private TrainingData[] trainSets;
	
	{ // Initializer
		// Loads the training set from the file system
		try {
			trainSets = TrainingData.readAll();
		} catch (IOException e2) {
			System.out.println("Impossibile leggere i dati di allenamento!");
			e2.printStackTrace();
		}
	}
	
	public Application(JFrame frame) {
		super(null);
		this.frame = frame;
		
		board = new Board(25, 25, 30, 30, 450, 450);
		int inputNodes = board.getRows()*board.getCols();
		network = new MatrixNeuralNetwork(inputNodes, alfabeto.length()); // Creates the neural network
		setup();
	}

	private void setup() {
		setPreferredSize(new Dimension(Window.width, Window.height));
		
		// Adds the board to the screen
		board.setup();
		board.setOnWrite(this::transcript);
		frame.addMouseMotionListener(board);
		frame.addMouseListener(board);
		add(board);
		
		// Adds the UI to the screen
		ui = new UI(null);
		ui.setBounds(490, 25, 90, 450);
		ui.create();
		add(ui);
		
		// Assigns corresponding actions to UI buttons
		JButton salvaSet = (JButton) ui.getByName("save_char_btn");
		salvaSet.addActionListener(e -> {
			JTextField carattere = (JTextField) ui.getByName("char_field");
			if (carattere.getText().length() == 1) {
				char c = Character.toUpperCase(carattere.getText().charAt(0));
				try {
					TrainingData.save(board.getImage(), c);
				} catch (IOException e1) {
					System.out.println("Impossibile salvare il set di allenamento!");
					e1.printStackTrace();
				}
			}
		});
		
		guess = (JLabel) ui.getByName("guess_lbl");
		
		JButton clear = (JButton) ui.getByName("clear_btn");
		clear.addActionListener(e -> {
			board.fill(Color.WHITE);
			transcript();
		});
		
		JButton salvaAI = (JButton) ui.getByName("save_brain_btn");
		salvaAI.addActionListener(e -> {
			try {
				network.save("AI.dat");
			} catch (IOException e1) {
				System.out.println("Impossibile salvare l'AI!");
				e1.printStackTrace();
			}
		});
		ui.repaint();
	}

	private void transcript() {
		if (trainSets != null && trainSets.length > 0)
			// Train the network 10 times, using the loaded training set
			for (int i = 0; i < 10; i++) {
				int r = (int) (Math.random()*trainSets.length);
				network.train(trainSets[r].getInputs(), trainSets[r].getTargets());
			}
		
		if (!board.isEmpty()) {
			// Tries to guess the written character
			double[] inputs = ImageUtil.toDoubleArray(board.getImage());
			double[] outputs = network.predict(inputs);
			int pmax = 0;
			for (int i = 1; i < outputs.length; i++)
				if (outputs[i] > outputs[pmax]) pmax = i;
			
			char c = Character.toUpperCase(alfabeto.charAt(pmax));
			guess.setText("<html><body><p style=\"text-align: center;\">" + c + "</p></body></html>");
		} else guess.setText("");
	}
}