package com.rdbusiness.rest.dao;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.rdbusiness.mongodb.DBConnection;
import com.rdbusiness.rest.bean.Product;

public class ProductDao {

	private MongoCollection<Document> collection;

	public ProductDao() {
		collection = DBConnection.getCollection("product");
	}

	public String getList() {
		StringBuilder stb = new StringBuilder("[");

		for (Document cur : collection.find()) {
			stb.append(cur.toJson());
		}

		return stb.append("]").toString();
	}

	public String get(String id) {
		Document doc = collection.find(eq("_id", new ObjectId(id))).first();

		return doc.toJson();
	}

	public void update(String id, Product product) {
		Document doc = new Document("id", product.getId()).append("name", product.getName()).append("category",
				product.getCategory());

		collection.updateOne(eq("_id", new ObjectId(id)), new Document("$set", doc));
	}

	public void delete(String id) {
		collection.deleteOne(eq("_id", new ObjectId(id)));
	}

	public String create(Product product) {
		Document doc = new Document("id", product.getId()).append("name", product.getName()).append("category",
				product.getCategory());

		collection.insertOne(doc);

		return doc.get("_id").toString();
	}

}
