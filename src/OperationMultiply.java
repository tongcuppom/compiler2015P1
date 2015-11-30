
public class OperationMultiply extends Term{
	public Term t;
	public Factor f;
	
	public OperationMultiply(Term t, Factor f){
		this.t = t;
		this.f = f;
		isTag = true;
		numChildren = 2;
		tagName = "Op: * \n";
	}

	public AST getChild(int i) {
		if(i==0)
			return t;
		else
			return f;
	}

	@Override
	public Object visit(Visitor v, Object org) throws Exception {
		return v.visitOperationMultiply(this, org);
	}
}
