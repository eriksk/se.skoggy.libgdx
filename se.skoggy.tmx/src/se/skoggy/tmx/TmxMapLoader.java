package se.skoggy.tmx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import com.google.gson.Gson;

public class TmxMapLoader {

	public static <T> T load(Reader file, Class<?> implementationType){
		T map = null;		
		Gson gson = new Gson();
		try{
			map = (T)gson.fromJson(fileToString(file), implementationType);		
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return map;
	}
	
	private static String fileToString(Reader file){
		    try {
		    	BufferedReader br = new BufferedReader(file);
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();
		        while (line != null) {
		            sb.append(line);
		            sb.append("\n");
		            line = br.readLine();
		        }
		        br.close();
		        return sb.toString();
		    }catch(IOException ex){
		    	ex.printStackTrace();
		    } finally {
		    }
		return null;
	}
}
