import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import helpers.Sentence;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomeScreen {

	private JFrame frame;
	private JTextField textField;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeScreen window = new WelcomeScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WelcomeScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(95, 29, 233, 53);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
		//		String test = j
				String a = textField.getText();
				Sentence sentence = new Sentence(a);
				try {
					sentence.preprocess();
				
				System.out.println("Sentence: " + sentence.getSentence());
				double value = sentence.getSentimentWordNet();
				System.out.println("Sentiment (SentiWordNet): " + value );
				String res = "";
				if(value>0)
				{
					res = "positive";
				}
				else
					res = "negative";
				label.setText(res);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(142, 110, 127, 35);
		frame.getContentPane().add(btnNewButton);
		
	    label = new JLabel("");
		label.setBounds(76, 184, 252, 41);
		frame.getContentPane().add(label);
	}
}
