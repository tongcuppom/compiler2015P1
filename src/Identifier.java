
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
}
