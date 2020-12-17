import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;
import java.awt.color.*;
public class Main {
    public static void main(String[] args) {
        new Frame();
    }
}
class Frame extends JFrame{
    ButtonListener b = new ButtonListener();
    Calculator c;

    int buttons = 1;
    int len = 0;
    boolean warningAdded = false;

    int[] data = new int[89];
    JTextField[] dataPoints = new JTextField[88];

    JPanel titlePanel = new JPanel();
    JPanel dataPanel = new JPanel();
    JPanel dataPanel2 = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel resultsPanel = new JPanel();

    JTextField dataPoint = new JTextField(2);

    JButton addMore = new JButton("MORE DATA");
    JButton submit = new JButton("SUBMIT");
    JButton clear = new JButton("CLEAR");

    JLabel title = new JLabel("DATA CALCULATOR");
    JLabel mean = new JLabel("Mean: ");
    JLabel mad = new JLabel("MAD: ");
    JLabel median = new JLabel("Median: ");
    JLabel mode = new JLabel("Mode: ");
    JLabel range = new JLabel("Range: ");
    JLabel q1 = new JLabel("Q1: ");
    JLabel q3 = new JLabel("Q3: ");

    Font titleFont = new Font("SANS_SERIF", Font.BOLD, 50);
    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 50));
        setSize(1000, 1000);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Data Calculator");
        addTextFields();

        title.setFont(titleFont);
        titlePanel.add(title);
        add(titlePanel);

        dataPoints[0] = dataPoint;
        dataPanel.add(dataPoint);
        add(dataPanel);
        add(dataPanel2);

        setupButton(addMore, "more");
        setupButton(submit, "submit");
        setupButton(clear, "clear");
        buttonPanel.add(addMore);
        buttonPanel.add(submit);
        buttonPanel.add(clear);
        add(buttonPanel);

        add(resultsPanel);

        pack();
        setVisible(true);
    }
    void setupButton(JButton button, String actionCommand) {
        button.addActionListener(b);
        button.setActionCommand(actionCommand);
    }
    void analyzeData() {
        for (int i = 0; i < dataPoints.length; i++) {
            if (! dataPoints[i].getText().equals("")) {
                data[len] = Integer.parseInt(dataPoints[i].getText());
                len++;
            }
        }
    }
    void addTextFields() {
        for (int i = 1; i < dataPoints.length; i++) {
            dataPoints[i] = new JTextField(2);
        }
    }
    void clearData() {
        len = 0;
        resultsPanel.remove(mean);
        resultsPanel.remove(mad);
        resultsPanel.remove(median);
        resultsPanel.remove(mode);
        resultsPanel.remove(range);
        resultsPanel.remove(q1);
        resultsPanel.remove(q3);
        for (int i = 1; i < dataPoints.length; i++) {
            if (i < 44) {
                dataPanel.remove(dataPoints[i]);
                setVisible(true);
            }
            else if (i < 88) {
                dataPanel2.remove(dataPoints[i]);
                setVisible(true);
            }
        }
        Arrays.fill(data, 0);
        addTextFields();
        setVisible(true);
    }
    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "more":
                    if (buttons < 44) {
                        dataPanel.add(dataPoints[buttons]);
                        setVisible(true);
                        buttons++;
                    }
                    else if (buttons < 88) {
                        dataPanel2.add(dataPoints[buttons]);
                        setVisible(true);
                        buttons++;
                    }
                    else {
                        if (! warningAdded) {
                            JLabel warning = new JLabel("Reached data limit");
                            warning.setFont(new Font("SANS_SERIF", Font.PLAIN, 20));
                            warning.setForeground(Color.red);
                            buttonPanel.add(warning);
                            warningAdded = true;
                            setVisible(true);
                        }
                    }
                    break;
                case "submit":
                    //FIX CALCULATOR
                    analyzeData();
                    c = new Calculator(data, len);
                    mean.setText("Mean: " + c.getMean());
                    q1.setText("Q1: " + c.getQ1());
                    median.setText("Median: " + c.getMedian(c.sortedData));
                    q3.setText("Q3: " + c.getQ3());
                    mode.setText("Mode: " + c.getMode());
                    range.setText("Range: " + c.getRange());
                    mad.setText("MAD: " + c.getMAD());
                    resultsPanel.add(mean);
                    resultsPanel.add(q1);
                    resultsPanel.add(median);
                    resultsPanel.add(q3);
                    resultsPanel.add(mode);
                    resultsPanel.add(range);
                    resultsPanel.add(mad);
                    len = 0;
                    setVisible(true);
                    break;
                case "clear":
                    clearData();
                    break;
                default:
                    break;
            }
        }
    }
}
