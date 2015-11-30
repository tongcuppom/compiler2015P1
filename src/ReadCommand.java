
public class ReadCommand extends Stmt{
	public IdCommand i;
	
	public ReadCommand(IdCommand i){
		this.i = i;
		isTag = true;
		numChildren = 1;
		tagName = "Read \n";
	}

	public AST getChild(int i) {
		return this.i;
	}

	@Override
	public Object visit(Visitor v, Object org) throws Exception {
		return v.visitReadCommand(this, org);
	}
	
	
}
