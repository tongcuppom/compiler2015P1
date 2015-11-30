
public class Number extends Terminal{
	
	public Number(String spelling){
		this.spelling = spelling;
		isTag = false;
		numChildren = 0;
		tagName = "";
	}

	public String getSpelling() {
		return spelling;
	}

	public AST getChild(int i) {
		return null;
	}

	@Override
	public Object visit(Visitor v, Object org) throws Exception {
		return v.visitNumber(this, org);
	}
}
