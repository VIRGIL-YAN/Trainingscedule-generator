package generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

public class StringImporter {
	static StringImporter stringImporter;
	public static StringImporter getInstance(){
		if(stringImporter == null){
			stringImporter = new StringImporter();
		}
		return stringImporter;
	}
	public LinkedList<String> importStringsFromFile(File file) throws Exception{
		LinkedList<String> stringList = null;
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "";
		while((line = br.readLine()) != null){
			stringList.add(line);
		}
		return stringList;
	}
	
}
