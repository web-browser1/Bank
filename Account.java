

package davanx1;

import java.math.*;
import java.util.*;

public abstract class Account
{

    /**
	Skapar konto med saldo, räntesats, kontonummer och kontotyp
	@author David Anderberg, davanx-1 
	*/


	// ------- Instansvariabler ----------

	private BigDecimal balance;
	private BigDecimal rateOfInterest;

	private int accountNumber;
	private String accountType;
	
	
	protected int creditLimit;


	protected ArrayList<String> transactions;
	abstract void setTransactions(String transactionStr);
	abstract ArrayList<String> getTransactions();


	// ------- Konstruktorer ----------

	public Account(int accountNumber, BigDecimal balance, BigDecimal rateOfInterest)
	{
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.rateOfInterest = rateOfInterest;
		
		
	}
	
	public Account()
	{

	    balance = new BigDecimal("0.0");
		rateOfInterest = new BigDecimal("0.0");
		accountNumber = 1000;
	//	accountType = "Sparkonto";
	

	}
	



	// ------- Set-metoder ----------

	public void setBalance(BigDecimal balance)
	{
		this.balance = balance;
	}
	public void setRateOfInterest(BigDecimal rateOfInterest)
	{
		this.rateOfInterest = rateOfInterest;
	}
	public void setAccountNumber(int accountNumber)
	{
		this.accountNumber = accountNumber;
	}
	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}


	// ------- Get-metoder ----------

	public BigDecimal getBalance()
	{
		return this.balance;
	}
	public BigDecimal getRateOfInterest()
	{
		return this.rateOfInterest;
	}
	public int getAccountNumber()
	{
		return this.accountNumber;
	}
	public String getAccountType()
	{
		return this.accountType;
	}






}
