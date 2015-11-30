import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class Traverse {
	
 String prefix;
 Writer writer;
	
	public Traverse(Writer writer) throws FileNotFoundException{
		this.writer = writer;
		prefix = "";
	}
	
	public void printTree(AST a, String pre) throws IOException{
		writer.write(a.isTag ? pre+a.tagName:"");
		if(a.numChildren>0){
			for(int i=0; i<a.numChildren; i++){
				AST temp = a.getChild(i);				
				printTree(temp, a.isTag? pre+".":pre);
			}
		}
		else
			writer.write(a.spelling+"\n");
	}
}
