package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class DeleteHotelView implements ActionListener {
    private JTextField indexField;
    private JFrame frame;

    public DeleteHotelView() {
        frame = new JFrame("Hotel Recommender");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(500, 300, 400, 200);
        frame.setPreferredSize(new Dimension(500, 160));
        ((JPanel) frame.getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        frame.setLayout(null);
        setLabelsFieldsButtons();
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void setLabelsFieldsButtons() {
        JLabel selectOptionLabel = new JLabel("Please enter one integer", JLabel.CENTER);
        selectOptionLabel.setBounds(26, 10, 300, 20);
        frame.add(selectOptionLabel);
        selectOptionLabel.setForeground(Color.black);

        JLabel indexLabel = new JLabel("The Index", JLabel.CENTER);
        indexLabel.setBounds(26, 70, 200, 20);
        frame.add(indexLabel);
        indexLabel.setForeground(Color.black);

        indexField = new JTextField(30);
        indexField.setBounds(226, 70, 200, 20);
        frame.add(indexField);

        JButton finishButton = new JButton("Finish");
        finishButton.setBounds(170,90,100,20);
        frame.add(finishButton);
        finishButton.setActionCommand("FINISH_ACTION");
        finishButton.addActionListener(this);
        finishButton.setForeground(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("FINISH_ACTION")) {
            int index = Integer.parseInt(indexField.getText());
            RecommenderApp.deleteHotel(index);
            frame.dispose();
        }
    }
}
