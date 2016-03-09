package Dictionary;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DictionaryMapper  extends Mapper<Text, Text, Text, Text> {
    
    // TODO define class variables for translation, language, and file name
	public Text word = new Text();
	String fileName = "";
    public void setup(Context context) {
      // TODO determine the language of the current file by looking at it's name
        fileName = ((FileSplit) context.getInputSplit()).getPath().getName();
        String[] file = fileName.split("\\.");
        fileName = file[0];
    }

    public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
    // TODO instantiate a tokenizer based on a regex for the file
        if(value.toString().matches("^(.+\\[\\S+\\])$")) {
            String[] valueArray = value.toString().split("\\[");
            String mainText = valueArray[0];
            String partOfSpeech = valueArray[1];
      	
            if(partOfSpeech.equalsIgnoreCase("adjective]") || partOfSpeech.equalsIgnoreCase("adverb]") || partOfSpeech.equalsIgnoreCase("conjunction]") || partOfSpeech.equalsIgnoreCase("noun]") || partOfSpeech.equalsIgnoreCase("preposition]") || partOfSpeech.equalsIgnoreCase("pronoun]") || partOfSpeech.equalsIgnoreCase("verb]")) {
                String keyMap = key.toString() + ": [" + partOfSpeech;
                key.set(keyMap);
                word.set(fileName + ":" + mainText);
                context.write(key, word);
            }
        }
    }
}
