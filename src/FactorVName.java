
public class FactorVName extends Factor{
	public VName v;
	
	public FactorVName(VName v){
		this.v = v;
		isTag = false;
		numChildren = 1;
		tagName = "";
	}

	public AST getChild(int i) {
		return v;
	}
}
