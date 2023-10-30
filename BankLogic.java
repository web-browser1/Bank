

package davanx1;

import java.util.*;
import java.text.*;
import java.math.*;

public class BankLogic
{

	/** Klass med metoder för logik av listan med kunder
	 @author David Anderberg, davanx-1 */

	private ArrayList<Customer> customers = new ArrayList<Customer>();

	private int currentAccountNumber = 1000;


	public BankLogic()
	{
		currentAccountNumber = 1000;

		customers = new ArrayList<Customer>();

	}	


	/**
	 @param 

	 @return ArrayList value
	 */

	public ArrayList<String> getAllCustomers()
	{

		ArrayList<String> customerList = new ArrayList<String>();

		for (Customer customer: customers)
		{
			customerList.add(customer.toString());
		}


		return customerList;
	} 


	/**
	 @param String name, String surname, String pNo

	 @return boolean value
	 */

	public boolean createCustomer(String name, String surname, String pNo)
	{

		boolean uniqueCustomer = true;

		for (Customer customer : customers)
		{
			if (customer.getpNo().equals(pNo))
			{
				uniqueCustomer = false;
			}

		}

		if (uniqueCustomer)
		{

			Customer customer = new Customer();

			customer.setFirstName(name);
			customer.setSurName(surname);
			customer.setpNo(pNo);


			customers.add(customer);

			return true;

		}
		else
		{

			return false;
		}


	}




	/**
	 @param String pNo

	 @return ArrayList value
	 */

	public ArrayList<String> getCustomer(String pNo)
	{

		ArrayList<String> customerList = new ArrayList<String>();

		for (Customer customer : customers)
		{


			if (customer.getpNo().equals(pNo))
			{

				customerList.add(customer.toString());


			    for (int a = 0; a < customer.getAccountLength(); a++)
				{

					Account account = customer.getAccountIndex(a);

					if (account != null)
					{

						BigDecimal balance = customer.getAccount().get(a).getBalance();

						String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(balance.floatValue());


						BigDecimal interest = customer.getAccount().get(a).getRateOfInterest();

						NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv", "SE"));
						percentFormat.setMaximumFractionDigits(1); // Anger att vi vill ha max 1 decimal
						String percentStr = percentFormat.format(interest.floatValue() / 100);


						customerList.add(String.format("%s %s %s %s",
													   customer.getAccount().get(a).getAccountNumber(),
													   balanceStr,
													   customer.getAccount().get(a).getAccountType(),
													   percentStr));

					}

				}			   


			}

		}


		if (customerList.size() < 1)
		{
			customerList = null;
		}


		return customerList;
	}






	/**
	 @param String name, String surname, String pNo

	 @return boolean value
	 */


	public boolean changeCustomerName(String name, String surname, String pNo)
	{

		boolean bChangeCustomerName = false;

		for (Customer customer : customers)
		{

			if (customer.getpNo().equals(pNo))
			{
				if (name.length() > 0)
				{
					bChangeCustomerName = true;
					customer.setFirstName(name);
				}
				if (surname.length() > 0)
				{
					bChangeCustomerName = true;
					customer.setSurName(surname);
				}



			}

		}


		return bChangeCustomerName;
	}




	/**
	 @param String pNo

	 @return int value
	 */

	public int createSavingsAccount(String pNo)
	{

		int nAccount = -1;

		for (Customer customer : customers)
		{

			if (customer.getpNo().equals(pNo))
			{

				currentAccountNumber++;

				Account account = new SavingsAccount();

				account.setAccountType("Sparkonto");

				account.setRateOfInterest(new BigDecimal("1.2"));

				account.setAccountNumber(currentAccountNumber);

				customer.addAccount(account);

				nAccount = currentAccountNumber;

			}

		}


		return nAccount;
	}




	/**
	 @param String pNo, int accountId

	 @return String value
	 */

	public String getAccount(String pNo, int accountId)
	{

		String sGetAccount = null;

		for (Customer customer : customers)
		{

			if (customer.getpNo().equals(pNo))
			{

				Account account = customer.getAccount(accountId);

				if (account != null)
				{

					BigDecimal balance = account.getBalance();

					String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(balance.floatValue());


					BigDecimal interest = account.getRateOfInterest();

					NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv", "SE"));
					percentFormat.setMaximumFractionDigits(1); // Anger att vi vill ha max 1 decimal
					String percentStr = percentFormat.format(interest.floatValue() / 100);


					sGetAccount = String.format("%s %s %s %s",
												account.getAccountNumber(),
												balanceStr,
												account.getAccountType(),
												percentStr);




				}



			}

		}




		return sGetAccount;
	}





	/**
	 @param String pNo, int accountId, int amount

	 @return boolean value
	 */


	public boolean deposit(String pNo, int accountId, int amount)
	{

		boolean bDeposit = false;


		for (Customer customer : customers)
		{

			if (customer.getpNo().equals(pNo))
			{

				Account account = customer.getAccount(accountId);

				if (account != null)
				{

					if (amount > 0)
					{

						String accoutType = account.getAccountType();

						int balance = account.getBalance().intValue();
						balance += amount;



						if (accoutType.equals("Sparkonto")) 
						{


							String amountStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(amount);


							String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(balance);


							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String strDate = sdf.format(new Date());


							String transactionStr = String.format("%s %s Saldo: %s", strDate, amountStr, balanceStr);


							account.setTransactions(transactionStr);



							account.setBalance(new BigDecimal(String.valueOf(balance)));

							bDeposit = true;


						}
						else if (accoutType.equals("Kreditkonto"))
						{


							String amountStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(amount);


							String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(balance);


							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String strDate = sdf.format(new Date());


							String transactionStr = String.format("%s %s Saldo: %s", strDate, amountStr, balanceStr);


							account.setTransactions(transactionStr);



							account.setBalance(new BigDecimal(String.valueOf(balance)));

							bDeposit = true;





						}



					}




				}


		    }


		}





		return bDeposit;
	}





	/**
	 @param String pNo, int accountId, int amount

	 @return boolean value
	 */

	public boolean withdraw(String pNo, int accountId, int amount)
	{

		boolean bWithdraw = false;


		for (Customer customer : customers)
		{

			if (customer.getpNo().equals(pNo))
			{

				Account account = customer.getAccount(accountId);

				if (account != null)
				{

					if (amount > 0)
					{

						int balance = account.getBalance().intValue();

						String accountType = account.getAccountType();

						if (accountType.equals("Sparkonto"))
						{

							if (((SavingsAccount) account).getFreeWithdraw() == 1)
							{
								((SavingsAccount) account).setFreeWithdraw(0);
							}
							else 
							{
								amount = amount + (int)(amount * 0.02f);
							}

							if (amount <= balance)
							{


								balance -= amount;


								String amountStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(-amount);


								String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(balance);


								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String strDate = sdf.format(new Date());


								String transactionStr = String.format("%s %s Saldo: %s", strDate, amountStr, balanceStr);


								account.setTransactions(transactionStr);

								account.setBalance(new BigDecimal(String.valueOf(balance)));

								bWithdraw = true;

							}


						}
						else if (accountType.equals("Kreditkonto"))
						{

							int creditLimit = ((CreditAccount) account).getCreditLimit();

							int creditBalance = account.getBalance().intValue();

							if ((creditBalance - amount) >= -creditLimit)
							{

								balance -= amount;

								String amountStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(-amount);


								String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(balance);


								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String strDate = sdf.format(new Date());


								String transactionStr = String.format("%s %s Saldo: %s", strDate, amountStr, balanceStr);


								account.setTransactions(transactionStr);

								account.setBalance(new BigDecimal(String.valueOf(balance)));

								if (balance < 0)
								{

									account.setRateOfInterest(new BigDecimal("7.0"));
								} 

								bWithdraw = true;

							}

						}



					}

				}





		    }


		}




		return bWithdraw;
	}




	/**
	 @param String pNo, int accountId

	 @return String value
	 */


	public String closeAccount(String pNo, int accountId)
	{

		String sCloseAccount = null;

		for (Customer customer : customers)
		{

			if (customer.getpNo().equals(pNo))
			{

				Account account = customer.getAccount(accountId);

				if (account != null)
				{


					BigDecimal balance = account.getBalance();

					String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(balance.intValue());




					BigDecimal interest = account.getRateOfInterest();

					float interestAmount = (balance.floatValue() * interest.floatValue() / 100);

					String interestStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(interestAmount);



					sCloseAccount = String.format("%s %s %s %s",
												  account.getAccountNumber(),
												  balanceStr,
												  account.getAccountType(),
												  interestStr);


					customer.deleteAccount(account);		



				}

			}



		}




		return sCloseAccount;


	}



	/**
	 @param String pNo

	 @return ArrayList value
	 */

	public ArrayList<String> deleteCustomer(String pNo)
	{

		ArrayList<String> customerList = new ArrayList<String>();

		for (int i = 0; i < customers.size(); i++)
		{

			if (customers.get(i).getpNo().equals(pNo))
			{


				customerList.add(String.format("%s %s %s",
											   customers.get(i).getpNo(),
											   customers.get(i).getFirstName(),
											   customers.get(i).getSurName()));



				for (int a = 0; a < customers.get(i).getAccountLength(); a++)
				{

					Account account = customers.get(i).getAccountIndex(a);

					if (account != null)
					{



						BigDecimal balance = account.getBalance();

						String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(balance.intValue());


						BigDecimal interest = account.getRateOfInterest();

						float interestAmount = (balance.floatValue() * interest.floatValue() / 100);

						String interestStr = NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(interestAmount);


						customerList.add(String.format("%s %s %s %s",
													   account.getAccountNumber(),
													   balanceStr,
													   account.getAccountType(),
													   interestStr));


					}

				}		   




				customers.remove(i);


			} 

		}

		if (customerList.size() < 1)
		{

			customerList = null;
		}


		return customerList;



	}





	/**
	 @param String pNo

	 @return int value
	 */

	public int createCreditAccount(String pNo)
	{

		int nAccount = -1;

		for (Customer customer : customers)
		{

			if (customer.getpNo().equals(pNo))
			{

				currentAccountNumber++;

				CreditAccount account = new CreditAccount();

				//CreditAccount account = new CreditAccount(currentAccountNumber, 0, new BigDecimal("1.2"));


				account.setAccountType("Kreditkonto");

				account.setRateOfInterest(new BigDecimal("0.5"));

				account.setAccountNumber(currentAccountNumber);

				customer.getAccount().add(account);

				nAccount = currentAccountNumber;

			}

		}


		return nAccount;


	}




	/**
	 @param String pNo, int accountId

	 @return ArrayList value
	 */

	public ArrayList<String> getTransactions(String pNo, int accountId)
	{

		ArrayList<String> arrayTransactions = null;



		for (Customer customer : customers)
		{

			if (customer.getpNo().equals(pNo))
			{

				Account account = customer.getAccount(accountId);

				if (account != null)
				{


					arrayTransactions = new ArrayList<String>();

					ArrayList<String> transactions = account.getTransactions();

					for (int t = 0; t < transactions.size(); t++)
					{
						String transaction = transactions.get(t);


						arrayTransactions.add(transaction);

					}



				} 




			}

		}



		return arrayTransactions;
	}









}
