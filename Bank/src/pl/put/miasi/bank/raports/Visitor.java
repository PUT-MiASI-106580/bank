package pl.put.miasi.bank.raports;


public interface Visitor {

	FullHistory visit(FullHistory fullHistory);

	WithdrawReport visit(WithdrawReport withdrawReport);

	DepositReport visit(DepositReport depositReport);

	
}
