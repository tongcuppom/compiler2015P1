
public class FactorVName extends Factor{
	public IdCommand i;
	
	public FactorVName(IdCommand i){
		this.i = i;
		isTag = false;
		numChildren = 1;
		tagName = "";
	}

	public AST getChild(int i) {
		return this.i;
	}

	@Override
	public Object visit(Visitor v, Object org) throws Exception {
		return v.visitFactorVName(this, org);
	}
}
