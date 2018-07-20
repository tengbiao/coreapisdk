package ts.coreapi.sdk;

import ts.coreapi.sdk.model.*;

public class testApi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*CoreAPIClient client = new CoreAPIClient("TSTool", "AKID4TtwNUFJg8oU9m4XDiJAaFofQ0KJEpCQ", SiteEnum.TS111,
				"http://119.81.201.156:8066","http://cs9.tp33.net:8066/");*/

		/*ApiResultListData<OnlineCustomServiceResModel> res1 = client.getOnlineCustomService();
		for (OnlineCustomServiceResModel resModel : res1.getData()) {
			System.out.println(resModel.getUrl());
		}*/

		/*ApiResultData<MemberInfoResModel> res2 = client.getMemberInfo("tb1234");
		System.out.println("是否有存款=>" + res2.getData().getIsdeposit());
		*/
	/*	
		ApiResultListData<BackupLinkResModel> res3 = client.getBackupLink(BackupLinkType.Member);
		for (BackupLinkResModel backupLinkResModel : res3.getData()) {
			System.out.println(backupLinkResModel.getImgUrl());
		}*/
		
	/*	ApiResultListData<ToolsRepairResModel> res4 = client.getToolsRepair(RepairType.BackupUrl);
		for (ToolsRepairResModel toolsRepairResModel : res4.getData()) {
			System.out.println(toolsRepairResModel.getLink());
		}*/
		
	/*	ApiResultData<String> res5 = client.getQRImage(QRImageType.CasinoApp);
		System.out.println("getQRImage=>" + res5.getData());*/
		
		/*QueryRepairReqModel repairReqModel = new QueryRepairReqModel();
		repairReqModel.setRepairNo("BFEBFBFF000306A9|4683210D|ACFDCEE531CA");
		ApiResultListData<QueryRepairResModel> res6 = client.queryRepair(repairReqModel);
		for (QueryRepairResModel queryRepairResModel : res6.getData()) {
			System.out.println(queryRepairResModel.getF_content());
		}*/
		
		/*
		AddRepairReqModel addRepairReqModel = new AddRepairReqModel();
		addRepairReqModel.setAccounts("TB1234");
		addRepairReqModel.setContent("test12344");
		addRepairReqModel.setPhone("15121158624");
		addRepairReqModel.setRepairNo("1314520");
		addRepairReqModel.setType(2);
		addRepairReqModel.setWebSite("http://ju11.net");
		ApiResult res7 = client.addRepair(addRepairReqModel);
		System.out.println("addRepair=>" + res7.getMessage());*/
		
	/*	ApiResultData<CheckVersionResModel> resultData = client.CheckVersion();
		System.out.println("window:"+resultData.getData().getWindows());
		System.out.println("ios:"+resultData.getData().getIos());
		System.out.println("andriod:"+resultData.getData().getAndriod());*/
		
		
//测试用例
		CoreAPIClient client = new CoreAPIClient("TSTool", "AKID4TtwNUFJg8oU9m4XDiJAaFofQ0KJEpCQ", SiteEnum.TS111,
                "http://aw.bvf11.com/");//"http://cf.bvf11.com/",
		
		ApiResultListData<OnlineCustomServiceResModel> resultListData = client.getOnlineCustomService();
		
		System.out.println(resultListData.getData().get(0).getUrl());
		
       /* QueryRepairReqModel repairReqModel = new QueryRepairReqModel();
        repairReqModel.setRepairNo("BFEBFBFF000306A9|4683210D|ACFDCEE531CA");
        ApiResultListData<QueryRepairResModel> res6 = client.queryRepair(repairReqModel);
        System.out.println(res6.getMessage());
        for (QueryRepairResModel queryRepairResModel : res6.getData()) {
            System.out.println(queryRepairResModel.getF_content());
        }*/
	}	
}
