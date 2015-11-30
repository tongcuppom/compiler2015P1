
public class Identifier extends Terminal{
	
	public Identifier(String spelling){
		this.spelling = spelling;
		isTag = false;
		numChildren = 0;
		tagName = "";
	}

	public AST getChild(int i) {
		return null;
	}

	public String getSpelling() {
		return spelling;
	}

	@Override
	public Object visit(Visitor v, Object org) throws Exception {
		return v.visitIdentifier(this, org);
	}
}
