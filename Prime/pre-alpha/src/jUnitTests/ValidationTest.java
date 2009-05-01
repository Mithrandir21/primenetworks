package jUnitTests;

import org.junit.Test;

import junit.framework.TestCase;
import logistical.checkLogic;

public class ValidationTest extends TestCase 
{
	/**
	 * Javadoc-TODO - Description
	 * 
	 */
	@Test
	public void testValidation()
	{
		String[] strings = new String[15];
		
		strings[0] = "a";
		strings[1] = "abc123";
		strings[2] = "abc_123";
		strings[3] = "ABC_123";
		strings[4] = "רזו";
		strings[5] = "״ֵֶ123";
		strings[6] = "רזו_123";
		strings[7] = "רזו_!123";
		strings[8] = "zxc/";
		strings[9] = "abc _123";
		strings[10] = "abc _ 123";
		strings[11] = "!234";
		strings[12] = "#442asc";
		strings[13] = " ttt";
		strings[14] = "testing-test";
		
		
		
		for( int i = 0; i < strings.length; i++ )
		{
			System.out.println();
			System.out.println("String to be tested: " + strings[i]);
			System.out.println(checkLogic.validateWidgetName(strings[i]));
		}
	}
	
}
