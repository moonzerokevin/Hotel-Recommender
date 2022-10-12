package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecommendWithSatisfactionView extends JFrame implements ActionListener {
    private JTextField lowSatisfactionField;
    private JTextField highSatisfactionField;

    public RecommendWithSatisfactionView() {
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

        JLabel lowSatisfaction = new JLabel("The Low Satisfaction", JLabel.CENTER);
        lowSatisfaction.setBounds(26, 50, 200, 20);
        this.add(lowSatisfaction);
        lowSatisfaction.setForeground(Color.black);

        lowSatisfactionField = new JTextField(30);
        lowSatisfactionField.setBounds(226, 50, 200, 20);
        this.add(lowSatisfactionField);
    }

    private void setLabelsFieldsButtons2() {
        JLabel highSatisfaction = new JLabel("The High Satisfaction", JLabel.CENTER);
        highSatisfaction.setBounds(26, 70, 200, 20);
        this.add(highSatisfaction);
        highSatisfaction.setForeground(Color.black);

        highSatisfactionField = new JTextField(30);
        highSatisfactionField.setBounds(226, 70, 200, 20);
        this.add(highSatisfactionField);

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
            int lowSatisfaction = Integer.parseInt(lowSatisfactionField.getText());
            int highSatisfaction = Integer.parseInt(highSatisfactionField.getText());
            RecommenderApp.recommendWithSatisfaction(lowSatisfaction,highSatisfaction);
            dispose();
        }
    }
}
