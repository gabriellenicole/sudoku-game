package sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class CurrencyConverter extends JFrame{
	
	private JTextField display;
	private JButton[] buttons = new JButton[10];
	private JButton CBtn, EBtn;
	private static String displayText = "";
	private String passcode;
	private boolean locked;
	public CurrencyConverter() {
		
		display = new JTextField(30);
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setEditable(false);
		display.setText("OPEN");
		
		JPanel tfPanel = new JPanel();
		tfPanel.setLayout(new FlowLayout());
		tfPanel.add(display);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(4,3));
		
		BtnListener listener = new BtnListener();
		for (int i=0; i<buttons.length; i++) {
			if(i<9) {
				buttons[i]=new JButton(String.valueOf((i+1)));
				btnPanel.add(buttons[i]);
			}
			else {
				buttons[i]=new JButton("0");
				btnPanel.add(buttons[i]);
			}
			buttons[i].addActionListener(listener);
			
		}
		
		CBtn = new JButton("C");
		EBtn = new JButton("E");
		
		btnPanel.add(CBtn);
		CBtn.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				display.setText("");
				displayText = "";
			}
		});
		locked = false;

		btnPanel.add(EBtn);
		EBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(locked==false) {
					passcode = displayText;
					display.setText("CODE SET");
					displayText ="";
					locked = true;
				}
				else {
					if(displayText.equals(passcode)==false) {
						display.setText("WRONG CODE");
						displayText = "";
						locked=true;
					}
					else {
						display.setText("OPEN");
						locked = false;
					}
				}
				
			}
		});
		
		
		setLayout(new BorderLayout());
		add(tfPanel, BorderLayout.NORTH);
		add(btnPanel, BorderLayout.CENTER);
		
		setTitle("Safe Box");
		setSize(300,400);
		setVisible(true);
		
	}
	
	public class BtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String btnLabel = e.getActionCommand();
			displayText += btnLabel;
			display.setText(displayText);
		}
	}
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new CurrencyConverter();
			}
		});
	}
}