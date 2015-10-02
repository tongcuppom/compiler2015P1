
public class IfCommand extends Stmt{
	public Exp e;
	public Stmt s1;
	public Stmt s2;
	
	public IfCommand(Exp e, Stmt s){
		this.e = e;
		this.s1 = s;
		this.s2 = null;
	}
	
	public IfCommand(Exp e, Stmt s1, Stmt s2){
		this.e = e;
		this.s1 = s1;
		this.s2 = s2;
	}
}
