
public class Program extends AST {
	public Stmt s;
	
	public Program(Stmt s){
		this.s = s;
		isTag = false;
		numChildren = 1;
		tagName = "";
	}

	public AST getChild(int i) {
		return s;
	}

	@Override
	public Object visit(Visitor v, Object org) {
		return v.visitProgram(this, org);
	}
	
}
