
public class Token {
	public byte kind;
	public String spelling;
	public int lineNumber;
	public int column;
	
	final static byte
		IDENTIFIER = 0,
		NUMBER = 1,
		
		EOT = 2,
		ELSE = 3,
		END = 4,
		IF = 5,
		READ = 6,
		REPEAT = 7,
		THEN = 8,
		UNTIL = 9,
		WRITE = 10,
		
		SEMICOLON = 11,
		OPENPARENTHESIS = 12,
		CLOSEPARENTHESIS = 13,
		ASSIGN = 14,
		
		OPLESSTHAN = 15,
		OPEQUAL = 16,
		OPPLUS = 17,
		OPMINUS = 18,
		OPMUL = 19,
		OPDIV = 20,
		
		EPSILON = 21;
	
	final static String[] Skind = {
		"ID : ",
		"Const: ",
		"EOT ",
		"Else ",
		"End ",
		"If ",
		"Read ",
		"Repeat ",
		"Then ",
		"Until ",
		"Write ",
		
		";",
		"(",
		")",
		"Assign to: ",
		
		"OP: < ",
		"OP: = ",
		"OP: + ",
		"OP: - ",
		"OP: * ",
		"OP: / ",
		
		""
	};
	
	public Token(byte kind, String spelling, int line, int column){
		this.kind = kind;
		this.spelling = spelling;
		this.lineNumber = line;
		this.column = column;
	}
}
