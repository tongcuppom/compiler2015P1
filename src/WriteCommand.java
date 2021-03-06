
public class WriteCommand extends Stmt{
	public Exp e;
	
	public WriteCommand(Exp e){
		this.e = e;
		isTag = true;
		numChildren = 1;
		tagName = "Write \n";
	}

	public AST getChild(int i) {
		return e;
	}

	@Override
	public Object visit(Visitor v, Object org) throws Exception {
		return v.visitWriteCommand(this, org);
	}
}
