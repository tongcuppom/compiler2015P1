
public final class CodeGenerator implements Visitor{
	
	private Instruction[] code;
	private short nextInstrAddr;
	private IdentificationTable idTable;
	
	public CodeGenerator(){
		code = new Instruction[1024];
		nextInstrAddr = 0;
		idTable = new IdentificationTable();
	}
	
	private void emit(byte op, byte n, byte r, short d){
		code[nextInstrAddr++] = new Instruction(op,n,r,d);
	}
	
	public void encode(Program prog) throws Exception{
		prog.visit(this, null);
		printCode();
	}
	
	public short valuation(String s){
		return Short.parseShort(s);
	}
	
	private void patch(short addr, short d){
		code[addr].d = d;
	}
	
	private void printCode(){
		for(int i=0; i<nextInstrAddr; i++){
			Instruction temp = code[i];
			String strOut = "";
			switch(temp.op){
				case 0:
					strOut+= "LD ";
					break;
				case 1:
					strOut+= "LDA ";
					break;
				case 2:
					strOut+= "LDC ";
					break;
				case 3:
					strOut+= "ST ";
					break;
				case 4:
					strOut+= "JLT ";
					break;
				case 5:
					strOut+= "JLE ";
					break;
				case 6:
					strOut+= "JGT ";
					break;
				case 7:
					strOut+= "JGE ";
					break;
				case 8:
					strOut+= "JEQ ";
					break;
				case 9:
					strOut+= "JNE ";
					break;
				case 10:
					strOut+= "HALT ";
					break;
				case 11:
					strOut+= "IN ";
					break;
				case 12:
					strOut+= "OUT ";
					break;
				case 13:
					strOut+= "ADD ";
					break;
				case 14:
					strOut+= "SUB ";
					break;
				case 15:
					strOut+= "MUL ";
					break;
				case 16:
					strOut+= "DIV ";
					break;
			}
			
			System.out.println(strOut);
		}
	}
	
	private void encodeAssign(IdCommand i){
		String spelling = i.i.getSpelling();
		Attribute temp = idTable.retrieve(spelling); 
		
		if(temp != null){
			int dMem = temp.dMemPlace;
			emit(Instruction.STop,Instruction.MPr,Instruction.ACr,(short) dMem);  // ST ACr, dMem(MPr)
		}
		else{
			idTable.enter(spelling, new Attribute(Attribute.VAR, Attribute.INT, idTable.lastInDMem));
			int dMem = idTable.retrieve(spelling).dMemPlace;
			emit(Instruction.STop,Instruction.MPr,Instruction.ACr,(short) dMem);  // ST ACr, dMem(MPr)
		}
	}

	@Override
	public Object visitProgram(Program prog, Object arg) throws Exception {
		
		emit(Instruction.LDop,new Byte("0"),Instruction.MPr,new Short("0")); // LD MPr, 0(0)
		emit(Instruction.STop,new Byte("0"),Instruction.ACr,new Short("0")); // ST ACr, 0(0)
		
		prog.s.visit(this, arg);
		
		emit(Instruction.HALTop,new Byte("0"),new Byte("0"),new Short("0")); // HALT 0,0,0
		
		return null;
	}

	@Override
	public Object visitAssignCommand(AssignCommand com, Object arg)
			throws Exception {
		com.e.visit(this, arg);
		encodeAssign(com.i);
		return null;
	}

	@Override
	public Object visitConstCommand(ConstCommand com, Object arg)
			throws Exception {
		com.n.visit(this, arg);
		return null;
	}

	@Override
	public Object visitIfCommand(IfCommand com, Object arg) throws Exception {
		com.e.visit(this, arg);
		
		short h = nextInstrAddr;
		emit(Instruction.JEQop,Instruction.PCr,Instruction.ACr,new Short("0")); // JEQ ACr, dummy(PCr)
		
		com.s1.visit(this, arg);
		
		short g = nextInstrAddr;
		
		if(com.numChildren==3){	
			short h2 = nextInstrAddr;
			emit(Instruction.LDAop,Instruction.PCr,Instruction.PCr,new Short("0")); // LDA PCr, dummy(PCr) 
			com.s2.visit(this, arg);			
			short g2 = nextInstrAddr;
			patch(h2,(short) (g2-h2)); // LDA PCr, (g2-h2)(PCr)
		}
		patch(h,(short) (g-h)); // JEQ ACr, (g-h)(PCr)
		return null;
	}

	@Override
	public Object visitReadCommand(ReadCommand com, Object arg)
			throws Exception {
		emit(Instruction.INop,new Byte("0"),Instruction.ACr,new Short("0"));  // IN ACr, 0, 0
		encodeAssign(com.i);
		return null;
	}

	@Override
	public Object visitRepeatCommand(RepeatCommand com, Object arg)
			throws Exception {
		short h = nextInstrAddr;
		com.s.visit(this, arg);
		com.e.visit(this, arg);
		short g = nextInstrAddr;
		emit(Instruction.JEQop,Instruction.PCr,Instruction.ACr,(short)(g-h));	// JEQ ACr, (g-h)(PCr)
		return null;
	}

	@Override
	public Object visitWriteCommand(WriteCommand com, Object arg)
			throws Exception {
		com.e.visit(this, arg);
		emit(Instruction.OUTop,new Byte("0"),Instruction.ACr,new Short("0")); // OUT ACr,0,0
		return null;
	}

	@Override
	public Object visitIdCommand(IdCommand com, Object arg) throws Exception {
		if(arg != null){
			if((int)arg == 1){ // first argument
				byte place = (byte)idTable.retrieve(com.i.spelling).dMemPlace;
				emit(Instruction.LDop,Instruction.GPr,Instruction.ACr,place);			// LD ACr, place(GPr)
				emit(Instruction.STop,Instruction.MPr,Instruction.ACr,new Short("0"));	// ST ACr, 0(MPr)
			}
			else{
				byte place = (byte)idTable.retrieve(com.i.spelling).dMemPlace;
				emit(Instruction.LDop,Instruction.GPr,Instruction.ACr,place);		// LD ACr, place(GPr)
			}
		}
		else{
			byte place = (byte)idTable.retrieve(com.i.spelling).dMemPlace;
			emit(Instruction.LDop,Instruction.GPr,Instruction.ACr,place);		// LD ACr, place(GPr)
		}
		return null;
	}

	@Override
	public Object visitFactorExp(FactorExp exp, Object arg) throws Exception {
		exp.e.visit(this, arg);
		return null;
	}

	@Override
	public Object visitFactorVName(FactorVName exp, Object arg)
			throws Exception {
		exp.i.visit(this, arg);
		return null;
	}

	@Override
	public Object visitSingleExp(SingleExp exp, Object arg) throws Exception {
		exp.s.visit(this, arg);
		return null;
	}

	@Override
	public Object visitSingleSimpleExp(SingleSimpleExp exp, Object arg)
			throws Exception {
		exp.t.visit(this, arg);
		return null;
	}

	@Override
	public Object visitSingleTerm(SingleTerm exp, Object arg) throws Exception {
		exp.f.visit(this, arg);
		return null;
	}

	@Override
	public Object visitStmtSeq(StmtSeq exp, Object arg) throws Exception {
		exp.s1.visit(this, null);
		exp.s2.visit(this, null);
		return null;
	}

	@Override
	public Object visitOperationDivide(OperationDivide op, Object arg)
			throws Exception {
		op.t.visit(this, 1);
		op.f.visit(this, 2);
		emit(Instruction.LDop,Instruction.MPr,Instruction.AC1r,new Short("0"));  	// LD 1,0(MPr)
		emit(Instruction.DIVop,new Byte("0"),new Byte("0"),new Short("0"));			// DIV 0,1,0
		return null;
	}

	@Override
	public Object visitOperationEqual(OperationEqual op, Object arg)
			throws Exception {
		op.s1.visit(this, 1);
		op.s2.visit(this, 2);
		emit(Instruction.LDop,Instruction.MPr,Instruction.AC1r,new Short("0"));  	// LD 1,0(MPr)
		emit(Instruction.SUBop,new Byte("0"),new Byte("0"),new Short("0"));			// SUB 0,1,0
		emit(Instruction.JEQop,Instruction.PCr,Instruction.ACr,new Short("2"));		// JEQ ACr, 2(PCr)
		emit(Instruction.LDCop,new Byte("0"),Instruction.ACr,new Short("0"));  		// LDC ACr,0(0)
		emit(Instruction.LDAop,Instruction.PCr,Instruction.PCr,new Short("1"));  	// LDA PCr,1(PCr)
		emit(Instruction.LDCop,new Byte("0"),Instruction.ACr,new Short("1"));  		// LDC ACr,1(0)
		return null;
	}

	@Override
	public Object visitOperationLessThan(OperationLessThan op, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitOperationMinus(OperationMinus op, Object arg)
			throws Exception {
		op.s.visit(this, 1);
		op.t.visit(this, 2);
		emit(Instruction.LDop,Instruction.MPr,Instruction.AC1r,new Short("0"));  	// LD 1,0(MPr)
		emit(Instruction.SUBop,new Byte("0"),new Byte("0"),new Short("0"));			// SUB 0,1,0
		return null;
	}

	@Override
	public Object visitOperationMultiply(OperationMultiply op, Object arg)
			throws Exception {
		op.t.visit(this, 1);
		op.f.visit(this, 2);
		emit(Instruction.LDop,Instruction.MPr,Instruction.AC1r,new Short("0"));  	// LD 1,0(MPr)
		emit(Instruction.MULop,new Byte("0"),new Byte("0"),new Short("0"));			// MUL 0,1,0
		return null;
	}

	@Override
	public Object visitOperationPlus(OperationPlus op, Object arg)
			throws Exception {
		op.s.visit(this, 1);
		op.t.visit(this, 2);
		emit(Instruction.LDop,Instruction.MPr,Instruction.AC1r,new Short("0"));  	// LD 1,0(MPr)
		emit(Instruction.ADDop,new Byte("0"),new Byte("0"),new Short("0"));			// ADD 0,1,0
		return null;
	}

	@Override
	public Object visitIdentifier(Identifier i, Object arg) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitNumber(Number i, Object org) throws Exception {
		short val = valuation(i.spelling);
		
		if(org != null){
			if((int)org == 1){ // first argument
				emit(Instruction.LDCop,Instruction.GPr,Instruction.ACr,val);				// LD ACr, val(GPr)
				emit(Instruction.STop,Instruction.MPr,Instruction.ACr,new Short("0"));	// ST ACr, 0(MPr)
			}
			else{
				emit(Instruction.LDCop,Instruction.GPr,Instruction.ACr,val);		// LD ACr, val(GPr)
			}
		}
		else{
			emit(Instruction.LDCop,Instruction.GPr,Instruction.ACr,val);		// LD ACr, val(GPr)
		}
		return null;
	}

}
