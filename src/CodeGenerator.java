
public final class CodeGenerator implements Visitor{
	
	private Instruction[] code = new Instruction[1024];
	private short nextInstrAddr = 0;
	
	private void emit(byte op, byte n, byte r, short d){
		code[nextInstrAddr++] = new Instruction(op,n,r,d);
	}
	
	public void encode(Program prog){
		prog.visit(this, null);
	}
	
	public short valuation(String s){
		return Short.parseShort(s);
	}
	
	private void patch(short addr, short d){
		code[addr].d = d;
	}

	@Override
	public Object visitProgram(Program prog, Object arg) throws Exception {
		
		emit(Instruction.LDop,new Byte("6"),new Byte("0"),new Short("0"));
		emit(Instruction.STop,new Byte("0"),new Byte("0"),new Short("0"));
		
		prog.s.visit(this, arg);
		
		emit(Instruction.HALTop,new Byte("0"),new Byte("0"),new Short("0"));
		return null;
	}

	@Override
	public Object visitAssignCommand(AssignCommand com, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitConstCommand(ConstCommand com, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitIfCommand(IfCommand com, Object arg) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitReadCommand(ReadCommand com, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitRepeatCommand(RepeatCommand com, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitWriteCommand(WriteCommand com, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitIdCommand(IdCommand com, Object arg) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitFactorExp(FactorExp exp, Object arg) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitFactorVName(FactorVName exp, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitSingleExp(SingleExp exp, Object arg) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitSingleSimpleExp(SingleSimpleExp exp, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitSingleTerm(SingleTerm exp, Object arg) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitStmtSeq(StmtSeq exp, Object arg) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitOperationDivide(OperationDivide op, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitOperationEqual(OperationEqual op, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitOperationMultiply(OperationMultiply op, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitOperationPlus(OperationPlus op, Object arg)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitIdentifier(Identifier i, Object arg) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitNumber(Number i, Object org) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
