
public class OperationMinus extends SimpleExp {
	public SimpleExp s;
	public Term t;
	
	public OperationMinus(SimpleExp s, Term t){
		this.s = s;
		this.t = t;
		isTag = true;
		numChildren = 2;
		tagName = "Op: - \n";
	}

	public AST getChild(int i) {
		if(i==0)
			return s;
		else
			return t;
	}
}
