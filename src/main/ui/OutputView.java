package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OutputView extends JFrame implements ActionListener {
    private JTextField lowPriceField;
    private JTextField highPriceField;

    public OutputView(String output) {
        super("Hotel Recommender");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 300, 400, 200);
        this.setPreferredSize(new Dimension(500, 160));
        ((JPanel) this.getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        this.setLayout(null);
        setLabelsFieldsButtons(output);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void setLabelsFieldsButtons(String output) {
        JLabel outputLabel = new JLabel(output, JLabel.CENTER);
        outputLabel.setBounds(26, 50, 300, 20);
        this.add(outputLabel);
        outputLabel.setForeground(Color.black);

        JButton finishButton = new JButton("Continue");
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
            dispose();
        }
    }
}
