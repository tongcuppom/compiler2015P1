import java.io.IOException;

public class Parser {
	private Token currentToken;
	private CompilerScanner scanner;
	
	private void accept(byte expectedKind) throws Exception{
		if(currentToken.kind == expectedKind)
			currentToken = scanner.scan();
		else{
			throw new Exception("error unexpected kind at line: " + scanner.getLine() + " column: "+ scanner.getColumn());
		}
	}
	
	private void acceptIt() throws IOException{
		currentToken = scanner.scan();
	}
	
	public Program parse(String filePath) throws Exception{
		Program p;
		scanner = new CompilerScanner(filePath);
		acceptIt();
		p = parseProgram();
		return p;					
	}
	
	private Program parseProgram() throws Exception{
		Program p = new Program(parseStmtSeq());
		accept(Token.EOT); // need EOT to confirm that it finish
		return p;
	}
	
	private Stmt parseStmtSeq() throws Exception{
		Stmt s = parseStmt();
		while (currentToken.kind == Token.SEMICOLON){
			acceptIt();
			s = new StmtSeq(s,parseStmt());
		}
		return s;
	}
	
	private Stmt parseStmt() throws Exception{
		Stmt s;
		switch(currentToken.kind){
			case Token.IF:
				acceptIt();
				s = parseIfStmt();
			break;
			case Token.REPEAT:
				acceptIt();
				s = parseRepeatStmt();
			break;
			case Token.IDENTIFIER:
				s = parseAssignStmt();   //no accept because we need to keep the spelling			
			break;
			case Token.READ:
				acceptIt();
				s = parseReadStmt();
			break;
			case Token.WRITE:
				acceptIt();
				s = parseWriteStmt();
			break;
			default:
				throw new Exception("error unexpected kind at line: " + scanner.getLine() + " column: "+ scanner.getColumn());
		}
		return s;
	}
	
	private Stmt parseIfStmt() throws Exception{
		Stmt s;
		Exp e = parseExp();
		accept(Token.THEN);
		Stmt s1 = parseStmtSeq();
		switch(currentToken.kind){
			case Token.END:
				acceptIt();
				s = new IfCommand(e,s1);
				break;
			case Token.ELSE:
				acceptIt();
				Stmt s2 = parseStmtSeq();
				accept(Token.END);
				s = new IfCommand(e,s1,s2);
				break;
			default:
				throw new Exception("error expected \'else\' or \'end\' at line: " + scanner.getLine() + " column: "+ scanner.getColumn());
		}
		return s;
	}
	
	private Stmt parseRepeatStmt() throws Exception{
		Stmt s = parseStmtSeq();
		if(currentToken.kind == Token.UNTIL){
			acceptIt();
			Exp e = parseExp();
			s = new RepeatCommand(s,e);
		}else{			
			throw new Exception("error expected \'until\' at line: " + scanner.getLine() + " column: "+ scanner.getColumn());
		}
		return s;
	}
	
	private Stmt parseAssignStmt() throws Exception{
		VName v = parseVName();
		acceptIt();	
		accept(Token.ASSIGN);
		Exp e = parseExp();
		return new AssignCommand(v,e);
	}
	
	private Stmt parseReadStmt() throws Exception{
		return new ReadCommand(parseVName());
	}
	
	private Stmt parseWriteStmt() throws Exception{
		return new WriteCommand(parseExp());
	}
	
	private Exp parseExp() throws Exception{
		Exp e;
		SimpleExp s = parseSimpleExp();
		e = new SingleExp(s);
		if(currentToken.kind == Token.OPLESSTHAN) {
			acceptIt();
			SimpleExp s2 = parseSimpleExp();
			e = new OperationLessThan(s,s2);
		}
		else if(currentToken.kind == Token.OPEQUAL){
			acceptIt();
			SimpleExp s2 = parseSimpleExp();
			e = new OperationEqual(s,s2);
		}
		return e;
	}
	
	private SimpleExp parseSimpleExp() throws Exception{
		SimpleExp s;
		Term t = parseTerm();
		s = new SingleSimpleExp(t);
		while(currentToken.kind == Token.OPPLUS || currentToken.kind == Token.OPMINUS){
			if(currentToken.kind == Token.OPPLUS){
				acceptIt();
				Term t2 = parseTerm();
				s = new OperationPlus(s,t2);
			}
			else{
				acceptIt();
				Term t2 = parseTerm();
				s = new OperationMinus(s,t2);
			}
		}
		return s;
	}
	
	private Term parseTerm() throws Exception{
		Term t;
		Factor f = parseFactor();
		t = new SingleTerm(f);
		
		while(currentToken.kind == Token.OPMUL || currentToken.kind == Token.OPDIV){
			if(currentToken.kind == Token.OPMUL){
				acceptIt();
				Factor f2 = parseFactor();
				t = new OperationMultiply(t,f2);
			}
			else{
				acceptIt();
				Factor f2 = parseFactor();
				t = new OperationDivide(t,f2);
			}			
		}
		return t;
	}
	
	private Factor parseFactor() throws Exception{
		Factor f;
		switch (currentToken.kind){
			case Token.OPENPARENTHESIS:
				acceptIt();
				Exp e =parseExp();
				accept(Token.CLOSEPARENTHESIS);
				f = new FactorExp(e);
			break;
			case Token.IDENTIFIER:
				VName v =parseVName();  //get it before acceptIt change spelling	
				acceptIt();
				f = new FactorVName(v);
			break;
			case Token.NUMBER:
				f = new ConstCommand(new Number(currentToken.spelling));  //get it before acceptIt change spelling
				acceptIt();				
			break;
			default:
				throw new Exception("error unexpected kind at line: " + scanner.getLine() + " column: "+ scanner.getColumn());
		}
		return f;
	}

	private VName parseVName() throws Exception{
		Identifier i = new Identifier(currentToken.spelling);
		return new IdCommand(i);
	}

}
