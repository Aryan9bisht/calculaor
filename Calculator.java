import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Calculator extends JFrame implements ActionListener {

private static final long serialVersionUID = 1L;
JPanel[] row = new JPanel[5];
JButton[] button = new JButton [19];
String[] buttonString = {"7","8","9","+", "4", "5", "6","- ","1", "2", "3", "*",".","/","C","?","+/-","=","0"};

int[] dimW = {430, 70, 150, 140};
int[] dimH = {50, 60};
Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
Dimension regularDimension = new Dimension(dimW[1], dimH[1]);
Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]);
Dimension zeroButDimension = new Dimension(dimW [3], dimH[1]);
boolean[] function = new boolean [4];
double[] temporary = {0, 0};
JTextArea display = new JTextArea (2,25);
Font font = new Font("Times new Roman", Font. BOLD, 20);

Calculator() {
super("Calculator");
setDesign();
setSize(500, 350);
setResizable(false);
setDefaultCloseOperation (EXIT_ON_CLOSE) ;
GridLayout grid = new GridLayout(5,5);
setLayout(grid);

for (int i = 0; i < 4; i++)
{function[i] = false;}
FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 1, 1);
for (int i = 0; i < 5; i++)
{row[i] = new JPanel();}
row [0].setLayout(f1);
for(int i = 1; i < 5; i++)
row[i].setLayout(f2);

for(int i = 0; i < 19; i++) {
button[i] = new JButton();
button[i].setText (buttonString[i]);
button[i].setBackground(Color.WHITE);

button[i].setFont (font);
button[i].addActionListener(this);
}
}

private void setDesign() {
    // Set the background color for the calculator
    getContentPane().setBackground(Color.LIGHT_GRAY);

    // Set the font for the display
    display.setFont(new Font("Arial", Font.PLAIN, 24));

    // Set the text alignment for the display
    display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

    // Set the button colors and font
    for (int i = 0; i < 19; i++) {
        button[i].setBackground(Color.WHITE);
        button[i].setForeground(Color.BLACK);
        button[i].setFont(new Font("Arial", Font.BOLD, 18));
    }

    // Customize the "=" button, for example:
    button[16].setBackground(Color.GREEN);
    button[16].setForeground(Color.WHITE);
    button[16].setFont(new Font("Arial", Font.BOLD, 18));

    // Customize the "C" (Clear) button, for example:
    button[14].setBackground(Color.RED);
    button[14].setForeground(Color.WHITE);
    button[14].setFont(new Font("Arial", Font.BOLD, 18));

    // Set the layout and other design properties as needed
}

@Override
public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand(); // Get the command associated with the button click

    // Handle different button clicks based on the command
    switch (command) {
        case "0":
        case "1":
        case "2":
        case "3":
        case "4":
        case "5":
        case "6":
        case "7":
        case "8":
        case "9":
            // Handle digit buttons
            // Append the digit to the display text
            display.append(command);
            break;

        case "+":
        case "-":
        case "*":
        case "/":
            // Handle arithmetic operation buttons
            // Store the current number in temporary[0] and set the operation flag
            temporary[0] = Double.parseDouble(display.getText());
            display.setText("");
            switch (command) {
                case "+":
                    function[0] = true; // Addition
                    break;
                case "-":
                    function[1] = true; // Subtraction
                    break;
                case "*":
                    function[2] = true; // Multiplication
                    break;
                case "/":
                    function[3] = true; // Division
                    break;
            }
            break;

        case "=":
            // Handle the equals button
            // Perform the calculation based on the selected operation
            if (function[0]) {
                temporary[1] = Double.parseDouble(display.getText());
                double result = temporary[0] + temporary[1];
                display.setText(String.valueOf(result));
                function[0] = false;
            } else if (function[1]) {
                temporary[1] = Double.parseDouble(display.getText());
                double result = temporary[0] - temporary[1];
                display.setText(String.valueOf(result));
                function[1] = false;
            } else if (function[2]) {
                temporary[1] = Double.parseDouble(display.getText());
                double result = temporary[0] * temporary[1];
                display.setText(String.valueOf(result));
                function[2] = false;
            } else if (function[3]) {
                temporary[1] = Double.parseDouble(display.getText());
                if (temporary[1] != 0) {
                    double result = temporary[0] / temporary[1];
                    display.setText(String.valueOf(result));
                } else {
                    display.setText("Error: Division by zero");
                }
                function[3] = false;
            }
            break;

        case "C":
            // Handle the clear button
            // Clear the display and reset temporary values and operation flags
            display.setText("");
            for (int i = 0; i < 4; i++) {
                function[i] = false;
                temporary[i] = 0;
            }
            break;
    }
}
}
