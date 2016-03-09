package Dictionary;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.HashMap;
import java.io.FileNotFoundException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.filecache.DistributedCache;

public class CacheMapper  extends Mapper<LongWritable, Text, Text, Text> {
 	String fileName=null, language=null;
	public Text word = new Text();	  
	public BufferedReader brReader; 
	public Map<String, String> translations = new HashMap<String, String>();
	   public void setup(Context context) throws IOException, InterruptedException{
		// TODO: determine the name of the additional language based on the file name 
		Path[] cacheFilesLocal = DistributedCache.getLocalCacheFiles(context.getConfiguration());
		fileName = cacheFilesLocal[0].getName().toString().trim();
		String[] file = fileName.split("\\.");
        	language = file[0];
	        // TODO: OPTIONAL: depends on your implementation -- create a HashMap of translations (word, part of speech, translations) from output of exercise 1
                String strLineRead = "";
		try {
			brReader = new BufferedReader(new FileReader(cacheFilesLocal[0].toString()));
			// Read each line, split and load to HashMap
			while ((strLineRead = brReader.readLine()) != null) {
				String langArray[] = strLineRead.split("\\t");
				if(langArray.length>1 && langArray[1].matches("^(.+\\[\\S+\\])$")) {
           			 	String[] valueArray = langArray[1].split("\\[");
            				String mainText = valueArray[0];
            				String partOfSpeech = valueArray[1];
      	
			                if(partOfSpeech.equalsIgnoreCase("adjective]") || partOfSpeech.equalsIgnoreCase("adverb]") || partOfSpeech.equalsIgnoreCase("conjunction]") || partOfSpeech.equalsIgnoreCase("noun]") || partOfSpeech.equalsIgnoreCase("preposition]") || partOfSpeech.equalsIgnoreCase("pronoun]") || partOfSpeech.equalsIgnoreCase("verb]")) {
						translations.put(langArray[0] + ": [" + partOfSpeech,language + ":" + mainText);
					}
				}
		    	}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (brReader != null) {
				brReader.close();
			}
		}
	   }
	   public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		// TODO: perform a map-side join between the word/part-of-speech from exercise 1 and the word/part-of-speech from the distributed cache file
		String[] token=value.toString().split("\\t");
		String newValue = "";
		if(translations.containsKey(token[0])) {
			newValue = translations.get(token[0]);
		} else {
			newValue = language + ":N/A";
		}
		word.set(token[1] + "|" + newValue);
		context.write(new Text(token[0]), word);

  	       // TODO: where there is a match from above, add language:translation to the list of translations in the existing record (if no match, add language:N/A
	 }
   }
