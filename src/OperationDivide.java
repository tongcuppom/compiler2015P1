
public class OperationDivide extends Term{
	public Term t;
	public Factor f;
	
	public OperationDivide(Term t, Factor f){
		this.t = t;
		this.f = f;
		isTag = true;
		numChildren = 2;
		tagName = "OP: / \n";
	}

	public AST getChild(int i) {
		if(i==0)
			return t;
		else
			return f;
	}

	@Override
	public Object visit(Visitor v, Object org) throws Exception {
		return v.visitOperationDivide(this, org);
	}
}
