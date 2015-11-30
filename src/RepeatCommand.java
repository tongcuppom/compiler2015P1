
public class RepeatCommand extends Stmt{
	public Stmt s;
	public Exp e;
	
	public RepeatCommand(Stmt s, Exp e){
		this.s = s;
		this.e = e;
		isTag = true;
		numChildren = 2;
		tagName = "Repeat \n";
	}

	public AST getChild(int i) {
		if(i==0)
			return s;
		else
			return e;
	}

	@Override
	public Object visit(Visitor v, Object org) throws Exception {
		return v.visitRepeatCommand(this, org);
	}
}
