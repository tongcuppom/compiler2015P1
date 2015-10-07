
public class ConstCommand extends Factor{
	public Number n;
	
	public ConstCommand(Number n){
		this.n = n;
		isTag = true;
		numChildren = 1;
		tagName = "Const: ";
	}

	public AST getChild(int i) {
		return n;
	}
}
