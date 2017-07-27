import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextArea;

public class CalculatorObserver implements Observer{
	JTextArea display;
	String displayText;
	Socket socket;
	CalculatorDisplayText calculatorDisplayText;
	CompositeOperand compositeOperand = new CompositeOperand();
	ComputingVisitor computingVisitor = new ComputingVisitor();
	
	public CalculatorObserver(JTextArea display, CalculatorDisplayText calculatorDisplayText){
		this.calculatorDisplayText = calculatorDisplayText;
		this.display = display;
		displayText = this.display.getText();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(calculatorDisplayText.getDisplayText().equals("C")){
			compositeOperand.clearDisplayOperandList();
			calculatorDisplayText.clearDisplayText();
		}
		if(calculatorDisplayText.getDisplayText().equals("=")){
			String displayAnswer = compositeOperand.accept(computingVisitor);
			String answer = compositeOperand.getOperandString() + "=" + displayAnswer;
			System.out.println(answer);
			try {
				PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
				printWriter.println(answer);
				printWriter.flush();
				printWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			display.setText(displayAnswer);
		}else{
			compositeOperand.addDisplayOperand(new DisplayOperand(calculatorDisplayText.getDisplayText()));
		}
	}
	
	public void addSocket(Socket socket){
		this.socket = socket;
	}
}
