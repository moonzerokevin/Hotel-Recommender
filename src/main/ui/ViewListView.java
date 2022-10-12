package ui;

import model.Hotel;
import model.HotelList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewListView extends JFrame implements ActionListener {
    private DefaultTableModel tableModel;
    private JTable table;
    HotelList hotelList;

    public ViewListView(HotelList savList) {
        this.hotelList = savList;
        final String[] columnLabels = new String[] {
                "Index",
                "Name",
                "Price",
                "Satisfaction"
        };
        tableModel = new DefaultTableModel(null, columnLabels) {};
        table = new JTable(tableModel);
        this.populateTableRows();
        add(new JScrollPane(table));
        this.setButtons();
        setTitle("Recommender App");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void populateTableRows() {
        for (int i = 0; i < hotelList.getList().size(); i++) {
            Hotel hotel = hotelList.getList().get(i);
            Object[] tableRow = new Object[] {
                    i + 1,
                    hotel.getName(),
                    hotel.getPrice(),
                    hotel.getSatisfaction(),
            };
            tableModel.addRow(tableRow);
        }
    }

    private void setButtons() {
        JButton finishButton = new JButton("Finish");
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
