
public class StmtSeq extends Stmt {
	public Stmt s1;
	public Stmt s2;
	
	public StmtSeq(Stmt s1, Stmt s2){
		this.s1 = s1;
		this.s2 = s2;
		isTag = false;
		numChildren = 2;
		tagName = "";
	}

	public AST getChild(int i) {
		if(i==0)
			return s1;
		else
			return s2;
	}
}
