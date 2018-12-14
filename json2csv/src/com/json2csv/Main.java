package com.json2csv;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.json2csv.dto.MainJson;
import com.json2csv.dto.OrgUnitId;
import com.opencsv.CSVWriter;

public class Main {

	public static void main(String[] args) throws Exception {
		List<MainJson> data = readJsonData();
		writeDataToCsvAtOnce(data, "D:\\test\\test2.csv"); 
	}
	
	
	

	public static List<MainJson> readJsonData() throws Exception {

		ObjectMapper mapper = new ObjectMapper();

		FileInputStream fis = new 
				FileInputStream("src/main/resources/sai.json");

		List<MainJson> data = mapper.readValue(fis, TypeFactory.defaultInstance()
				.constructCollectionType(List.class, MainJson.class));

		return data;
	}

	public static void writeDataToCsvAtOnce(List<MainJson> jsonData, String filePath) { 
		// first create file object for file placed at location 
		// specified by filepath 
		File file = new File(filePath); 

		try { 
			// create FileWriter object with file as parameter 
			FileWriter outputfile = new FileWriter(file); 

			// create CSVWriter object filewriter object as parameter 
			CSVWriter writer = new CSVWriter(outputfile); 

			// create a List which contains String array 
			List<String[]> data = new ArrayList<>(); 
			data.add(new String[] { "Name", "Version", "Alias", "OrgUnitId" }); 
			for (MainJson obj : jsonData) {
				for (OrgUnitId orgUnitId : obj.getOrgUnitIds()) {
					data.add(new String[] { obj.getId().getName(), obj.getId().getVersion().toString(), obj.getId().getAlias(), orgUnitId.get$oid() }); 
				}
			}
			writer.writeAll(data);
			// closing writer connection 
			writer.close(); 
		} catch (IOException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} 
	} 

}