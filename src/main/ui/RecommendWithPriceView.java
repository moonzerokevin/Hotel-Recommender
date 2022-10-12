package ui;

import model.Hotel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class RecommendWithPriceView extends JFrame implements ActionListener {
    private JTextField lowPriceField;
    private JTextField highPriceField;

    public RecommendWithPriceView() {
        super("Hotel Recommender");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 300, 400, 200);
        this.setPreferredSize(new Dimension(500, 160));
        ((JPanel) this.getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        this.setLayout(null);
        setLabelsFieldsButtons1();
        setLabelsFieldsButtons2();
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void setLabelsFieldsButtons1() {
        JLabel selectOptionLabel = new JLabel("Please enter two integer", JLabel.CENTER);
        selectOptionLabel.setBounds(26, 10, 300, 20);
        this.add(selectOptionLabel);
        selectOptionLabel.setForeground(Color.black);

        JLabel priceLabel = new JLabel("The Low Price", JLabel.CENTER);
        priceLabel.setBounds(26, 50, 200, 20);
        this.add(priceLabel);
        priceLabel.setForeground(Color.black);

        lowPriceField = new JTextField(30);
        lowPriceField.setBounds(226, 50, 200, 20);
        this.add(lowPriceField);
    }

    private void setLabelsFieldsButtons2() {
        JLabel highPrice = new JLabel("The High Price", JLabel.CENTER);
        highPrice.setBounds(26, 70, 200, 20);
        this.add(highPrice);
        highPrice.setForeground(Color.black);

        highPriceField = new JTextField(30);
        highPriceField.setBounds(226, 70, 200, 20);
        this.add(highPriceField);

        JButton finishButton = new JButton("Finish");
        finishButton.setBounds(170,90,100,20);
        this.add(finishButton);
        finishButton.setActionCommand("FINISH_ACTION");
        finishButton.addActionListener(this);
        finishButton.setForeground(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("FINISH_ACTION")) {
            int lowPrice = Integer.parseInt(lowPriceField.getText());
            int highPrice = Integer.parseInt(highPriceField.getText());
            RecommenderApp.recommendWithPrice(lowPrice,highPrice);
            dispose();
        }
    }
}
