
public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hi Rahul");
		System.out.println("Hi Alok");
		System.out.println("Hi Rajesh");
		System.out.println("Hi Rakesh");
		
		//------------------
		System.out.println("Hi Rahul");
		System.out.println("Hi Alok");
		System.out.println("Hi Rajesh");
		System.out.println("Hi Rakesh");
		//------------------
		System.out.println("Hi Rahul");
		System.out.println("Hi Alok");
		System.out.println("Hi Rajesh");
		System.out.println("Hi Rakesh");
		
	}

}

======================================================================================
package MyRunner;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


	
	@CucumberOptions(
			glue ={"StepDefination"},
//	format = {"json:target/cucumber.json","html:target/site/cucumber-pretty"},
			plugin = {"pretty", "html:target/results/search"},
			monochrome=true
	)
	
	public class TestNGTestRunner extends AbstractTestNGCucumberTests{
		
//		@Parameters({ "tc1","tc2" })
//		@BeforeClass
//		public void bc(@Optional("optional value")String tc1,String tc2)
//		{
//			System.out.println(tc1);
//			System.out.println(tc2);
//			System.out.println(tc2);
//			
//		}
		
		@Parameters({"tc1"})
		@BeforeTest(alwaysRun = true)
		public void setUpTest(@Optional("src/main/java/cucumberFeatureFiles/Testcase.feature") String featurePath) throws Exception {   
		    Class<?> testClass = this.getClass();
		    changeCucumberAnnotation(testClass, "features", new String [] {featurePath});       
//		    WaltersTestngCucumberRunner testNGCucumberRunner = new WaltersTestngCucumberRunner(testClass);        
		}

		private static void changeCucumberAnnotation(Class<?> clazz, String key, Object newValue) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{  
		    Annotation options = clazz.getAnnotation(CucumberOptions.class);                   //get the CucumberOptions annotation  
		    InvocationHandler proxyHandler = Proxy.getInvocationHandler(options);              //setup handler so we can update Annotation using reflection. Basically creates a proxy for the Cucumber Options class
		    Field f = proxyHandler.getClass().getDeclaredField("memberValues");                //the annotaton key/values are stored in the memberValues field
		    f.setAccessible(true);                                                             //suppress any access issues when looking at f
		    Map<String, Object> memberValues = (Map<String, Object>) f.get(proxyHandler);      //get the key-value map for the proxy
		    memberValues.remove(key);                                                          //renove the key entry...don't worry, we'll add it back
		    memberValues.put(key,newValue);                                                    //add the new key-value pair. The annotation is now updated.
		}
}
	
	

	
//	@CucumberOptions(features = {"src/test/resources/FeatueFiles/Soumya.feature"} ,
//			glue ={"StepDefination"},
////	format = {"json:target/cucumber.json","html:target/site/cucumber-pretty"},
//			plugin = {"pretty", "html:target/results/search"}
//	)

======================================================================================
	
	<?xml version="1.0" encoding="UTF-8"?>
<suite name="SimpleTest">
<parameter name="tc1" value="src/test/resources/FeatueFiles/Soumya1.feature"/>
<parameter name="tc2" value="GetClassOfServiceResource"/>
<test name="testing">
<classes>
<class name="MyRunner.TestNGTestRunner">
</class>
</classes>
</test>
</suite>
