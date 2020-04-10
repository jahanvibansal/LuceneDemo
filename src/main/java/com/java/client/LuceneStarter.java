/*
 * package com.java.client;
 * 
 * import java.io.IOException; import java.util.Arrays;
 * 
 * import org.apache.lucene.analysis.standard.StandardAnalyzer; import
 * org.apache.lucene.document.Document; import org.apache.lucene.document.Field;
 * import org.apache.lucene.document.StoredField; import
 * org.apache.lucene.document.TextField; import
 * org.apache.lucene.index.DirectoryReader; import
 * org.apache.lucene.index.IndexOptions; import
 * org.apache.lucene.index.IndexReader; import
 * org.apache.lucene.index.IndexWriter; import
 * org.apache.lucene.index.IndexWriterConfig; import
 * org.apache.lucene.index.Term; import
 * org.apache.lucene.queryparser.classic.ParseException; import
 * org.apache.lucene.queryparser.classic.QueryParser; import
 * org.apache.lucene.search.IndexSearcher; import
 * org.apache.lucene.search.Query; import org.apache.lucene.search.TermQuery;
 * import org.apache.lucene.search.TopDocs; import
 * org.apache.lucene.store.Directory; import
 * org.apache.lucene.store.RAMDirectory; import org.apache.lucene.util.BytesRef;
 * import org.apache.lucene.util.NumericUtils;
 * 
 * public class LuceneStarter { public static void main(String[] args) throws
 * IOException, ParseException { Directory index= new RAMDirectory();
 * IndexWriter writer= new IndexWriter(index, new IndexWriterConfig());
 * addDocument(writer, "Payal Bansal", 43); addDocument(writer, "Ritu Sharma",
 * 36); addDocument(writer, "Kanika Bansal", 32); writer.close();
 * //searchByName(index); searchByRank(index); }
 * 
 * 
 * private static void searchByRank(Directory index) throws IOException,
 * ParseException { IndexReader reader= DirectoryReader.open(index);
 * IndexSearcher searcher=new IndexSearcher(reader); //queryparser: raw native
 * sql queries run on db, results written //querbuilder: .where and .and //
 * QueryParser qp = new QueryParser("rank", new StandardAnalyzer()); Query
 * idQuery =new TermQuery(new Term("rank", "32")); TopDocs
 * docs=searcher.search(idQuery, 1); Arrays.stream(docs.scoreDocs).map(x-> { try
 * { return searcher.doc(x.doc); } catch (IOException e) { e.printStackTrace();
 * return null; } }).forEach(System.out::println); }
 * 
 * 
 * private static void searchByName(Directory index) throws IOException,
 * ParseException { IndexReader reader= DirectoryReader.open(index);
 * IndexSearcher searcher=new IndexSearcher(reader); Query q=new
 * QueryParser("name", new StandardAnalyzer()).parse("bansal");//raw search
 * query TopDocs docs=searcher.search(q, 10);
 * Arrays.stream(docs.scoreDocs).map(x-> { try { return searcher.doc(x.doc); }
 * catch (IOException e) { e.printStackTrace(); return null; }
 * }).forEach(System.out::println); }
 * 
 * 
 * There are two methods for adding documents:
 * 
 * addDocument(Document)—Adds the document using the default analyzer, which you
 * specified when creating the IndexWriter, for tokenization.
 * addDocument(Document, Analyzer)—Adds the document using the provided analyzer
 * for tokenization.
 * 
 * private static void addDocument(IndexWriter writer,String name, int rank)
 * throws IOException { Document d1= new Document(); d1.add(new
 * TextField("name",name, Field.Store.YES));//analyzed by default d1.add(new
 * StoredField("rank",rank)); // d1.add(new IntPoint("rank", 5,
 * Field.Store.YES)); writer.addDocument(d1); } }
 */