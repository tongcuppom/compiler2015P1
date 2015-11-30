
public class IdCommand extends VName{
	public Identifier i;
	
	public IdCommand(Identifier i){
		this.i = i;
		isTag = true;
		numChildren = 1;
		tagName = "Id: ";
	}

	public AST getChild(int i) {
		return this.i;
	}

	@Override
	public Object visit(Visitor v, Object org) throws Exception {
		return v.visitIdCommand(this, org);
	}
}
