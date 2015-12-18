import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class Main {

	public static void main(String[] args) throws Exception {
		Parser miniPParser = new Parser();
		int fileNum = 0;
		String fileName = "";
		for(int i=1; i<args.length; i++){
			try{
				if(i%2==1){
					String path = new File("").getAbsolutePath();
					fileNum++;
					
					fileName = args[i];
					String pathFile = "\\";
					if(args[i].lastIndexOf("\\")!=-1) pathFile = args[i].substring(0,args[i].lastIndexOf("\\"));   // args[i] = \minip\ex001.minip
					if(args[i].lastIndexOf("\\")!=-1) fileName = args[i].substring(args[i].lastIndexOf("\\"));   // args[i] = \minip\ex001.minip
					if(fileName.lastIndexOf(".")!=-1) fileName = fileName.substring(0,fileName.lastIndexOf("."));  // filename will be only ex001
					
					Program p = miniPParser.parse(path+ pathFile + fileName + ".minip");  // input name = ex001.minip
					
					Checker checker = new Checker();
					checker.checkProgram(p);
					
					CodeGenerator cg = new CodeGenerator();
					cg.encode(p);
					
					OutputStream outputStream = new FileOutputStream(new File(path+pathFile+fileName+".tree"));  // output name = ex001.tree
					Writer writer = new OutputStreamWriter(outputStream);
					
					Traverse t = new Traverse(writer);
					t.printTree(p, "");
					
					writer.close();
				}
			}
			catch (Exception e){
				System.out.println("Error in file "+fileNum+" : "+fileName+".minip");
				System.out.println(e.getMessage());
			}
		}
	}
}
