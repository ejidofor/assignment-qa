package com;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;



public class WebDriverManager {

 	private static WebDriver driver = null;
    private static String browser = null;
    private static URL remoteAddress = null;

     // Default constructor, no need to extend this just use as a static
    public WebDriverManager()   
    {
    	
    }

   
    public static WebDriver startDriver(String browser, String hubIP)
    {
        WebDriverManager.browser = browser;
        
        int timeout = 50;
        /*
        * Determine what browser we are using and start the appropriate driver instance
        */
        if ( browser.equalsIgnoreCase("Internet Explorer") )
        {
            System.out.println("browser : "+ browser);
            
            DesiredCapabilities ieCapability = DesiredCapabilities.internetExplorer();
            
            ieCapability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            
            ieCapability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true); 
            
    		ieCapability.setBrowserName("Internet Explorer");
    		
    		ieCapability.setVersion("9");
    		
    		try {
					driver = new RemoteWebDriver(new URL("http://"+hubIP+"/wd/hub"), ieCapability);
				} 
	            catch (MalformedURLException e) 
	            	{
						e.printStackTrace();
	            	}

            driver.manage().deleteAllCookies();
            
            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        }
       
            else if ( browser.equalsIgnoreCase("Chromium-browser") )
            {
            System.out.println("browser :"+ browser);

            try
            {
            	String chromeDriverPath = null;
                chromeDriverPath = "/home/ejikeme/Tools/chromedriver";
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            }
            	catch (Exception ex)
            		{
            			System.out.println("\nException in getting and setting the webdriver chrome driver: "+ ex.getMessage() + ex.getClass() );
            			ex.printStackTrace();
            		}
            
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            DesiredCapabilities dc = DesiredCapabilities.chrome();
            dc.setCapability(ChromeOptions.CAPABILITY, options);
           
            try {
            remoteAddress = new URL("http://"+hubIP+"/wd/hub");
            }
            catch (MalformedURLException e) {
				e.printStackTrace();
			}
            driver = new RemoteWebDriver(remoteAddress, dc);
            driver.manage().deleteAllCookies();
        	}
        
        else
        {
            System.out.println("browser : Firefox (Default)\n");
                    
			DesiredCapabilities capability = DesiredCapabilities.firefox();
            
            capability.setCapability("marionette", true);
            
            capability.setJavascriptEnabled(true);
            
            capability.setBrowserName("firefox");
            	            
            capability.setVersion("52");
            
            capability.setPlatform(Platform.LINUX);
            
   
            try {
            	remoteAddress = new URL("http://"+hubIP+"/wd/hub");
				} 
            catch (MalformedURLException e) {
				e.printStackTrace();
			}
            
            driver = new RemoteWebDriver(remoteAddress, capability);
            
  	        }

        // return a reference to the static web driver instance started
        return driver;
    }

    public static String getBrowser()
    {
        return browser;
    } 
   
}
