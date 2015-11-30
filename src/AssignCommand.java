
public class AssignCommand extends Stmt{
	public IdCommand i;
	public Exp e;
	
	public AssignCommand(IdCommand i, Exp e){
		this.i = i;
		this.e = e;
		isTag = true;
		numChildren = 2;
		tagName = "Assign to: \n";
	}
	
	public AST getChild(int i){
		if(i==0)
			return this.i;
		else
			return e;
	}

	@Override
	public Object visit(Visitor v, Object org) throws Exception {
		return v.visitAssignCommand(this, org);
	}
}
