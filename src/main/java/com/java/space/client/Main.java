package com.java.space.client;

import java.io.IOException;
import java.time.LocalDate;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import com.java.space.dto.Location;
import com.java.space.dto.Pricing;
import com.java.space.dto.Property;
import com.java.space.dto.Property.Category;
import com.java.space.dto.Property.Purpose;
import com.java.space.dto.Property.Timings;
import com.java.space.dto.Property.Type;
import com.java.space.dto.PropertyDetails;
import com.java.space.dto.PropertyDetails.Age;
import com.java.space.dto.PropertyDetails.AreaType;
import com.java.space.dto.PropertyDetails.AreaUnit;
import com.java.space.dto.PropertyDetails.Availability;
import com.java.space.service.PropertySearchService;

public class Main {
	public static void main(String[] args) throws IOException, ParseException {
	Directory index= new RAMDirectory();
	Analyzer analyzer= new StandardAnalyzer();
	IndexWriterConfig config=new IndexWriterConfig(analyzer);
	IndexWriter writer= new IndexWriter(index, config);
	Property p1= Property.builder().availableFrom(LocalDate.now()).title("Office space for lease in Delhi road, Meerut")
			.description("Two office space/ godown available, size 950 sq ft and 2500 sq ft respectively. Shaded. The bigger godown has one washroom also. 24 hours water supply. 2 mins walk from the main road. All facilities nearby.")
			.details(PropertyDetails.builder().availabiltiy(Availability.READY_TO_MOVE).maxArea(2500).minArea(250).noOfWashrooms(1).propertyAge(Age.TEN_PLUS).type(AreaType.BUILTUP).unit(AreaUnit.ACRES).build())
			.location(Location.builder().city("meerut").houseNumber("10r").locality("delhi road").pincode(250002).build()).pricing(Pricing.builder().bookingAmount(25000).rentPerSqFt(150).build())
			.type(Type.COMMERCIAL)
			.category(Category.NORMAL)
			.timing(Timings.DAILY)
			.popularity(25)
			.purpose(Purpose.RENT).build();
	addDocument(writer, p1);
	Property p2= Property.builder().availableFrom(LocalDate.now()).title("3bhk for rent in Noida")
			.description("3 bhk fully furnished, in noida")
			.details(PropertyDetails.builder().availabiltiy(Availability.UNDER_CONSTRUCTION).maxArea(3000).noOfWashrooms(3).propertyAge(Age.ZERO_TO_ONE).type(AreaType.CARPET).unit(AreaUnit.SQUARE_FEET).build())
			.location(Location.builder().city("noida").houseNumber("507").locality("jaypee aman").pincode(201301).build()).pricing(Pricing.builder().bookingAmount(30000).rentPerSqFt(100).build())
			.type(Type.ROOM)
			.timing(Timings.MONTHLY)
			.category(Category.PREMIUM)
			.popularity(20)
			.purpose(Purpose.RENT).build();
	addDocument(writer, p2);
	writer.close();
	//System.out.println(PropertySearchService.searchByTypePurposeAndCity(index,Type.COMMERCIAL, Purpose.RENT,"meerut"));
	//System.out.println(PropertySearchService.searchByTypePurposeAndCity(index,Type.GYM, Purpose.RENT,"meerut"));
	//System.out.println(PropertySearchService.searchByTypePurposeAndCity(index,Type.COMMERCIAL, Purpose.RENT,"mert"));
	//System.out.println(PropertySearchService.searchByTimings(index,Timings.DAILY));
	//PropertySearchService.searchBySpaceNameOrTitle(index, "lease space for office in meerut").stream().forEach(System.out::println);
	//PropertySearchService.searchByAvailability(index, Availability.READY_TO_MOVE);
	//PropertySearchService.searchByPopularity(index, 20);
	PropertySearchService.searchByCategory(index, Category.NORMAL);
	}


	

	private static void addDocument(IndexWriter writer,Property p) throws IOException {
		Document d1= new Document();
		d1.add(new TextField("title",p.getTitle(), Field.Store.YES));//analyzed by default
		d1.add(new TextField("description",p.getDescription(), Field.Store.YES));
		//stored field is only stored, cannot be used for searching, not indexed
		d1.add(new StringField("type",p.getType().toString(), Store.YES));
		d1.add(new StringField("purpose",p.getPurpose().toString(), Field.Store.YES));
		d1.add(new StringField("city", p.getLocation().getCity(), Field.Store.YES));
		d1.add(new StringField("timing", p.getTiming().toString(), Field.Store.YES));
		d1.add(new StringField("availabiltiy", p.getDetails().getAvailabiltiy().toString(), Field.Store.YES));
		d1.add(new NumericDocValuesField("popularity", p.getPopularity()));
		d1.add(new StringField("category",p.getCategory().toString(), Store.YES));
//	d1.add(new IntPoint("rank", 5, Field.Store.YES));
		writer.addDocument(d1);
	}
}
