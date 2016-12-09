//package com.computing.cloud.dao;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import org.springframework.stereotype.Repository;
//
//import com.computing.cloud.domain.StorageType;
//
//@Repository
//public class StorageTypeRepositoryFakeImpl implements StorageTypeRepository {
//	
//	List<StorageType> storageTypes = new ArrayList<StorageType>();
//	private StorageType hdd = new StorageType("HDD");
//	private StorageType ssd = new StorageType("SSD");
//	
//	public StorageTypeRepositoryFakeImpl() {
//		hdd.setId(1L);
//		storageTypes.add(hdd);
//		
//		ssd.setId(2L);
//		storageTypes.add(ssd);
//	}
//	
//	@Override
//	public StorageType findOne(Long id) {
//		for (StorageType storageType : storageTypes) {
//			if(storageType.getId().equals(id)) {
//				return storageType;
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public List<StorageType> findAll() {
//		return Collections.unmodifiableList(storageTypes);
//	}
//
//}