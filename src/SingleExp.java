
public class SingleExp extends Exp{
	public SimpleExp s;
	
	public SingleExp(SimpleExp s){
		this.s = s;
		isTag = false;
		numChildren = 1;
		tagName = "";
	}

	public AST getChild(int i) {
		return s;
	}
}
