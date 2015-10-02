
public abstract class AST {
	public boolean isTag;
	public int numChildren;
	public String tagName;
	public abstract AST getChild(int i);
}
