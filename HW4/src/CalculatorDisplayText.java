import java.util.Observable;

public class CalculatorDisplayText extends Observable{
	private String calculatorDisplayText = "";
	
	public String getDisplayText(){
		return calculatorDisplayText;
	}
	
	public void setDisplayText(String calculatorDisplayText){
		this.calculatorDisplayText = this.calculatorDisplayText + calculatorDisplayText;
		setChanged();
	}
	
	public void clearDisplayText(){
		calculatorDisplayText = "";
	}
}
