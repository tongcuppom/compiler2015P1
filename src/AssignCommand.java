
public class AssignCommand extends Stmt{
	public VName v;
	public Exp e;
	
	public AssignCommand(VName v, Exp e){
		this.v = v;
		this.e = e;
		isTag = true;
		numChildren = 2;
		tagName = "Assign to: ";
	}
	
	public AST getChild(int i){
		if(i==0)
			return v;
		else
			return e;
	}
}
