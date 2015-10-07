
public class SingleSimpleExp extends SimpleExp{
	public Term t;
	
	public SingleSimpleExp(Term t){
		this.t = t;
		isTag = false;
		numChildren = 1;
		tagName = "";
	}

	public AST getChild(int i) {
		return t;
	}
}
