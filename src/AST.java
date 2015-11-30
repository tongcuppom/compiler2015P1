
public abstract class AST {
	public boolean isTag;
	public int numChildren;
	public String tagName;
	public String spelling;
	public abstract AST getChild(int i);
	public abstract Object visit(Visitor v, Object org) throws Exception;
}
