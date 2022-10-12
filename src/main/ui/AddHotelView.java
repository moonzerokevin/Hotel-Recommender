package ui;

import model.Hotel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class AddHotelView implements ActionListener {
    private JTextField nameField;
    private JTextField priceField;
    private JTextField satisfactionField;
    private JFrame frame;

    public AddHotelView() {
        frame = new JFrame("Hotel Recommender");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(500, 300, 400, 200);
        frame.setPreferredSize(new Dimension(500, 160));
        ((JPanel) frame.getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        frame.setLayout(null);
        setLabelsFieldsButtons1();
        setLabelsFieldsButtons2();
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void setLabelsFieldsButtons1() {
        JLabel selectOptionLabel = new JLabel("Please enter two integer and a String separately", JLabel.CENTER);
        selectOptionLabel.setBounds(26, 10, 300, 20);
        frame.add(selectOptionLabel);
        selectOptionLabel.setForeground(Color.black);

        JLabel priceLabel = new JLabel("The Price", JLabel.CENTER);
        priceLabel.setBounds(26, 50, 200, 20);
        frame.add(priceLabel);
        priceLabel.setForeground(Color.black);

        priceField = new JTextField(30);
        priceField.setBounds(226, 50, 200, 20);
        frame.add(priceField);

        JLabel satisfactionLabel = new JLabel("The Satisfaction", JLabel.CENTER);
        satisfactionLabel.setBounds(26, 70, 200, 20);
        frame.add(satisfactionLabel);
        satisfactionLabel.setForeground(Color.black);

        satisfactionField = new JTextField(30);
        satisfactionField.setBounds(226, 70, 200, 20);
        frame.add(satisfactionField);
    }

    private void setLabelsFieldsButtons2() {
        JLabel nameLabel = new JLabel("The Name", JLabel.CENTER);
        nameLabel.setBounds(26, 30, 200, 20);
        frame.add(nameLabel);
        nameLabel.setForeground(Color.black);

        nameField = new JTextField(30);
        nameField.setBounds(226, 30, 200, 20);
        frame.add(nameField);

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
            String name = nameField.getText();
            int price = Integer.parseInt(priceField.getText());
            int satisfaction = Integer.parseInt(satisfactionField.getText());
            Hotel newHotel = new Hotel(price, satisfaction, name);
            RecommenderApp.addHotel(newHotel);
            frame.dispose();
        }
    }
}
