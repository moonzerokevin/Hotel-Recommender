package ui;

import model.Hotel;
import model.HotelList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

// Hotel recommender app
public class MainView implements ActionListener {
    private static final int BUTTON_POSITION = 100;
    private static final int BUTTON_WIDTH = 300;
    private static final int BUTTON_HEIGHT = 20;
    JFrame frame;

    public MainView() {
        frame = new JFrame("Hotel Recommender");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBounds(500, 300, 400, 200);
        frame.setPreferredSize(new Dimension(800, 400));
        ((JPanel) frame.getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        frame.setLayout(null);
        setButtons1();
        setButtons2();
        setButtons3();
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void setButtons1() {
        JLabel selectOptionLabel = new JLabel("Please select an option: ", JLabel.CENTER);
        selectOptionLabel.setBounds(26, 10, 300, 20);
        frame.add(selectOptionLabel);
        selectOptionLabel.setForeground(Color.black);

        JButton viewListButton = new JButton("View list");
        viewListButton.setBounds(BUTTON_POSITION, 40, BUTTON_WIDTH, BUTTON_HEIGHT);
        viewListButton.setActionCommand("VIEW_LIST_ACTION");
        viewListButton.addActionListener(this);
        viewListButton.setForeground(Color.black);
        frame.add(viewListButton);

        JButton addHotelButton = new JButton("Add hotel");
        addHotelButton.setBounds(BUTTON_POSITION, 80, BUTTON_WIDTH, BUTTON_HEIGHT);
        addHotelButton.setActionCommand("ADD_HOTEL_ACTION");
        addHotelButton.addActionListener(this);
        addHotelButton.setForeground(Color.black);
        frame.add(addHotelButton);

        JButton deleteHotelButton = new JButton("Delete hotel");
        deleteHotelButton.setBounds(BUTTON_POSITION, 120, BUTTON_WIDTH, BUTTON_HEIGHT);
        deleteHotelButton.setActionCommand("DELETE_HOTEL_ACTION");
        deleteHotelButton.addActionListener(this);
        deleteHotelButton.setForeground(Color.black);
        frame.add(deleteHotelButton);
    }

    private void setButtons2() {
        JButton recommendWithPriceButton = new JButton("Recommend with price");
        recommendWithPriceButton.setBounds(BUTTON_POSITION, 160, BUTTON_WIDTH, BUTTON_HEIGHT);
        recommendWithPriceButton.setActionCommand("RECOMMEND_WITH_PRICE_ACTION");
        recommendWithPriceButton.addActionListener(this);
        recommendWithPriceButton.setForeground(Color.black);
        frame.add(recommendWithPriceButton);

        JButton recommendWithSatisfactionButton = new JButton("Recommend with satisfaction");
        recommendWithSatisfactionButton.setBounds(BUTTON_POSITION, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
        recommendWithSatisfactionButton.setActionCommand("RECOMMEND_WITH_SATISFACTION_ACTION");
        recommendWithSatisfactionButton.addActionListener(this);
        recommendWithSatisfactionButton.setForeground(Color.black);
        frame.add(recommendWithSatisfactionButton);
    }

    private void setButtons3() {
        JButton loadListButton = new JButton("Load list");
        loadListButton.setBounds(BUTTON_POSITION, 240, BUTTON_WIDTH, BUTTON_HEIGHT);
        loadListButton.setActionCommand("LOAD_LIST_ACTION");
        loadListButton.addActionListener(this);
        loadListButton.setForeground(Color.black);
        frame.add(loadListButton);

        JButton quitAppButton = new JButton("Quit Application");
        quitAppButton.setBounds(BUTTON_POSITION, 280, BUTTON_WIDTH, BUTTON_HEIGHT);
        quitAppButton.setActionCommand("QUIT_APP_ACTION");
        quitAppButton.addActionListener(this);
        quitAppButton.setForeground(Color.black);
        frame.add(quitAppButton);

        JLabel imageLabel = new JLabel(new ImageIcon("src/main/ui/image/hotel.jpeg"));
        imageLabel.setBounds(430,0,300,300);
        frame.add(imageLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("VIEW_LIST_ACTION")) {
            RecommenderApp.viewList();
        } else if (action.equals("ADD_HOTEL_ACTION")) {
            new AddHotelView();
        } else if (action.equals("DELETE_HOTEL_ACTION")) {
            new DeleteHotelView();
        } else if (action.equals("RECOMMEND_WITH_PRICE_ACTION")) {
            new RecommendWithPriceView();
        } else if (action.equals("RECOMMEND_WITH_SATISFACTION_ACTION")) {
            new RecommendWithSatisfactionView();
        } else if (action.equals("LOAD_LIST_ACTION")) {
            RecommenderApp.loadHotelList();
        } else if (action.equals("QUIT_APP_ACTION")) {
            new QuitView();
            frame.dispose();
        }
    }
}