package jUnitTests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() 
	{
		TestSuite suite = new TestSuite("Test for jUnitTests");
		//$JUnit-BEGIN$
		
		// MANAGMENT PACKAGE TESTS
		/////////////////////////////////////////////
		suite.addTestSuite(ArrayManagmentTest.class);
		
		suite.addTestSuite(ComponentsManagmentTest.class);
		
		suite.addTestSuite(ConnectionManagmentTest.class);
		
		//suite.addTestSuite(ExternalConnectionCheckTest.class);
		
		//suite.addTestSuite(InternalComponentsCheckTest.class);
		/////////////////////////////////////////////
		
		
		// LOGISTICAL PACKAGE TESTS
		/////////////////////////////////////////////
		
		suite.addTestSuite(cleanupTest.class);
		/*
		suite.addTestSuite(ContainerLogisticTest.class);
		
		suite.addTestSuite(RackFunctionsTest.class);
		*/
		/////////////////////////////////////////////
		
		//$JUnit-END$
		return suite;
	}

}
