
public class ReadCommand extends Stmt{
	public VName v;
	
	public ReadCommand(VName v){
		this.v = v;
		isTag = true;
		numChildren = 1;
		tagName = "Read \n";
	}

	public AST getChild(int i) {
		return v;
	}
	
	
}
