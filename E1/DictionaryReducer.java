package Dictionary;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DictionaryReducer extends Reducer<Text,Text,Text,Text> {
   public Text result = new Text();
   public void reduce(Text word, Iterable<Text> values, Context context ) throws IOException, InterruptedException {
     // TODO iterate through values, parse, transform, and write to context
            String translations = "";
	    String translationFrench = "";
	    String translationGerman = "";
	    String translationItalian = "";
	    String translationPortugese = "";
       	    String translationSpanish = "";
	    boolean blnFrench = false;
	    boolean blnGerman = false;
	    boolean blnItalian = false;
	    boolean blnPortugese = false;
	    boolean blnSpanish = false;
            for (Text val : values)
            {	
		String value = val.toString();
		if(!blnFrench && value.contains("french")) {
			translationFrench += value;
			blnFrench = true;	
		} else if(blnFrench && value.contains("french")) {
			translationFrench += "," + value.substring(7);  	
		}
		
		if(!blnGerman && value.contains("german")) {
			translationGerman += value;
			blnGerman = true;	
		} else if(blnGerman && value.contains("german")) {
			translationGerman += "," + value.substring(7);  	
		}

		if(!blnItalian && value.contains("italian")) {
			translationItalian += value;
			blnItalian = true;	
		} else if(blnItalian && value.contains("italian")) {
			translationItalian += "," + value.substring(8);  	
		}
		if(!blnPortugese && value.contains("portuguese")) {
			translationPortugese += value;
			blnPortugese = true;	
		} else if(blnPortugese && value.contains("portuguese")) {
			translationPortugese += "," + value.substring(11);  	
		}
		if(!blnSpanish && value.contains("spanish")) {
			translationSpanish += value;
			blnSpanish = true;	
		} else if(blnSpanish && value.contains("spanish")) {
			translationSpanish += "," + value.substring(8);  	
		}
            }
   	    translations = translationFrench.equals("") ? "french:N/A" : translationFrench;
	    translations += "|"; 
	    translations += translationGerman.equals("") ? "german:N/A" : translationGerman;
	    translations += "|"; 
	    translations += translationItalian.equals("") ? "italian:N/A" : translationItalian;
	    translations += "|" ;
	    translations += translationPortugese.equals("") ? "portuguese:N/A" : translationPortugese;
	    translations += "|"; 
            translations += translationSpanish.equals("") ? "spanish:N/A" : translationSpanish;
            result.set(translations);
            context.write(word, result);
        }
}
