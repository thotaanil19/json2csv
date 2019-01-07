package com.json2csv;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.opencsv.CSVReader;

public class MongoIndexUsageByQueryPlanner {

	public static void main(String[] args) throws Exception {
		MongoClient mongoClient = null;
		CSVReader csvReader = null;
		try {
			StringBuilder result = new StringBuilder();
			mongoClient = MongoDBUtil.getMongoClient();
			DBCollection collection = MongoDBUtil.getDBCollection(mongoClient, "mongo", "configuration");
			System.out.println("connection created");

			List<BasicDBObject> queries = buildAllPossibleQueries();

			for (BasicDBObject query : queries) {
			String indexName = "NULL";
				try (DBCursor cursor = collection.find(query)) {
					DBObject obj = cursor.explain();
					DBObject executionStats = (DBObject)obj.get("executionStats");
					if (null != executionStats) {
						DBObject executionStages = (DBObject)executionStats.get("executionStages");
						if (null != executionStages) {
							DBObject inputStage = (DBObject)executionStages.get("inputStage");
							if (null != inputStage) {
								indexName = (String)inputStage.get("indexName");
							}
						}
					}
					result.append("Query: " + query);
					result.append("\n");
					result.append("Index: " + indexName);
					result.append("\n");
				}
				result.append("\n");
				result.append("\n");
			}
			
			System.out.println(result.toString());
		} finally {
			if (null != csvReader) {
				csvReader.close();
			}
			if (null != mongoClient) {
				mongoClient.close();
			}
		}
	}
	
	public static List<BasicDBObject> buildAllPossibleQueries () {
		List<BasicDBObject> queries = new ArrayList<>();
		
		BasicDBObject query1 = new BasicDBObject();
		query1.append("name", "AuthenticationConfig");
		query1.append("alias", "VTTest");
		query1.append("orgUnitId", "5b991781fd73f9329cde2f42");
		query1.append("version", "3");
		queries.add(query1);
		
		BasicDBObject query2 = new BasicDBObject();
		query2.append("version", "3");
		query2.append("alias", "VTTest");
		query2.append("orgUnitId", "5b991781fd73f9329cde2f42");
		query2.append("name", "AuthenticationConfig");
		queries.add(query2);
		
		BasicDBObject query3 = new BasicDBObject();
		query3.append("orgUnitId", "5b991781fd73f9329cde2f42");
		query3.append("name", "AuthenticationConfig");
		query3.append("version", "3");
		query3.append("alias", "VTTest");
		queries.add(query3);
		
		
		BasicDBObject query4 = new BasicDBObject();
		query4.append("orgUnitId", "5b991781fd73f9329cde2f42");
		queries.add(query4);
		
		BasicDBObject query5 = new BasicDBObject();
		query5.append("orgUnitId", "5b991781fd73f9329cde2f42");
		query5.append("name", "AuthenticationConfig");
		queries.add(query5);
		
		BasicDBObject query6 = new BasicDBObject();
		query6.append("orgUnitId", "5b991781fd73f9329cde2f42");
		query6.append("name", "AuthenticationConfig");
		query6.append("alias", "VTTest");
		queries.add(query6);
		
		return queries;
	}
	
	

}
