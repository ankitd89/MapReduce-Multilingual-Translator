package Dictionary;
import java.io.IOException;

import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Reducer;


public class CacheReducer extends Reducer<Text,Text,Text,Text> {
      public void reduce(Text word, Iterable<Text> values, Context context ) throws IOException, InterruptedException {
	// TODO: add appropriate translations to the existing word/part-of-speech translations
	Text val = new Text();
	for(Text x : values)
        {
		val.set(x);
	}
	 context.write(word,val);
              
      }

   }

