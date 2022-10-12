package ui;

import model.HotelList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitView extends JFrame implements ActionListener {
    private JTextField lowPriceField;
    private JTextField highPriceField;

    public QuitView() {
        super("Hotel Recommender");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 300, 400, 200);
        this.setPreferredSize(new Dimension(500, 160));
        ((JPanel) this.getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        this.setLayout(null);
        setLabelsFieldsButtons();
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private void setLabelsFieldsButtons() {
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(100,90,100,20);
        this.add(saveButton);
        saveButton.setActionCommand("Save_ACTION");
        saveButton.addActionListener(this);
        saveButton.setForeground(Color.black);

        JButton withoutSaveButton = new JButton("Without Save");
        withoutSaveButton.setBounds(300,90,100,20);
        this.add(withoutSaveButton);
        withoutSaveButton.setActionCommand("Without_Save_ACTION");
        withoutSaveButton.addActionListener(this);
        withoutSaveButton.setForeground(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Save_ACTION")) {
            RecommenderApp.saveHotelList();
            dispose();
            HotelList.printLog();
        } else if (action.equals("Without_Save_ACTION")) {
            dispose();
            HotelList.printLog();
        }
    }
}
