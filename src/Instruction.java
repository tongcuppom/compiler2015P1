
public class Instruction {
	public byte op;
	public byte r;
	public byte n;
	public short d;
	
	public static final byte LDop = 0,
			LDAop = 1,
			LDCop = 2,
			STop = 3,
			JLTop = 4,
			JLEop = 5,
			JGTop = 6,
			JGEop = 7,
			JEQop = 8,
			JNEop = 9,
			HALTop = 10,
			INop = 11,
			OUTop = 12,
			ADDop = 13,
			SUBop = 14,
			MULop = 15,
			DIVop = 16;
	
	public static final byte ACr = 0,
			AC1r = 1,
			GPr = 5,
			MPr = 6,
			PCr = 7;
	
	public Instruction(byte op, byte n, byte r, short d){
		this.op = op;
		this.n = n;
		this.r = r;
		this.d = d;
	}
}
