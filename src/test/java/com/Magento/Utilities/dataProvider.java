package com.Magento.Utilities;

import org.testng.annotations.DataProvider;

public class dataProvider {
	
	@DataProvider(name="Data")
	public Object[][] data()
	{
		return new Object[][]
				{
			
			{"Lerato","Lemo","Motshabi","leraxvtshab@gmail.com","Password1","Password1"},
				};
	}

}
