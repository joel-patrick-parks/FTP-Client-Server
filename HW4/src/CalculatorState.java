import javax.swing.JTextArea;

public class CalculatorState {
	CalculatorDisplayText calculatorDisplayText;
	JTextArea display;
	
	public CalculatorState(CalculatorDisplayText calculatorDisplayText, JTextArea display){
		this.calculatorDisplayText = calculatorDisplayText;
		this.display = display;
	}
	
	public void resetState(int errorResponse){
		if(errorResponse == 1){
			display.setText("");
        	calculatorDisplayText.setDisplayText("C");
        	calculatorDisplayText.notifyObservers();
        	calculatorDisplayText.clearDisplayText();
		}else{
			calculatorDisplayText.clearDisplayText();
		}
	}
}
