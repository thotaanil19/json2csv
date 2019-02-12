package com.mongo.morphia;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import com.mongo.morphia.model.EmployeeEntity;
import com.mongodb.MongoClient;

public class EmployeeMongoRepository extends BasicDAO<EmployeeEntity, ObjectId> {
   
	public EmployeeMongoRepository(MongoClient mongo, Morphia morphia, String dbName) {
        super(mongo,morphia,dbName);
    }

   /* public Iterator<EmployeeEntity> findByQuoteNumber(String quoteNumber) {
        Pattern regExp = Pattern.compile(quoteNumber + ".*", Pattern.CASE_INSENSITIVE);
        return ds.find(entityClazz).filter("quoteNumber", regExp).iterator();   
    }*/
}
