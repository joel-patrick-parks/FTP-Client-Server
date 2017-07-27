import java.util.ArrayList;

public class CompositeOperand implements CalculatorOperand{
	ArrayList<DisplayOperand> displayOperandList = new ArrayList<DisplayOperand>();
	public String displayInput;
	
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

	public void addDisplayOperand(DisplayOperand displayOperand){
		displayOperandList.add(displayOperand);
	}
	
	public void clearDisplayOperandList(){
		displayOperandList.clear();
	}
	
	public String accept(final ComputingVisitor computingVisitor){
		String answer = computingVisitor.visit(this);
		return answer;
	}
	
	public void copyList(ArrayList<String> copiedList){
		for(int i = 0; i < displayOperandList.size(); i++){
			copiedList.add(displayOperandList.get(i).getLastDisplayInput());
		}
	}
	
	public String getOperandString(){
		String operandString = "";
		for(int i = 0; i < displayOperandList.size(); i++){
			operandString = operandString + displayOperandList.get(i).getLastDisplayInput();
		}
		return operandString;
	}

}
