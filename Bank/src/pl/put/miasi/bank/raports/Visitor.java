package pl.put.miasi.bank.raports;


public interface Visitor {

	FullHistory visit(FullHistory fullHistory);

	WithdrawRaport visit(WithdrawRaport withdrawRaport);

	DepositRaport visit(DepositRaport depositRaport);

	
}
