package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import Model.*;
import Controller.*;

public class PanelPrincipaleGridLayout extends JPanel implements ActionListener, KeyListener {

	private JLabel inputLabel, outputLabel;
	private JButton encryptButton, decryptButton;
	private JTextField inputTextField, outputTextField;
	private InputOutputController iocVuoto, ioc;
	private Dizionario dizionarioLetto;
	
	// Layout of Panel
	public PanelPrincipaleGridLayout(){
		
		// add InputOutputControllers
		iocVuoto = new InputOutputController("Dizionario_cipher.txt", "Output.txt"); 				// create IOC without dictionary
		dizionarioLetto = iocVuoto.leggiDizionario();												// Read dictionary
		ioc = new InputOutputController(dizionarioLetto, "Dizionario_cipher.txt", "Output.txt");	// create new IOC with dictionary

		// fonts
		Font fontLabel = new Font("Arial", Font.BOLD, 36);
		Font fontButton = new Font("Arial", Font.PLAIN, 30);
		Font fontText = new Font("Arial", Font.PLAIN, 22);

	
		// Main Panel
		LayoutManager man = new GridLayout(2,3,50,25);     	// I had problems with setting element sizes, relative positions, etc, 
		this.setLayout(man);								// so I added internal space between elements --> not ideal!
					
		
		// define panel elements
		//
		// UPPER LEFT - Input label
		inputLabel = new JLabel("   INPUT");				// problems centering labels, so I centered this label "by hand"
		inputLabel.setForeground(Color.blue);		
		inputLabel.setFont(fontLabel);
		this.add(inputLabel);

		// UPPER CENTER - Encrypt button
		encryptButton = new JButton("Encrypt");
		encryptButton.setForeground(Color.green);		
		encryptButton.setFont(fontButton);
		this.add(encryptButton);
		
		// UPPER RIGHT - Output label
		outputLabel = new JLabel("OUTPUT");
		outputLabel.setForeground(Color.blue);		
		outputLabel.setFont(fontLabel);
		this.add(outputLabel);

		// LOWER LEFT - Input text
		inputTextField = new JTextField("Insert text here");
		inputTextField.setForeground(Color.black);
		inputTextField.setFont(fontText);
		this.add(inputTextField);

		// LOWER CENTER - Encrypt button
		decryptButton = new JButton("Decrypt");
		decryptButton.setForeground(Color.red);		
		decryptButton.setFont(fontButton);
		this.add(decryptButton);
		
		// LOWER RIGHT - Output label
		outputTextField = new JTextField("");
		outputTextField.setEditable(false);
		outputTextField.setBackground(Color.white);
		outputTextField.setForeground(Color.black);			// output text is black (like the input text) before encryption/decryption
		outputTextField.setFont(fontText);
		this.add(outputTextField);
		
		
				
		// define the event launchers
		encryptButton.addActionListener(this);   	
		decryptButton.addActionListener(this);   	
		inputTextField.addKeyListener(this); 	
	}

	
	// Event/action management
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton tempButton = (JButton) arg0.getSource(); 
		
		String parolaInput = inputTextField.getText();
		String parolaOutput = "";
		String tipo = "";
		
		if ( !parolaInput.equals("") ){
			if ( tempButton.equals(encryptButton) ){
				parolaOutput = ioc.criptaParola(parolaInput);
				tipo = "ENCRYPTED";
				outputTextField.setForeground(Color.green);		// when a word is encrypted, the output changes colour (green) to match the Encrypt button text colour
			} else {
				parolaOutput = ioc.decriptaParola(parolaInput);
				tipo = "DECRYPTED";
				outputTextField.setForeground(Color.red);		// when a word is decrypted, the output changes colour (red) to match the Decrypt button text colour
			}
			outputTextField.setText(parolaOutput);
			ioc.scriviLog(parolaInput, parolaOutput, tipo);
		}
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		outputTextField.setForeground(Color.black);				// when the input text is modified or deleted, the output text changes colour to black 
		outputTextField.setText(inputTextField.getText());		// and matches the input text
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
	
