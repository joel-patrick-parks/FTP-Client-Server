import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Calculator extends JFrame implements ActionListener {  
    JPanel[] row = new JPanel[5];
    JButton[] button = new JButton[16];
    String[] buttonString = {"1", "2", "3", "+",
                             "4", "5", "6", "-",
                             "7", "8", "9", "*",
                             "0", "=", "C", "/"};
    Object[] options = {"Reset", "Discard"};
    int[] dimW = {300,45,100,90};
    int[] dimH = {35, 40};
    Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
    Dimension regularDimension = new Dimension(dimW[1], dimH[1]);
    JTextArea display = new JTextArea(1,20);
    CalculatorDisplayText calculatorDisplayText = new CalculatorDisplayText();
    CalculatorObserver calculatorObserver = new CalculatorObserver(display, calculatorDisplayText);
    CalculatorState calculatorState = new CalculatorState(calculatorDisplayText, display);
    Socket socket;
     
    Calculator() throws UnknownHostException, IOException {
        super("Calculator");
        setSize(380, 250);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GridLayout grid = new GridLayout(5,5);
        setLayout(grid);
        calculatorDisplayText.addObserver(calculatorObserver);
        socket = new Socket("localhost", 2121);
        calculatorObserver.addSocket(socket);
         
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER);
        FlowLayout f2 = new FlowLayout(FlowLayout.CENTER,1,1);
        for(int i = 0; i < 5; i++)
            row[i] = new JPanel();
        row[0].setLayout(f1);
        for(int i = 1; i < 5; i++)
            row[i].setLayout(f2);
         
        for(int i = 0; i < 16; i++) {
            button[i] = new JButton();
            button[i].setText(buttonString[i]);
            button[i].addActionListener(this);
        }
         
        display.setEditable(false);
        display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        display.setPreferredSize(displayDimension);
        for(int i = 0; i < 16; i++)
            button[i].setPreferredSize(regularDimension);
         
        row[0].add(display);
        add(row[0]);
         
        for(int i = 0; i < 4; i++)
            row[1].add(button[i]);
        add(row[1]);
         
        for(int i = 4; i < 8; i++)
            row[2].add(button[i]);
        add(row[2]);
         
        for(int i = 8; i < 12; i++)
            row[3].add(button[i]);
        add(row[3]);
         
        for(int i = 12; i < 16; i++)
            row[4].add(button[i]);
        add(row[4]);
         
        setVisible(true);
    }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button[0]){
        	display.append("1");
        }
        if(e.getSource() == button[1]){
        	display.append("2");
        }
        if(e.getSource() == button[2]){
        	display.append("3");
        }
        if(e.getSource() == button[3]){
        	if(calculatorDisplayText.getDisplayText().equals("+") || calculatorDisplayText.getDisplayText().equals("-") || calculatorDisplayText.getDisplayText().equals("*") || calculatorDisplayText.getDisplayText().equals("/")){
        		int errorResponse = JOptionPane.showOptionDialog(null, "ERROR: Would you like to Reset or Discard?", "ERROR", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        		calculatorState.resetState(errorResponse);
        	}else{
        		calculatorDisplayText.clearDisplayText();
        		calculatorDisplayText.setDisplayText(display.getText());
            	calculatorDisplayText.notifyObservers();
            	display.setText("");
            	calculatorDisplayText.clearDisplayText();
            	calculatorDisplayText.setDisplayText("+");
            	calculatorDisplayText.notifyObservers();
        	}
        }
        if(e.getSource() == button[4]){
        	display.append("4");
        }
        if(e.getSource() == button[5]){
        	display.append("5");
        }
        if(e.getSource() == button[6]){
        	display.append("6");
        }
        if(e.getSource() == button[7]){
        	calculatorDisplayText.setDisplayText(display.getText());
        	calculatorDisplayText.notifyObservers();
        	display.setText("");
        	calculatorDisplayText.clearDisplayText();
        	calculatorDisplayText.setDisplayText("-");
        	calculatorDisplayText.notifyObservers();
        	calculatorDisplayText.clearDisplayText();
        }
        if(e.getSource() == button[8]){
        	display.append("7");
        }
        if(e.getSource() == button[9]){
        	display.append("8");
        }
        if(e.getSource() == button[10]){
        	display.append("9");
        }
        if(e.getSource() == button[11]){
        	calculatorDisplayText.setDisplayText(display.getText());
        	calculatorDisplayText.notifyObservers();
        	display.setText("");
        	calculatorDisplayText.clearDisplayText();
        	calculatorDisplayText.setDisplayText("*");
        	calculatorDisplayText.notifyObservers();
        	calculatorDisplayText.clearDisplayText();
        }
        if(e.getSource() == button[12]){
        	display.append("0");
        }
        if(e.getSource() == button[13]){
        	calculatorDisplayText.setDisplayText(display.getText());
        	calculatorDisplayText.notifyObservers();
        	calculatorDisplayText.clearDisplayText();
        	calculatorDisplayText.setDisplayText("=");
        	calculatorDisplayText.notifyObservers();
        }
        if(e.getSource() == button[14]){
        	display.setText("");
        	calculatorDisplayText.setDisplayText("C");
        	calculatorDisplayText.notifyObservers();
        }
        if(e.getSource() == button[15]){
        	calculatorDisplayText.setDisplayText(display.getText());
        	calculatorDisplayText.notifyObservers();
        	display.setText("");
        	calculatorDisplayText.clearDisplayText();
        	calculatorDisplayText.setDisplayText("/");
        	calculatorDisplayText.notifyObservers();
        	calculatorDisplayText.clearDisplayText();
        }
    }
     
    public static void main(String[] arguments) throws UnknownHostException, IOException {
        Calculator c = new Calculator();
    }
}
