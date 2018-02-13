package com.iliap.taxesManager.model;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import com.mongodb.MongoWriteException;

@Service
public class BatchSaver<T> {	
	private int duplicates = 0;
	public void batchSave(MongoRepository repo,List<? extends CrudAbstract> batchList){
		System.out.println("Batch saving to the DB");		
		batchList.forEach(x->{			
				try {
					repo.save(x);
				} catch (MongoWriteException e) {
					duplicates++;
					//e.printStackTrace();
				} catch (DuplicateKeyException e) {
					duplicates++;
					//e.printStackTrace();
				}					
		});
		System.out.println("Batch portion saved in DB. "+duplicates+" duplicate rows ignored");	
	}	
	
}
