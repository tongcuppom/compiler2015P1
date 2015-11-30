
public interface Visitor {
	
	//Program
	public Object visitProgram(Program prog, Object arg) throws Exception;
	
	//Command
	public Object visitAssignCommand(AssignCommand com, Object arg) throws Exception;
	public Object visitConstCommand(ConstCommand com, Object arg) throws Exception;
	public Object visitIfCommand(IfCommand com, Object arg) throws Exception;
	public Object visitReadCommand(ReadCommand com, Object arg) throws Exception;
	public Object visitRepeatCommand(RepeatCommand com, Object arg) throws Exception;
	public Object visitWriteCommand(WriteCommand com, Object arg) throws Exception;
	public Object visitIdCommand(IdCommand com, Object arg) throws Exception;
	
	//Expression
	public Object visitFactorExp(FactorExp exp, Object arg) throws Exception;
	public Object visitFactorVName(FactorVName exp, Object arg) throws Exception;
	public Object visitSingleExp(SingleExp exp, Object arg) throws Exception;
	public Object visitSingleSimpleExp(SingleSimpleExp exp, Object arg) throws Exception;
	public Object visitSingleTerm(SingleTerm exp, Object arg) throws Exception;
	
	//Statement
	public Object visitStmtSeq(StmtSeq exp, Object arg) throws Exception;
	
	//Operation
	public Object visitOperationDivide(OperationDivide op, Object arg) throws Exception;
	public Object visitOperationEqual(OperationEqual op, Object arg) throws Exception;
	public Object visitOperationLessThan(OperationLessThan op, Object arg) throws Exception;
	public Object visitOperationMinus(OperationMinus op, Object arg) throws Exception;
	public Object visitOperationMultiply(OperationMultiply op, Object arg) throws Exception;
	public Object visitOperationPlus(OperationPlus op, Object arg) throws Exception;
	
	//Identifier
	public Object visitIdentifier(Identifier i, Object arg) throws Exception;
	public Object visitNumber(Number i, Object org) throws Exception;
}
