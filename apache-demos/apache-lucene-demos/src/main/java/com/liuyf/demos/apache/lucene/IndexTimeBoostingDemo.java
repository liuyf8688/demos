package com.liuyf.demos.apache.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class IndexTimeBoostingDemo {

	public static void main(String[] args) throws IOException, ParseException {
		
		Directory directory = new RAMDirectory();
		System.out.println("===== Index Time Boosting =====");
		IndexWriter writer = new IndexWriter(directory, new IndexWriterConfig(new StandardAnalyzer()));
		try {
			
			String[][] vehicles = {
					{ "Lada Niva", "Brown", "2000000", "Russia", "SUV" },
					{ "Tata Aria", "Red", "1600000", "India", "SUV" },
					{ "Nissan Terrano", "Blue", "2000000", "Japan", "SUV" },
					{ "Mahindra XUV500", "Black", "1600000", "India", "SUV" },
					{ "Ford Ecosport", "White", "1000000", "USA", "SUV" },
					{ "Mahindra Thar", "White", "1200000", "India", "SUV" }
				};
			
			for (String[] vehicle : vehicles) {
				Document docToAdd = new Document();
				docToAdd.add(new StringField("itemName", vehicle[0], Store.YES));
				docToAdd.add(new StringField("itemColour", vehicle[1], Store.YES));
				docToAdd.add(new StringField("itemPrice", vehicle[2], Store.YES));
				String originOfItem = vehicle[3];
				docToAdd.add(new StringField("originOfItem", originOfItem, Store.YES));
				TextField itemTypeField = new TextField("itemType", vehicle[4], Store.YES);
				docToAdd.add(itemTypeField);
				// Boost items made in India
				if ("India".equalsIgnoreCase(originOfItem)) {
					itemTypeField.setBoost(2.0f);
				}
				
				writer.addDocument(docToAdd);
			}
		
		} finally {
			writer.close();
		}
		
		IndexReader reader = DirectoryReader.open(directory);
		try {
			
			IndexSearcher searcher = new IndexSearcher(reader);
			Query queryToSearch = new QueryParser("itemType", new StandardAnalyzer()).parse("suv");
			System.out.println(queryToSearch);
			TopScoreDocCollector collector = TopScoreDocCollector.create(10);
			searcher.search(queryToSearch, collector);
			ScoreDoc[] hitsTop = collector.topDocs().scoreDocs;
			
			System.out.println("Search produced " + hitsTop.length + " hits.");
			System.out.println();
			System.out.println("/////////////////");
			System.out.println();
			for (int i = 0;i < hitsTop.length; ++i) {
				int docId = hitsTop[i].doc;
				Document docAtHand = searcher.doc(docId);
				System.out.println(docAtHand.get("itemName") + "\t" +
						docAtHand.get("originOfItem")
						+ "\t" + docAtHand.get("itemColour") + "\t" +
						docAtHand.get("itemPrice")
						+ "\t" + docAtHand.get("itemType"));
				Explanation explanation = searcher.explain(queryToSearch, docId);
				System.out.println();
				System.out.println("##############");
				System.out.println(explanation.toString());
				System.out.println("##############");
				System.out.println();
				System.out.println();
			}
		
		} finally {
			directory.close();
			reader.close();
		}
	}
}
