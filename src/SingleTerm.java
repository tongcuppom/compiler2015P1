
public class SingleTerm extends Term{
	public Factor f;
	
	public SingleTerm(Factor f){
		this.f = f;
		isTag = false;
		numChildren = 1;
		tagName = "";
	}

	public AST getChild(int i) {
		return f;
	}
}
