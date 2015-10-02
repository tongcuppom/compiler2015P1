
public class OperationMinus extends SimpleExp {
	public SimpleExp s;
	public Term t;
	
	public OperationMinus(SimpleExp s, Term t){
		this.s = s;
		this.t = t;
	}
}
