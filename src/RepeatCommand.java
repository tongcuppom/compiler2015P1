
public class RepeatCommand extends Stmt{
	public Stmt s;
	public Exp e;
	
	public RepeatCommand(Stmt s, Exp e){
		this.s = s;
		this.e = e;
	}
}
