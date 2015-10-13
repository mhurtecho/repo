package com.rdbusiness.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class DBConnection {

	private static MongoClient client;

	private static MongoDatabase database;

	
	private static MongoDatabase getInstance() {
		if (database == null) {
			client = new MongoClient("localhost");
			database = client.getDatabase("dbzero");
		}

		return database;
	}
	
	public static MongoCollection<Document> getCollection(String name){
		return getInstance().getCollection(name);
	}
	
}
