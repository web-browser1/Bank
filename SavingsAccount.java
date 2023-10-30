package davanx1;
import java.util.*;
import java.math.*;


public class SavingsAccount extends Account
{

    /**
	
	 @author David Anderberg, davanx-1 
	 */
	
	 private int freeWithdraw = 0;
	 
	
	 
	 // ------- Konstruktorer ----------
	
	 public SavingsAccount() {
		 freeWithdraw = 1;
		 transactions = new ArrayList<String>();
	 }
	 
	 
	 public int getFreeWithdraw() 
	 {
		 return this.freeWithdraw;
	 }
	 public void setFreeWithdraw(int freeWithdraw) 
	 {
		 this.freeWithdraw = freeWithdraw;
	 }
	 
	 

	 @Override
	 public void setBalance(BigDecimal balance)
	 {
		 // TODO: Implement this method
		 super.setBalance(balance);
	 }

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



	 
	
	
}
