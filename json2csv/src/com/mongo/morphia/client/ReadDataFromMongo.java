package com.mongo.morphia.client;

import java.util.Arrays;
import java.util.List;

import org.mongodb.morphia.Morphia;

import com.json2csv.MongoDBUtil;
import com.mongo.morphia.EmployeeMongoRepository;
import com.mongo.morphia.model.EmployeeEntity;
import com.mongodb.MongoClient;

public class ReadDataFromMongo {
	
	public static void main(String[] args) {
		
		
		MongoClient	mongoClient = MongoDBUtil.getMongoClient();
		//MongoCollection<Document> collection = MongoDBUtil.getMongoCollection(mongoClient, "mongo", "configuration");
		System.out.println("connection created");
		
		EmployeeMongoRepository employeeMongoRepository = new EmployeeMongoRepository(mongoClient, new Morphia(), "mongo");
		
		EmployeeEntity employee = new EmployeeEntity();
		employee.setName("Anil");
		/*Phone phone = new Phone();
		phone.setPhone("1234");*/
		employee.setPhones(Arrays.asList("hi"));
		
		employeeMongoRepository.save(employee);
		
		
		List<EmployeeEntity> list =employeeMongoRepository.createQuery().asList();
		
		System.out.println(list);
		
	}

}
