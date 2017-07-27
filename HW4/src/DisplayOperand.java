
public class DisplayOperand implements CalculatorOperand{
	public String displayInput;
	
	public DisplayOperand(String displayInput){
		this.displayInput = displayInput;
	}
	
	@Override
	public void setLastDisplayInput(String input) {
		// TODO Auto-generated method stub
		displayInput = input;
	}

	@Override
	public String getLastDisplayInput() {
		// TODO Auto-generated method stub
		return displayInput;
	}
	
}
