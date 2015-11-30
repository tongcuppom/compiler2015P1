
public class OperationPlus extends SimpleExp {
	public SimpleExp s;
	public Term t;
	
	public OperationPlus(SimpleExp s, Term t){
		this.s = s;
		this.t = t;
		isTag = true;
		numChildren = 2;
		tagName = "Op: + \n";
	}

	public AST getChild(int i) {
		if(i==0)
			return s;
		else
			return t;
	}

	@Override
	public Object visit(Visitor v, Object org) throws Exception {
		return v.visitOperationPlus(this, org);
	}
}
