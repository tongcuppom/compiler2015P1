
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
}
