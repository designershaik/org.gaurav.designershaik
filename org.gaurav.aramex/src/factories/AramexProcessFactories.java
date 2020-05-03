package factories;

import net.aramex.ws.ShippingAPI.v1.AramexClient;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

import com.examples.www.wsdl.HelloService_wsdl.TestWebService;

public class AramexProcessFactories implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) 
	{
		if(className.equals(TestWebService.class.getName()))
			return new TestWebService();
		
		if(className.equals(AramexClient.class.getName()))
			return new AramexClient();
		
		return null;
	}

	
}
