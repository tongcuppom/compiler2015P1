
public class Checker implements Visitor{

	private IdentificationTable idTable;
	
	public void checkProgram(Program prog){
		idTable = new IdentificationTable();
		
		prog.visit(this, null);
	}
	
	@Override
	public Object visitProgram(Program prog, Object arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitAssignCommand(AssignCommand com, Object arg) throws Exception {
		Type vType = (Type) com.i.visit(this, null);
		Type eType = (Type) com.e.visit(this, null);
		
		if(! eType.equals(vType)){
			throw new Exception("error incompatible type");
		}
		
		return null;
	}

	@Override
	public Object visitConstCommand(ConstCommand com, Object arg) throws Exception {
		com.n.visit(this, null);
		
		return null;
	}

	@Override
	public Object visitIfCommand(IfCommand com, Object arg) throws Exception {
		com.s1.visit(this, null);
		Type eType = (Type) com.e.visit(this, null);
		if(!eType.equals(Type.BOOL)) throw new Exception("Expression doesn't return boolean");
		if(com.numChildren==3) com.s2.visit(this, null);
		
		return null;
	}

	@Override
	public Object visitReadCommand(ReadCommand com, Object arg) throws Exception {
		com.i.visit(this, null);
		return null;
	}

	@Override
	public Object visitRepeatCommand(RepeatCommand com, Object arg) throws Exception {
		com.s.visit(this, null);
		Type eType = (Type) com.e.visit(this, null);
		if(!eType.equals(Type.BOOL)) throw new Exception("Expression doesn't return boolean");
		
		return null;
	}

	@Override
	public Object visitWriteCommand(WriteCommand com, Object arg) throws Exception {
		com.e.visit(this, null);
		
		return null;
	}

	@Override
	public Object visitIdCommand(IdCommand com, Object arg) throws Exception {
		com.i.visit(this, null);
		
		return null;
	}

	@Override
	public Object visitFactorExp(FactorExp exp, Object arg) throws Exception {
		
		return (Type) exp.e.visit(this, null);
	}

	@Override
	public Object visitFactorVName(FactorVName exp, Object arg) throws Exception {
		Type vType = (Type) exp.i.visit(this, null);
		return vType;
	}

	@Override
	public Object visitSingleExp(SingleExp exp, Object arg) throws Exception {
		
		return (Type) exp.s.visit(this, null);
	}

	@Override
	public Object visitSingleSimpleExp(SingleSimpleExp exp, Object arg) throws Exception {
		
		return (Type) exp.t.visit(this, null);
	}

	@Override
	public Object visitSingleTerm(SingleTerm exp, Object arg) throws Exception {
		
		return (Type) exp.f.visit(this, null);
	}

	@Override
	public Object visitStmtSeq(StmtSeq exp, Object arg) throws Exception {
		exp.s1.visit(this, null);
		exp.s2.visit(this, null);
		
		return null;
	}

	@Override
	public Object visitOperationDivide(OperationDivide op, Object arg) throws Exception {
		Type sType = (Type) op.f.visit(this, null);
		Type tType = (Type) op.t.visit(this, null);
		
		if(!sType.equals(Type.INT)) throw new Exception("First parameter is not INT");
		if(!tType.equals(Type.INT)) throw new Exception("Second parameter is not INT");
		if(op.t.spelling.equals("0")) throw new Exception("Divided by zero");
		
		return Type.INT;
	}

	@Override
	public Object visitOperationEqual(OperationEqual op, Object arg) throws Exception {
		Type s1Type = (Type) op.s1.visit(this, null);
		Type s2Type = (Type) op.s2.visit(this, null);
		
		if(!s1Type.equals(s2Type)) throw new Exception("error incompatible type");
		
		return Type.BOOL;
	}

	@Override
	public Object visitOperationLessThan(OperationLessThan op, Object arg) throws Exception {
		Type s1Type = (Type) op.s1.visit(this, null);
		Type s2Type = (Type) op.s2.visit(this, null);
		
		if(!s1Type.equals(Type.INT)) throw new Exception("First parameter is not INT");
		if(!s2Type.equals(Type.INT)) throw new Exception("Second parameter is not INT");
		
		return Type.BOOL;
	}

	@Override
	public Object visitOperationMinus(OperationMinus op, Object arg) throws Exception {
		Type sType = (Type) op.s.visit(this, null);
		Type tType = (Type) op.t.visit(this, null);
		
		if(!sType.equals(Type.INT)) throw new Exception("First parameter is not INT");
		if(!tType.equals(Type.INT)) throw new Exception("Second parameter is not INT");
		
		return Type.INT;
	}

	@Override
	public Object visitOperationMultiply(OperationMultiply op, Object arg) throws Exception {
		Type sType = (Type) op.f.visit(this, null);
		Type tType = (Type) op.t.visit(this, null);
		
		if(!sType.equals(Type.INT)) throw new Exception("First parameter is not INT");
		if(!tType.equals(Type.INT)) throw new Exception("Second parameter is not INT");
		
		return Type.INT;
	}

	@Override
	public Object visitOperationPlus(OperationPlus op, Object arg) throws Exception {
		Type sType = (Type) op.s.visit(this, null);
		Type tType = (Type) op.t.visit(this, null);
		
		if(!sType.equals(Type.INT)) throw new Exception("First parameter is not INT");
		if(!tType.equals(Type.INT)) throw new Exception("Second parameter is not INT");
		
		return Type.INT;
	}

	@Override
	public Object visitIdentifier(Identifier i, Object arg) {
		String spelling = i.getSpelling();
		Attribute temp =idTable.retrieve(spelling); 
		
		if(temp != null){
			return temp.type;
		}
		else{
			idTable.enter(spelling, new Attribute(Attribute.VAR, Attribute.INT, idTable.lastInDMem));
		}
		
		return null;
	}

	@Override
	public Object visitNumber(Number i, Object org) {
		return Type.INT;
	}

}
