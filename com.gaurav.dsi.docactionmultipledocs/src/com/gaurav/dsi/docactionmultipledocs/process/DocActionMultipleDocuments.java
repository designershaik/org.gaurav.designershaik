package com.gaurav.dsi.docactionmultipledocs.process;

import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MAssetAddition;
import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;

public class DocActionMultipleDocuments extends SvrProcess{

	@Override
	protected void prepare() {
	
		
	}

	@Override
	protected String doIt()  {
		
		try {
			List<Integer> records = new ArrayList<>();
			if(getRecord_IDs()!=null)
				records = getRecord_IDs();
			else
				records.add(getRecord_ID());
			
			for(int record_id : records)
			{
				MAssetAddition asset = new MAssetAddition(getCtx(),record_id,get_TrxName());
				if(asset.isActive())
				{
					System.out.println("Asset Addition: "+asset.getDocumentNo());
					statusUpdate("Asset Addition: "+asset.getDocumentNo());
					asset.processIt(DocAction.ACTION_Complete);
					asset.saveEx();
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Issue with the Asset Addtion.");
		}
		return null;
	}

}
