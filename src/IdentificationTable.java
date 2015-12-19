import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class IdentificationTable {

	private int level;
	public byte lastInDMem;
	private LinkedList<Map<String,Attribute>> idenTable;
	
	public IdentificationTable(){
		idenTable = new LinkedList<Map<String,Attribute>>();
		level = 0;
		lastInDMem = 0;
		idenTable.add(new HashMap<String,Attribute>());
	}
	
	public void enter(String id, Attribute att){
		lastInDMem++;
		idenTable.get(level).put(id, att);
	}
	
	public Attribute retrieve(String id){
		Attribute ret = null;
		for(int i=level; i>=0; i--){
			if(idenTable.get(i).containsKey(id)){
				ret = idenTable.get(i).get(id);
				break;
			}
		}
		return ret;
	}
	
	public void openScope(){
		if(idenTable.size()-1<=level){
			idenTable.add(new HashMap<String,Attribute>());
		}
		level++;
	}
	
	public void closeScope(){
		idenTable.get(level).clear();
		level--;
	}
}
