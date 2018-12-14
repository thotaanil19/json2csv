package com.json2csv;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.opencsv.CSVReader;

public class DeleteDataFromMongo {

	public static void main(String[] args) throws Exception {
		MongoClient mongoClient = null;
		CSVReader csvReader = null;
		try {
			MongoClientURI uri = new MongoClientURI(
					"mongodb+srv://system:tiger@cluster0-0r7hr.mongodb.net/test?retryWrites=true");

			mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase("test");

			MongoCollection<Document> collection = database
					.getCollection("test");

			System.out.println("connection created");

			System.out.println(collection.countDocuments());

			Reader reader = new InputStreamReader(new FileInputStream(
					"src/main/resources/sai.csv"));

			csvReader = new CSVReader(reader);

			String[] nextRecord;
			/*while ((nextRecord = csvReader.readNext()) != null) {
				int i = 0;
				Map<String, Object> document = new HashMap<>();
				document.put("name", nextRecord[i++]);
				document.put("version", nextRecord[i++]);
				document.put("alias", nextRecord[i++]);
				document.put("orgUnitId", nextRecord[i++]);
				collection.insertOne(new Document(document));
			}*/

			// Reading Records One by One in a String array
			while ((nextRecord = csvReader.readNext()) != null) {
				int i = 0;
				BasicDBObject document = new BasicDBObject();
				document.append("name", nextRecord[i++]);
				document.append("version", nextRecord[i++]);
				document.append("alias", nextRecord[i++]);
				document.append("orgUnitId", nextRecord[i++]);
				collection.deleteMany(document);
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
