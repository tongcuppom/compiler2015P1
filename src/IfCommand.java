
public class IfCommand extends Stmt{
	public Exp e;
	public Stmt s1;
	public Stmt s2;
	
	public IfCommand(Exp e, Stmt s){
		this.e = e;
		this.s1 = s;
		this.s2 = null;
		isTag = true;
		numChildren = 2;
		tagName = "If\n";
	}
	
	public IfCommand(Exp e, Stmt s1, Stmt s2){
		this.e = e;
		this.s1 = s1;
		this.s2 = s2;
		isTag = true;
		numChildren = 3;
		tagName = "If\n";
	}

	public AST getChild(int i) {
		if(i==0)
			return e;
		else if(i==1)
			return s1;
		else
			return s2;
	}

	@Override
	public Object visit(Visitor v, Object org) throws Exception {
		return v.visitIfCommand(this, org);
	}
}
