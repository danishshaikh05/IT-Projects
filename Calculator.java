import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CalculatorApp extends JFrame implements KeyListener, ActionListener {
    JFrame jf;
    Container c;
    JTextArea disField;
    JButton[] numberButtons;
    JButton addButton, subtractButton, multiplyButton, divisionButton, equalsButton, clearButton;
    JPanel panel;

    public char operator;
    public double num1, num2;

    CalculatorApp() {
        createGUI();
        initializeComponents();
        addingComponents();
        addListeners();
    }

    
    public void createGUI() {
        setTitle("Calculator");
        setLocationRelativeTo(null);
        setSize(600, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    public void initializeComponents() {
        c = getContentPane();
        panel = new JPanel(new GridLayout(4, 4, 20, 10));
        Font f = new Font("courier new",Font.BOLD,30);
        Cursor cr = new Cursor(Cursor.HAND_CURSOR);
        disField = new JTextArea(10,10);
        disField.setEditable(false);
        disField.setFont(f);

        
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setCursor(cr);
            numberButtons[i].addActionListener(this);
        }

    
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divisionButton = new JButton("/");
        equalsButton = new JButton("=");
        clearButton = new JButton("C");
        addButton.setCursor(cr);
        subtractButton.setCursor(cr);
        multiplyButton.setCursor(cr);
        divisionButton.setCursor(cr);
        equalsButton.setCursor(cr);
        clearButton.setCursor(cr);
    }

    
    public void addingComponents() {
        c.add(disField, BorderLayout.NORTH);
        c.add(panel);
        for (int i = 1; i <= 9; i++) {
            panel.add(numberButtons[i]);
        }
        panel.add(numberButtons[0]);
        panel.add(addButton);
        panel.add(subtractButton);
        panel.add(multiplyButton);
        panel.add(divisionButton);
        panel.add(equalsButton);
        panel.add(clearButton);
    }

    // Add key listeners to components
    public void addListeners() {
        addKeyListener(this);
        disField.addKeyListener(this);
        addButton.addKeyListener(this);
        subtractButton.addKeyListener(this);
        multiplyButton.addKeyListener(this);
        divisionButton.addKeyListener(this);
        equalsButton.addKeyListener(this);
        clearButton.addKeyListener(this);
    
    // Add action listeners to components
        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divisionButton.addActionListener(this);
        equalsButton.addActionListener(this);
        clearButton.addActionListener(this);
    }
    

    // KeyListener interface methods
    public void keyTyped(KeyEvent ke) {}

    public void keyPressed(KeyEvent ke) {
        // Handle key events
        char keyChar = ke.getKeyChar();
        if (Character.isDigit(keyChar)) {
            disField.setText(disField.getText() + keyChar);
        } else if (keyChar == '+' || keyChar == '-' || keyChar == '*' || keyChar == '/') {
            operator = keyChar;
            num1 = Double.parseDouble(disField.getText());
            disField.setText("");
        } else if (keyChar == '=') {
            num2 = Double.parseDouble(disField.getText());
            double result = 0;
            // Perform arithmetic operation based on operator
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        disField.setText("Error"); 
                    }
                    break;
            }
            // Display result
            disField.setText(String.valueOf(result));
        } else if (keyChar == 'c') {
            disField.setText("");
        }
    }

    public void keyReleased(KeyEvent ke) {}

    // ActionListener interface method
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        // Handle button clicks
        switch (command) {
            case "+":
            case "-":
            case "*":
            case "/":
                // Set operator and clear display field
                operator = command.charAt(0);
                num1 = Double.parseDouble(disField.getText());
                disField.setText("");
                break;
            case "=":
                num2 = Double.parseDouble(disField.getText());
                double result = 0;
                // Perform arithmetic operation based on operator
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            disField.setText("Error");
                            return;
                        }
                        break;
                }
                // Display result
                disField.setText(String.valueOf(result));
                break;
            case "C":
                // Clear display field
                disField.setText("");
                break;
            default:
                // Append number or other characters to the display field
                String currentText = disField.getText();
                String newText = currentText + command;
                disField.setText(newText);
                break;
        }
    }
}

public class Calculator {
    public static void main(String[] args) {
        new CalculatorApp();
    }
}
