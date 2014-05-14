package View;

import java.awt.*;
import javax.swing.*;

import Model.*;
import Controller.*;

public class FramePrincipale extends JFrame {

	public static void main(String[] args0) {
		
		JFrame f = new JFrame("Simple encryption and decryption");
		JPanel panel = new PanelPrincipaleGridLayout(); 
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = f.getContentPane();
		c.add(panel);
		f.setVisible(true);
		f.setSize(600, 200);
		f.setLocation(300, 150);
		
	
	}

}