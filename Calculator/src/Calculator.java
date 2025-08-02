import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private final JTextField display;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public Calculator() {
        setTitle("Java Calculator - Project 19");
        setSize(400, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        display = new JTextField();
        display.setBounds(20, 20, 340, 60);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(false);
        add(display);

        String[] buttonLabels = {
                "7", "8", "9", "/", "C",
                "4", "5", "6", "*", "←",
                "1", "2", "3", "-", "",
                "0", ".", "=", "+", ""
        };

        int x = 20, y = 100;
        for (int i = 0; i < buttonLabels.length; i++) {
            if (!buttonLabels[i].isEmpty()) {
                JButton btn = new JButton(buttonLabels[i]);
                btn.setBounds(x, y, 60, 60);
                btn.setFont(new Font("Arial", Font.BOLD, 20));
                btn.addActionListener(this);
                add(btn);
            }
            x += 70;
            if ((i + 1) % 5 == 0) {
                x = 20;
                y += 70;
            }
        }

        getContentPane().setBackground(Color.DARK_GRAY);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = ((JButton) e.getSource()).getText();

        if (input.matches("[0-9.]")) {
            display.setText(display.getText() + input);
        } else if (input.matches("[+\\-*/]")) {
            if (!display.getText().isEmpty()) {
                num1 = Double.parseDouble(display.getText());
                operator = input.charAt(0);
                display.setText("");
            }
        } else if (input.equals("=")) {
            if (!display.getText().isEmpty()) {
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 != 0) result = num1 / num2;
                        else {
                            display.setText("Cannot divide by 0");
                            return;
                        }
                        break;
                }
                display.setText(String.valueOf(result));
            }
        } else if (input.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
        } else if (input.equals("←")) {
            String current = display.getText();
            if (!current.isEmpty()) {
                display.setText(current.substring(0, current.length() - 1));
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
