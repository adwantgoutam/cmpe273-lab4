package edu.sjsu.cmpe.cache.client;

public class Client {
	
	
	public static void main(String[] args) throws Exception {
        System.out.println("Cache Client Start **");
        
        CRDTClient crdtClient=new CRDTClient();
        crdtClient.putCacheClient(1,"a");
        System.out.println("Stoping Server A **");
        Thread.sleep(30000);
        crdtClient.putCacheClient(1,"b");
        System.out.println("Starting Server A ***");
        Thread.sleep(30000);
        crdtClient.getCacheClient(1);
        System.out.println("Present Cache Client ***");
    }

}
