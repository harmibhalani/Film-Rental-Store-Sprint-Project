package com.cg.service;
 
import java.util.List;
 
import com.cg.model.Store;
 
public interface StoreService {
 
	
	List<Store> getStoreByCityName(String city) throws Exception;
	Store updateStorePhone(Short storeId, Store store);
}