import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

public class PressMeIfYouCan {

	private static double x,y;
	
	private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
	private static final int WIDTH = dim.width; 
	private static final int HEIGHT = dim.height;
	
	private static JFrame window = new JFrame("");
	
	private static double randomizeX() {
		return (Math.random() * (WIDTH - window.getWidth()));
	}
	
	private static double randomizeY() {
		return (Math.random() * (HEIGHT - window.getHeight()));
	}
	
	public static void main(String[] args) {
	
		window.setUndecorated(true);	// AGREGAR ESTO AL RESUMEN
		window.setResizable(false);
		window.setAlwaysOnTop(true);
		
		JButton boton = new JButton("Press me if you can!");
		
		boton.setBorder(BorderFactory.createRaisedBevelBorder());
		boton.setBackground(Color.LIGHT_GRAY);
		boton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		
		window.add(boton, BorderLayout.CENTER);
		
		window.setSize(200, 80);
		window.setLocationRelativeTo(null);
		// window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		boton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {	}

			public void mouseEntered(MouseEvent e) {
				
				int mouseX = e.getXOnScreen();
				int mouseY = e.getYOnScreen();
				
				/* System.out.println("X Mouse: " + mouseX + " Y Mouse: " + mouseY); */
				Point mousePos = new Point(mouseX, mouseY);
				
				double xStart = window.getLocation().x;
				double yStart = window.getLocation().y;
				Rectangle bounds;
				
				do {
					x = randomizeX();
					y = randomizeY();
					
					bounds = new Rectangle((int) x, (int) y, window.getWidth(), window.getHeight());
				} while (bounds.contains(mousePos));
				
				// System.out.println("Destino: \nX: " + x + "\nY: " + y);
				
				double xVel = (x - xStart) * 0.005;
				double yVel = (y - yStart) * 0.005;
				
				while (window.getLocation().x != (int) x && window.getLocation().y != (int) y) {
					xStart += xVel;
					yStart += yVel;
					window.setLocation((int) xStart, (int) yStart);
				}
				
				/* System.out.println("Llegada: \nX: " + window.getLocation().x + "\nY: " + 
										window.getLocation().y); */
				
			}

			public void mouseExited(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		
		window.setVisible(true);
	}
}
