package com.exadatum.product;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.List;

/**
 * Created by jksingh on 27/2/17.
 */
public class MongoCrawler {

    public static void main(String args[]){

        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        System.out.println(mongoClient.getCredentialsList());
        System.out.println(mongoClient.getMongoClientOptions());
        for(Object db : mongoClient.listDatabaseNames()) {
            System.out.println("DATABASE:  "+db);
            MongoDatabase mongoDatabase = mongoClient.getDatabase(db.toString());
            for(String collection : mongoDatabase.listCollectionNames()){
                System.out.println( "COLLECTION:"+collection);
                MongoCollection mongoCollection = mongoDatabase.getCollection(collection);
                System.out.println(mongoCollection.getNamespace().getFullName());
                for(Object doc : mongoCollection.find()){
                    System.out.println("Document: "+doc);
                }
            }
        }
    }
}
