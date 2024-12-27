package com.example.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.example.entity.Sequence;
import com.example.exception.JobPortalException;

@Component
public class Utilities {

	private static MongoOperations mongoOperations;
	
	@Autowired
	public void setMongoOperations(MongoOperations mongoOperations) {
		Utilities.mongoOperations = mongoOperations;
	}
	
	public static Long getNextSequence(String key) throws JobPortalException {
		var query = new Query(Criteria.where("_id").is(key));
		var update = new Update();
		update.inc("seq", 1);
		var options = new FindAndModifyOptions();
		options.returnNew(true);
		var seq = mongoOperations.findAndModify(query, update, options, Sequence.class);
		if(seq == null) {
			throw new JobPortalException("Unable to get sequence id for key : " + key);
		}
		return seq.getSeq();
	}
	
}
