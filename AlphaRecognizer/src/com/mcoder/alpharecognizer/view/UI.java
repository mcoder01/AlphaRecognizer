package com.mcoder.alpharecognizer.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UI extends JPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> names;

	public UI(LayoutManager layout) {
		super(layout);
		names = new ArrayList<>();
	}
	
	/**
	 * Creates the UI structure, using buttons and labels.
	 */
	public void create() {
		JTextField carattere = new JTextField();
		carattere.setName("char_field");
		carattere.setBounds(0, 0, 90, 30);
		add(carattere);
		
		JButton saveChar = new JButton("<html><body><p style=\"text-align: center;\">Salva immagine</p></body></html>");
		saveChar.setName("save_char_btn");
		saveChar.setBounds(0, 35, 90, 50);
		saveChar.setFocusable(false);
		add(saveChar);
		
		JLabel text = new JLabel("Carattere:");
		text.setFont(new Font("Calibri", Font.BOLD, 14));
		text.setBounds(0, getHeight()/2-55, 90, 20);
		add(text);
		
		JLabel guess = new JLabel("", JLabel.CENTER);
		guess.setName("guess_lbl");
		guess.setFont(new Font("Arial Black", Font.PLAIN, 48));
		guess.setBounds(0, getHeight()/2-35, 90, 90);
		guess.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(guess);
		
		JButton clear = new JButton("Pulisci");
		clear.setName("clear_btn");
		clear.setBounds(0, getHeight()-70, 90, 30);
		clear.setFocusable(false);
		add(clear);
		
		JButton saveBrain = new JButton("Salva AI");
		saveBrain.setName("save_brain_btn");
		saveBrain.setBounds(0, getHeight()-30, 90, 30);
		saveBrain.setFocusable(false);
		add(saveBrain);
	}
	
	@Override
	public Component add(Component comp) {
		names.add(comp.getName());
		return super.add(comp);
	}
	
	public Component getByName(String name) {
		for (Component c : getComponents())
			if (c.getName() != null && c.getName().equals(name))
				return c;
		return null;
	}
}