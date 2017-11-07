

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminDashboard extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashboard frame = new AdminDashboard();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminDashboard() {
		setFont(new Font("Arial Black", Font.BOLD, 12));
		setTitle("Admin Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Welcome Admin");
		label.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 14));
		label.setBounds(139, 22, 191, 62);
		contentPane.add(label);
		
		Label label_1 = new Label("Choose options to proceed");
		label_1.setBounds(139, 90, 270, 22);
		contentPane.add(label_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String selection = comboBox.getSelectedItem().toString();
				if(selection.equals("view and analyze sentiment"))
				{
					SentimentProcessorView s = new SentimentProcessorView();
				    s.setVisible(true);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				String selection = comboBox.getSelectedItem().toString();
				if(selection.equals("view and analyze sentiment"))
				{
					SentimentProcessorView s = new SentimentProcessorView();
				    s.setVisible(true);
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"available choices", "view and analyze sentiment", "view and analyse fake user"}));
		comboBox.setBounds(111, 144, 231, 20);
		contentPane.add(comboBox);
		
	}
}
