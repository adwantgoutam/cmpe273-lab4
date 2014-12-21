package edu.sjsu.cmpe.cache.client;

public class CRDTClient {
	
	CacheServiceInterface cache1 = new DistributedCacheService(
            "http://localhost:3000");
    CacheServiceInterface cache2 = new DistributedCacheService(
            "http://localhost:3001");
    CacheServiceInterface cache3 = new DistributedCacheService(
            "http://localhost:3002");
    
    public void putCacheClient(long key,String value)
    {
    	if(cache1.put(key,value)+cache2.put(key,value)+cache3.put(key,value)>=2)
    	{
    		System.out.println("Put Successful**");
    	}
    	else
    	{
    		System.out.println("Performing Rollback**");
    		cache1.delete(key);
    		cache2.delete(key);
    		cache3.delete(key);
    	}
    }
    
    public void getCacheClient(long key)
    {
    	String v1=cache1.get(key);
    	String v2=cache2.get(key);
    	String v3=cache3.get(key);
    	System.out.println(v1);
    	if(v1.equals(v2) && v2.equals(v3) && v3.equals(v1))
    	{
    		System.out.println("Consistent State - Successful **");
    	}
    	else
    	{
    		if(v1.equals(v2))
    		{
    			cache3.put(key,v1);
    			System.out.println("Consistent State of cache3 changed and set equal to 1 & 2 servers **");
    		}
    		else if(v2.equals(v3))
    		{
    			cache1.put(key,v2);
    			System.out.println("Consistent State of cache1 changed and set equal to 2 & 3 servers **");
    		}
    		else if(v3.equals(v1))
    		{
    			cache2.put(key,v1);
    			System.out.println("Consistent State of cache2 changed and set equal to 1 & 3 servers **");
    		}
    	}
    }

}
