package findYourPath;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class RetrieveDataFromDB extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int firstNumber;
	static int secondNumber;
	String Ans;
	String a;

	private JTextField rowField, colField;
	private JTextArea outputArea;

	public RetrieveDataFromDB() {
		// Set up the GUI
		setTitle("Find_Your_Path");
		setLayout(new FlowLayout());
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		add(new JLabel("ENTER QUESTION NUMBER"));
		rowField = new JTextField(10);
		add(rowField);

		add(new JLabel("ENTER CARD NUMBER"));
		colField = new JTextField(10);
		add(colField);

		JButton button = new JButton("View");
		add(button);

		outputArea = new JTextArea(10, 30);
		add(outputArea);
		outputArea.setLineWrap(true);
		outputArea.setWrapStyleWord(true);
		outputArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(outputArea);

		add(scrollPane);

		// The action listener for the Retrieve button
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {

					firstNumber = Integer.parseInt(rowField.getText());
					secondNumber = Integer.parseInt(colField.getText());

					int Qname = firstNumber;
					int c = secondNumber;
					int Cname = c + 2;
					try {
						Connection con = connection.connectDB();

						String query = "SELECT * FROM qdata WHERE QID = ?";

						PreparedStatement statement = con.prepareStatement(query);
						statement.setInt(1, Qname);
						ResultSet resultSet = statement.executeQuery();

						if (resultSet.next()) {
							Ans = resultSet.getString(Cname);
//				
							a = Ans;

							outputArea.setText("Solution:- " + a);

						} else {
							Ans = "No data found";

							outputArea.setText("Error: " + Ans);
						}

					} catch (Exception ea) {
//			System.out.println("Error: " + ea.getMessage());
						a = ea.getMessage();
						outputArea.setText("Error: " + a);
					}

				} catch (NumberFormatException exception) {
					Component frame = null;
					JOptionPane.showMessageDialog(frame, "Invalid input. Please enter two integers.");
				}

			}
		});
		setVisible(true);
	}

}