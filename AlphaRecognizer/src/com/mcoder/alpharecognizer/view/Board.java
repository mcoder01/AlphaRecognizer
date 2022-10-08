package com.mcoder.alpharecognizer.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.mcoder.alpharecognizer.Window;

public class Board extends JPanel implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image;
	private int x, y, rows, cols;
	private int pi, pj, button;
	
	private Runnable onWrite;
	
	public Board(int x, int y, int rows, int cols, int width, int height) {
		super(null);
		this.x = x;
		this.y = y;
		this.rows = rows;
		this.cols = cols;
		
		image = new BufferedImage(rows, cols, BufferedImage.TYPE_INT_RGB);
		setBounds(x-1, y-1, width+2, height+2);
	}

	public void setup() {
		resetPointer();
		fill(Color.WHITE);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect(0, 0, getWidth()+2, getHeight()+2);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
	
	private void resetPointer() {
		pi = -1;
		pj = -1;
	}
	
	/**
	 * Colors in black the spot pointed by the mouse 
	 * @param mouseX x-coordinate on the window of the mouse
	 * @param mouseY y-coordinate on the window of the mouse
	 */
	private void write(int mouseX, int mouseY) {
		if (mouseX >= x && mouseX < x+getWidth()
				&& mouseY >= y && mouseY < y+getHeight()) {
			
			double dx = (double) mouseX-x;
			double dy = (double) mouseY-y;
			int i = (int) (dy/getHeight()*rows);
			int j = (int) (dx/getWidth()*cols);
			if (i != pi || j != pj) {
				if (button == MouseEvent.BUTTON1)
					image.setRGB(j, i, Color.BLACK.getRGB());
				else if (button == MouseEvent.BUTTON3)
					image.setRGB(j, i, Color.WHITE.getRGB());
				repaint();
				pi = i;
				pj = j;
			}
		}
	}
	
	/**
	 * Fills the image with the given color
	 * @param c Color to fill the image to
	 */
	public void fill(Color c) {
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				image.setRGB(j, i, c.getRGB());
		repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		button = e.getButton();
		mouseDragged(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int mouseX = e.getX()-Window.getFrame().getInsets().left;
		int mouseY = e.getY()-Window.getFrame().getInsets().top;
		write(mouseX, mouseY);
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		resetPointer();
		onWrite.run(); // Execute something after writing
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}
	
	/**
	 * Checks if the board is empty
	 * @return true if the board is all empty, false otherwise
	 */
	public boolean isEmpty() {
		for (int x = 0; x < image.getWidth(); x++)
			for (int y = 0; y < image.getHeight(); y++)
				if (image.getRGB(x, y) != Color.WHITE.getRGB())
					return false;
		return true;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}
	
	public void setOnWrite(Runnable onWrite) {
		this.onWrite = onWrite;
	}
}