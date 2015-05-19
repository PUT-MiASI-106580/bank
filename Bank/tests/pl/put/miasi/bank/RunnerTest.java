package pl.put.miasi.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.put.miasi.bank.commondata.Owner;
import pl.put.miasi.bank.facilities.BankOffice;
import pl.put.miasi.bank.manage.*;
import pl.put.miasi.bank.accountdata.Account;
import pl.put.miasi.bank.accountdata.IAccount;

public class RunnerTest {

	private static Random rand;
	private  KIR kir;
	private Banks bankList;
	private ArrayList<BankOffice> bankOffices;
	private Map<IAccount,Double> accountResultWithdraw;
	
	@BeforeClass
	public static void setUpClass() {
		rand = new Random(System.currentTimeMillis());
		
	}
	
	@Before
	public void setUp() throws Exception {
		kir = KIR.getKir();
		for(int i =0;i<4;i++){
			Bank temp = kir.createBank();
			int numOfAccounts = rand.nextInt(20);	
			if(numOfAccounts<0)
				numOfAccounts*=-1;
			for(int j =0;j<numOfAccounts;j++)
				temp.CreateAccount(null);			
			List<IAccount> tempAccounts = temp.getAccounts().getAccounts();
			for(int j=0;j<tempAccounts.size();j++){
				double tempAmount = rand.nextInt(100000)*rand.nextDouble();
				tempAccounts.get(i).deposit(tempAmount, "Pierwsza wp³ata");
				accountResultWithdraw.put(tempAccounts.get(i), tempAmount);
			}
			bankOffices.add(new BankOffice(1000000, temp));
			bankList.addBank(temp);				
		}		
	}
	
	@Test
	public void AntiFraudingFilterTest() throws Exception{
		IAccount account1 = bankList.getBanks().get(0).getAccounts().getAccounts().get(0);
		IAccount account2 = bankList.getBanks().get(1).getAccounts().getAccounts().get(0);
		bankOffices.get(0).Deposit(1000000, account1, new Owner());
		bankOffices.get(1).Deposit(1000000, account2, new Owner());		
		bankOffices.get(0).makeTransfer(account1, null, bankList.getBanks().get(1), account2,
				20001,"transfer testowy");
		kir.openSession();
		kir.processTransfersPackage();
		
	}	
	
	@Test
	public void AntiFraudingManyTransFilterTest() throws Exception{
		IAccount account1 = bankList.getBanks().get(0).getAccounts().getAccounts().get(0);
		IAccount account2 = bankList.getBanks().get(1).getAccounts().getAccounts().get(0);
		bankOffices.get(0).Deposit(1000000, account1, new Owner());
		bankOffices.get(1).Deposit(1000000, account2, new Owner());	
		for(int i =0;i<100;i++){
			bankOffices.get(0).makeTransfer(account1, null, bankList.getBanks().get(1), account2,
					20,"transfer testowy");	
			kir.openSession();
			kir.processTransfersPackage();		
		}
		
	}

	
	@Test
	public void testTransfers() throws Exception{
		for(int i =0;i<bankOffices.size();i++){
			int numOfAccountsFrom = bankList.getBanks().get(i).getAccounts().getAccounts().size();
			int numOfTransfers = rand.nextInt(50);
			for(int j=0;j<numOfTransfers;j++){
				IAccount tempAccountFrom = bankList.getBanks().get(i).getAccounts().getAccounts().get(rand.nextInt(numOfAccountsFrom));
				double tempAmount = rand.nextInt(30000)*rand.nextDouble();
				int idBankTo = i;
				while(idBankTo == i)
					idBankTo = rand.nextInt(5);
				Bank tempBankTo = bankList.getBanks().get(idBankTo);
				int numOfAccountsTo = tempBankTo.getAccounts().getAccounts().size();
				IAccount tempAccountTo = bankList.getBanks().get(idBankTo).getAccounts().getAccounts().get(rand.nextInt(numOfAccountsTo));
				bankOffices.get(i).makeTransfer(tempAccountFrom, null, tempBankTo, tempAccountTo,
						tempAmount,"transfer testowy");
				kir.openSession();
				kir.processTransfersPackage();	
					
			}			
		}		
	}

	@After
	public void tearDown() throws Exception {
	}

}
