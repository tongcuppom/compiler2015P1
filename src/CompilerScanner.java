import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class CompilerScanner {

	private int currentChar;
	private StringBuffer currentSpelling;
	private byte currentKind;
	private int lineNumber;
	private int column;
	private boolean comment;
	private Reader reader;
	
	public CompilerScanner(String filePath) throws IOException{
		lineNumber = 1;
		column = 0;
		reader = new FileReader(filePath);	// get file path
		currentSpelling =  new StringBuffer();
		currentChar = 32; //dummy to get some letter first time (must go into the loop in scan)
		currentKind = 0;  //dummy to get some letter first time
	}
	
	private void take(char expectedChar) throws IOException{
		if(currentChar == expectedChar){
			currentSpelling.append((char)currentChar);
			int read = reader.read();
			if(read == -1){ //EOT
				currentChar = 3;
			}else if(read == 10){ // \n
				lineNumber++;
				column = 0;
			}
			else{
				currentChar = read;
				column++;
			}
			
			if(currentChar == 123) // {
				comment = true;
			if(currentChar == 125) // }
				comment = false;
		}
		else
			System.out.println("error expected \""+expectedChar+"\" at line: " + lineNumber + " column: "+column);
	}
	
	private void takeIt() throws IOException{
		currentSpelling.append((char)currentChar);
		
		int read = reader.read();
		
		if(read == -1){ //EOT
			currentChar = 3;
		}else if(read == 10){ // \n
			lineNumber++;
			column = 0;
		}
		else{
			currentChar = read;
			column++;
		}
		
		if(currentChar == 123) // {
			comment = true;
		if(currentChar == 125) // }
			comment = false;
	}
	
	public Token scan() throws IOException{
		while(comment || currentChar==125 || currentChar==32 || currentChar ==13 || currentChar ==10 || currentChar==9)
			scanSeperator();
		
		String spelling = "";
		if(currentKind!=Token.EOT){
			currentSpelling =  new StringBuffer();
			currentKind = scanToken();
			spelling = currentSpelling.toString();
			if(currentKind == Token.IDENTIFIER)
				currentKind = isKeywords(spelling);
		}
		
		return new Token(currentKind, spelling, lineNumber, column);
	}
	
	public int getLine(){
		return lineNumber;
	}
	
	public int getColumn(){
		return column;
	}
	
	private void scanSeperator() throws IOException{
		takeIt();
	}
	
	private byte scanToken() throws IOException{
		byte kind = 0;
		
		if((currentChar < 91 && currentChar > 64) || (currentChar < 123 && currentChar > 96)){  //Letter
			takeIt();
			while(isLetter(currentChar) || isDigit(currentChar))
				takeIt();
			kind = Token.IDENTIFIER;
		}
		else if(currentChar < 58 && currentChar > 47){  //Digit
			takeIt();
			while(isDigit(currentChar))
				takeIt();
			kind = Token.NUMBER;
		}
		else if(currentChar == 43){ // +
			takeIt();
			kind = Token.OPPLUS;
		}
		else if(currentChar == 45){ // -
			takeIt();
			kind = Token.OPMINUS;
		}
		else if(currentChar == 42){ // *
			takeIt();
			kind = Token.OPMUL;
		}
		else if(currentChar == 47){ // /
			takeIt();
			kind = Token.OPDIV;
		}
		else if(currentChar == 60){ // <
			takeIt();
			kind = Token.OPLESSTHAN;
		}
		else if(currentChar == 61){ // =
			takeIt();
			kind = Token.OPEQUAL;
		}
		else if(currentChar == 40){ // (
			takeIt();
			kind = Token.OPENPARENTHESIS;
		}
		else if(currentChar == 41){ // )
			takeIt();
			kind = Token.CLOSEPARENTHESIS;
		}
		else if(currentChar == 59){ // ;
			takeIt();
			kind = Token.SEMICOLON;
		}
		else if(currentChar == 58){ // :
			takeIt();
			take('=');
			kind = Token.ASSIGN;
		}
		else if(currentChar == 3){ // EOT
			kind = Token.EOT;
		}
		
		return kind;
	}
	
	private boolean isLetter(int tester){
		
		if((tester < 91 && tester > 64) || (tester < 123 && tester > 96)) 
			return true;
		else	
			return false;
		
	}
	
	private boolean isDigit(int tester){
		if(tester < 58 && tester > 47) 
			return true;
		else	
			return false;
	}
	
	private byte isKeywords(String spelling){
		if(spelling.equals("else"))
			return Token.ELSE;
		else if(spelling.equals("end"))
			return Token.END;
		else if(spelling.equals("if"))
			return Token.IF;
		else if(spelling.equals("read"))
			return Token.READ;
		else if(spelling.equals("repeat"))
			return Token.REPEAT;
		else if(spelling.equals("then"))
			return Token.THEN;
		else if(spelling.equals("until"))
			return Token.UNTIL;
		else if(spelling.equals("write"))
			return Token.WRITE;
		else
			return Token.IDENTIFIER;
	}
	
}
