
public class Main {

	public static void main(String[] args) throws Exception {
		Parser miniPParser = new Parser();
		int fileNum = 1;
		for(int i=1; i<args.length; i++){
			try{
				if(i%2==1){
					miniPParser.parse(args[i]);
					fileNum++;
				}
			}
			catch (Exception e){
				System.out.println("Error in file "+fileNum);
				System.out.println(e.getMessage());
			}
		}
	}
}
