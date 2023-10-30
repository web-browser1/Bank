package davanx1;
import java.math.*;
import java.util.*;

public class CreditAccount extends Account
{

    /**

	 @author David Anderberg, davanx-1 
	 */
	
	 
	// private BigDecimal interest;
	 
	// private int creditLimit;
	 
	private BigDecimal debtInterest;
	

	

	@Override
	void setTransactions(String transactionStr)
	{
		// TODO: Implement this method
		transactions.add(transactionStr);
	}

	@Override
	ArrayList<String> getTransactions()
	{
		// TODO: Implement this method
	    return transactions;
	}



	 
	// ------- Konstruktorer ----------
	
	 public CreditAccount() {
		 
		 
		// interest = new BigDecimal("0.5");
		 
		 debtInterest = new BigDecimal("7.0");
		 
		 creditLimit = 5000;
		 
		 transactions = new ArrayList<String>();
		 
		 
	 }
	 
	 
	 
	 public int getCreditLimit() 
	 {
		 return this.creditLimit;
	 }
	 
	 
	
	 
	 
	 
	 
	
	
	
}
