package com.rdbusiness.main;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

	public static void main(String[] args) {

		try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
			MongoDatabase database = mongoClient.getDatabase("javadb");

			MongoCollection<Document> collection = database.getCollection("test");

			Document doc = new Document("name", "MongoDB4").append("type", "database").append("count", 1).append("info",
					new Document("x", 203).append("y", 102));

			collection.insertOne(doc);

			for (Document cur : collection.find()) {
			    System.out.println(cur.toJson());
			}
		}
	}
}
