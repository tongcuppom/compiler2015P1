
public class Traverse {
	
 String prefix;
	
	public Traverse(){
		prefix = "";
	}
	
	public void printTree(AST a, String pre){
		System.out.print(a.isTag ? pre+a.tagName:"");
		if(a.numChildren>0){
			for(int i=0; i<a.numChildren; i++){
				AST temp = a.getChild(i);				
				printTree(temp, a.isTag? pre+".":pre);
			}
		}
		else
			System.out.println(a.spelling);
	}
}
