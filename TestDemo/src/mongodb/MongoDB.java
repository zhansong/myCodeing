package mongodb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.UpdateOptions;

public class MongoDB {
	public static MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 2222), new ServerAddress("localhost", 3333), new ServerAddress("localhost", 4444)));

	public static void main(String[] args) {
		MongoDatabase db = mongoClient.getDatabase("test");
		MongoIterable<String> list = db.listCollectionNames();
		for(String temp : list) {
			System.out.println(temp);
		}
		MongoCollection<Document> col = db.getCollection("ainimal");
		Document doc = new Document();
//		doc.append("name", "二哈").append("age", 3).append("type", "狗");
//		col.insertOne(doc);
		col.deleteMany(new BasicDBObject("name","二哈"));
		FindIterable<Document> find = col.find();
		for(Document d : find) {
			System.out.println(d);
			System.err.println(d.get("name")+"是"+d.getString("type"));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "running");
		map.put("age", 1);
		map.put("type", "小老鼠");
		doc = new Document(map);
		col.updateOne(new BasicDBObject("name","running"), new BasicDBObject("$set",new BasicDBObject("type","小老鼠")), new UpdateOptions().upsert(false));
	}
	
	public void insert(MongoCollection<Document> col) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "running");
		map.put("age", 1);
		map.put("type", "小老鼠");
		Document doc = new Document(map);
		col.insertOne(doc);
	}
	public void delete(MongoCollection<Document> col) {
		col.deleteOne(new BasicDBObject("name","小黑"));
	}
	public void update(MongoCollection<Document> col) {
		col.updateOne(new BasicDBObject("name","running"), new BasicDBObject("$set",new BasicDBObject("type","小老鼠")), new UpdateOptions());
	}
}
