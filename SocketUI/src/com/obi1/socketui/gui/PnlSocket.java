package com.obi1.socketui.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class PnlSocket extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public PnlSocket() {
		setLayout(null);
		
		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(76, 76, 29, 16);
		add(lblIp);
		
		JRadioButton rdbtnListener = new JRadioButton("Listener");
		rdbtnListener.setBounds(117, 41, 78, 25);
		add(rdbtnListener);
		
		JRadioButton rdbtnConsumer = new JRadioButton("Consumer");
		rdbtnConsumer.setBounds(199, 41, 96, 25);
		add(rdbtnConsumer);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(351, 76, 37, 16);
		add(lblPort);
		
		JLabel lblDelimiter = new JLabel("Delimiter:");
		lblDelimiter.setBounds(49, 105, 56, 16);
		add(lblDelimiter);
		
		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setBounds(30, 324, 56, 16);
		add(lblMessage);
		
		textField = new JTextField();
		textField.setBounds(117, 73, 209, 22);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(387, 73, 116, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSocketName = new JLabel("Socket Name:");
		lblSocketName.setBounds(17, 13, 88, 16);
		add(lblSocketName);
		
		textField_2 = new JTextField();
		textField_2.setBounds(117, 10, 386, 22);
		add(textField_2);
		textField_2.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(117, 102, 209, 22);
		add(comboBox);
	}
}
