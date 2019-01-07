package com.json2csv;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {

	public static MongoClient getMongoClient() {
		MongoClient mongoClient = null;
		MongoCollection<Document> collection = null;
		try {
			MongoClientURI uri = new MongoClientURI(
					// "mongodb+srv://system:tiger@cluster0-0r7hr.mongodb.net/test?retryWrites=true"
					// "mongodb+srv://scott:tiger@cluster0-a1qsn.mongodb.net/mongo?retryWrites=true"
					"mongodb+srv://admin:tiger@cluster0-a1qsn.mongodb.net/mongo?retryWrites=true"
					);
			mongoClient = new MongoClient(uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mongoClient;
	}

	public static MongoCollection<Document> getMongoCollection(
			MongoClient mongoClient, String dataBaseName, String collectionName) {
		MongoDatabase database = mongoClient.getDatabase(dataBaseName);
		MongoCollection<Document> collection = database
				.getCollection(collectionName);
		return collection;
	}

}
