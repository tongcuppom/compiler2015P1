
public class FactorExp extends Factor{
	public Exp e;
	
	public FactorExp(Exp e){
		this.e = e;
		isTag = false;
		numChildren = 1;
		tagName = "";
	}

	public AST getChild(int i) {
		return e;
	}
}
