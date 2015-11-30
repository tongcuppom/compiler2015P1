
public class Type {

	private byte kind;
	public static final byte BOOL = 0;
	public static final byte INT = 1;
	
	public Type(byte kind){
		this.kind = kind;
	}
	
	public boolean equals (Object other){
		Type otherType = (Type) other;
		return (this.kind == otherType.kind);
	}
}
