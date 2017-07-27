import java.util.ArrayList;

public class ComputingVisitor implements CalculatorVisitor{
	ArrayList<String> operands = new ArrayList<String>();
	String answer;
	
	@Override
	public String visit(final CompositeOperand compositeOperand) {
		// TODO Auto-generated method stub
		compositeOperand.copyList(operands);
		for(int i = 0; i < operands.size()-1; i++){
			if(operands.get(1).equals("+")){
				operands.set(0, Integer.toString(Integer.parseInt(operands.get(0)) + Integer.parseInt(operands.get(2))));
				operands.remove(1);
				operands.remove(1);
			}
			else if(operands.get(1).equals("-")){
				operands.set(0, Integer.toString(Integer.parseInt(operands.get(0)) - Integer.parseInt(operands.get(2))));
				operands.remove(1);
				operands.remove(1);
			}
			else if(operands.get(1).equals("*")){
				operands.set(0, Integer.toString(Integer.parseInt(operands.get(0)) * Integer.parseInt(operands.get(2))));
				operands.remove(1);
				operands.remove(1);
			}
			else if(operands.get(1).equals("/")){
				operands.set(0, Integer.toString(Integer.parseInt(operands.get(0)) / Integer.parseInt(operands.get(2))));
				operands.remove(1);
				operands.remove(1);
			}
		}
		answer = operands.get(0);
		return answer;
	}

}
