
public class FactorExp extends Factor{
	public Exp e;
	
	public FactorExp(Exp e){
		this.e = e;
		isTag = false;
		numChildren = 1;
		tagName = "";
	}

	public AST getChild(int i) {
		return e;
	}

	@Override
	public Object visit(Visitor v, Object org) throws Exception {
		return v.visitFactorExp(this, org);
	}
}
