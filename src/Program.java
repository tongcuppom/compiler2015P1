
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
	
}
