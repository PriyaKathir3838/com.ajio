package com.ajio.rerun;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	
	int i=0,j=2;

	@Override
	public boolean retry(ITestResult result) {
		if(i<j) {
			i++;
		
		return true;
		
	}
		return false;

}
}