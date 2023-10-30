package davanx1;

import java.util.*;
import javax.security.auth.*;


public class Customer
{

	/** Skapar kund med namn, efternamn, personnummer och konton
	@author David Anderberg, davanx-1 */


	// ------- Instansvariabler ----------

    private String firstName;
	private String surName;

	private String pNo;

	// accounts

	private ArrayList<Account> account;


	// ------- Konstruktorer ----------

	public Customer()
	{
		firstName = "";
		surName = "";
		pNo = "";
		account = new ArrayList<Account>();


	}


	public String toString()
	{
		return String.format("%s %s %s", this.pNo, this.firstName, this.surName);
	}

	
	public Account getAccount(int accountId) {
		
		Account rAccount = null;
		
		for(Account accountCurrent : account) {
			
			if(accountCurrent.getAccountNumber() == accountId)
			{
				
				rAccount = accountCurrent;
			}
			
		}
		
		return rAccount;
	}
	
	public Account getAccountIndex(int index) {
		
		return account.get(index);
	}
	
	public int getAccountLength() {
		return account.size();
	}
	
	public void deleteAccount(Account account) 
	{
		this.account.remove(account);
	}
	
	public void addAccount(Account account)
	{
		this.account.add(account);
	}
	
	

	// ------- Set-metoder ----------

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public void setSurName(String surName)
	{
		this.surName = surName;
	}
	public void setpNo(String pNo)
	{
		this.pNo = pNo;
	}

	public void setAccount(ArrayList<Account> account)
	{
		this.account = account;
	}


	// ------- Get-metoder ----------

	public String getFirstName()
	{
		return this.firstName;
	}
	public String getSurName()
	{
		return this.surName;
	}
    public String getpNo()
	{
		return this.pNo;
	}

	public ArrayList<Account> getAccount()
	{
		return this.account;
	}







}
