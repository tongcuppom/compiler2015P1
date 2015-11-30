public class Attribute {
 
	public static final byte CONST = 0, 
			VAR = 1, 
			BOOL = 0, 
			INT = 1; 
 
	byte kind; 
	byte type; 
	int dMemPlace;
	
	public Attribute(byte kind, byte type, int dMemPlace){
		this.kind = kind;
		this.type = type;
		this.dMemPlace = dMemPlace;
	}
}