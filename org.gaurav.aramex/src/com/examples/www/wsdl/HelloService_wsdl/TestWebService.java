package com.examples.www.wsdl.HelloService_wsdl;

import java.net.URL;

import org.compiere.process.SvrProcess;

public class TestWebService extends SvrProcess{

	@Override
	protected void prepare() 
	{
		
		
	}

	@Override
	protected String doIt() throws Exception 
	{
		Hello_BindingStub main = new Hello_BindingStub(new URL("http://www.examples.com/SayHello"), null);
		main.sayHello("Gaurav");
		return null;
	}
}
