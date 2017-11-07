import java.awt.BorderLayout;
import java.awt.EventQueue;
import Controller.SentimentProcessorController;
import DBUtils.CommentContract;
import Model.Comment;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SentimentProcessorView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel model = new DefaultTableModel();
	private JButton btnNewButton_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SentimentProcessorView frame = new SentimentProcessorView();
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
	public SentimentProcessorView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("View Unprocessed Comments");
		btnNewButton.setFont(new Font("Rockwell", Font.BOLD, 13));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				List<Comment> list = Comment.fetchAllUnprocessedComments();
				System.out.println(" co : " +  list.size());
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int count = 1;
				
				for(Comment c : list)
				{
					//String co = Integer.parseInt(count).to;
					System.out.println(c.body.toString());
					//System.out.println(c.sentiment);
					model.addRow(new Object[]{c.id.toString(), c.body.toString(),"na",c.sentiment.toString()});
					count++;
				}
				
				
			}
		});
		btnNewButton.setBounds(0, 11, 434, 36);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 59, 401, 147);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sno", "Comment", "SentimentFactor", "SentimentClass"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(97);
		table.getColumnModel().getColumn(3).setPreferredWidth(101);
	
		scrollPane.setViewportView(table);
		
		btnNewButton_1 = new JButton("Compute Sentiment For A Comment");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int rowSelected = table.getSelectedRow();
				System.out.println(rowSelected);
				//String arr[] = new String[table.getColumnCount() - 1];
				System.out.println(table.getColumnCount());
				String comment = table.getModel().getValueAt(rowSelected,1).toString();
				//String comment = model.getValueAt(1,1).toString();
				System.out.println(comment);
				SentimentProcessorController controller = new SentimentProcessorController();
				String outcome  = controller.generateSentiment(comment);
				table.getModel().setValueAt(outcome, rowSelected, 3);
				((AbstractTableModel) table.getModel()).fireTableDataChanged();
				//update document in mongodb using id 
				String id = table.getModel().getValueAt(rowSelected,0).toString();
				String body = table.getModel().getValueAt(rowSelected,1).toString();
				Comment updatedComment = new Comment();
				updatedComment.id = id;
				updatedComment.sentiment = outcome;
				updatedComment.status = "done";
				updatedComment.body = body;
				//boolean updateCommentSentiment = ((SentimentProcessorController) controller).updateCommentSentiment(updatedComment);
				boolean updateCommentSentiment = updatedComment.updateComment();
				if(updateCommentSentiment==true)
					JOptionPane.showMessageDialog(null, "updated on database");
			}
		});
		btnNewButton_1.setFont(new Font("Rockwell", Font.BOLD, 10));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(7, 217, 235, 33);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Compute Sentiment For All");
		btnNewButton_2.setFont(new Font("Rockwell", Font.BOLD, 11));
		btnNewButton_2.setBounds(252, 217, 195, 33);
		contentPane.add(btnNewButton_2);
	}
}
