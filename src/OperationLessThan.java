
public class OperationLessThan extends Exp{
	public SimpleExp s1;
	public SimpleExp s2;
	
	public OperationLessThan(SimpleExp s1, SimpleExp s2){
		this.s1 = s1;
		this.s2 = s2;
		isTag = true;
		numChildren = 2;
		tagName = "Op: < \n";
	}

	public AST getChild(int i) {
		if(i==0)
			return s1;
		else
			return s2;
	}

	@Override
	public Object visit(Visitor v, Object org) throws Exception {
		return v.visitOperationLessThan(this, org);
	}
}
