
public class AssignCommand extends Stmt{
	public VName v;
	public Exp e;
	
	public AssignCommand(VName v, Exp e){
		this.v = v;
		this.e = e;
	}
}
