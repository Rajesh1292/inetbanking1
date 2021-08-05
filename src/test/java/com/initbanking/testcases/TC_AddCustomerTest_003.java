package com.initbanking.testcases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.initbanking.pageobject.AddCustomerPage;
import com.initbanking.pageobject.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass
{

	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("User name is provided");
		lp.setPassword(password);
		logger.info("Passsword is provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);
		
		AddCustomerPage addcust=new AddCustomerPage(driver);
		
		addcust.clickAddNewCustomer();
		
		logger.info("providing customer details....");
		
		
		addcust.custName("Rajesh");
		addcust.custgender("male");
		addcust.custdob("12","08","1992");
		Thread.sleep(5000);
		addcust.custaddress("INDIA");
		addcust.custcity("CHE");
		addcust.custstate("TN");
		addcust.custpinno("600056");
		addcust.custtelephoneno("9008902211");
		
		String email=randomestring()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		Thread.sleep(2000);
		addcust.custsubmit();
		
		Thread.sleep(3000);
		logger.info("validation started....");
		Thread.sleep(2000);
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("test case passed....");
			
		}
		else
		{
			logger.info("test case failed....");
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
			
	}

	
}