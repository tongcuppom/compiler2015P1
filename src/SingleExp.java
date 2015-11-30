
public class SingleExp extends Exp{
	public SimpleExp s;
	
	public SingleExp(SimpleExp s){
		this.s = s;
		isTag = false;
		numChildren = 1;
		tagName = "";
	}

	public AST getChild(int i) {
		return s;
	}

	@Override
	public Object visit(Visitor v, Object org) throws Exception {
		return v.visitSingleExp(this, org);
	}
}
