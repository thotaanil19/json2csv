package com.json2csv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.opencsv.CSVReader;

public class MongoIndexUsage {

	public static void main(String[] args) throws Exception {
		MongoClient mongoClient = null;
		CSVReader csvReader = null;
		try {
			mongoClient = MongoDBUtil.getMongoClient();
			MongoCollection<Document> collection = MongoDBUtil.getMongoCollection(mongoClient, "mongo", "configuration");
			System.out.println("connection created");
			
			BasicDBObject basicDBObject = new BasicDBObject();
			basicDBObject.append("$indexStats", new HashMap<>());
			
			List<BasicDBObject> list = new ArrayList<>();
			list.add(basicDBObject);

			AggregateIterable<Document> r = collection.aggregate(list);
			
			MongoCursor<Document> iterator = r.iterator();
			
			while (iterator.hasNext()) {
				Document doc = iterator.next();	
				System.out.print("Index name: " + doc.get("name"));
				Document accesses = (Document)doc.get("accesses");
				System.out.print(", Number of Operations done: " + accesses.get("ops"));
				System.out.println();
			}
		} finally {
			if (null != csvReader) {
				csvReader.close();
			}
			if (null != mongoClient) {
				mongoClient.close();
			}
		}
	}

}
