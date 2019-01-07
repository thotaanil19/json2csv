package com.json2csv;

import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Utility class for Mongo DB.
 * 
 * @author Anil
 * 
 */
public class MongoDBUtil {

	/**
	 * Creates Mongo DB Client
	 * 
	 * @return MongoClient
	 */
	public static MongoClient getMongoClient() {
		MongoClient mongoClient = null;
		try {
			MongoClientURI uri = new MongoClientURI(
					// "mongodb+srv://system:tiger@cluster0-0r7hr.mongodb.net/test?retryWrites=true"
					// "mongodb+srv://scott:tiger@cluster0-a1qsn.mongodb.net/mongo?retryWrites=true"
					"mongodb+srv://admin:tiger@cluster0-a1qsn.mongodb.net/mongo?retryWrites=true");
			mongoClient = new MongoClient(uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mongoClient;
	}

	/**
	 * Gets connection to mongo DB based on client, database name, and
	 * collection name
	 * 
	 * @param mongoClient
	 * @param dataBaseName
	 * @param collectionName
	 * @return MongoCollection<Document>
	 */
	public static MongoCollection<Document> getMongoCollection(
			MongoClient mongoClient, String dataBaseName, String collectionName) {
		MongoDatabase database = mongoClient.getDatabase(dataBaseName);
		MongoCollection<Document> collection = database
				.getCollection(collectionName);
		return collection;
	}
	
	/**
	 * Gets DB connection to mongo DB based on client, database name, and
	 * collection name
	 * 
	 * @param mongoClient
	 * @param dataBaseName
	 * @param collectionName
	 * @return DBCollection
	 */
	@SuppressWarnings("deprecation")
	public static DBCollection getDBCollection(
			MongoClient mongoClient, String dataBaseName, String collectionName) {
		DB db = mongoClient.getDB(dataBaseName);
		DBCollection collection = db.getCollection(collectionName); 
		return collection;
	}

}
