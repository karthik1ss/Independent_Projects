import java.io.IOException;
import java.util.*;
import java.util.Map.*;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapreduce.Job;


public class HW1_part4
{


	public static class AvgMap extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> 
    {
	      
	      private Text word = new Text();
	      private String visit_date=null;
	      private String month = null;
	      private String yeardate =null;
	     
	      public void map(LongWritable key, Text value, OutputCollector< Text, Text> output, Reporter reporter) throws IOException
	      {
		        String line = value.toString();
		        									        
		       	String[] splits = line.split(",",-1);
	       	
		       	if(splits.length >=12)
		       	{
		       	
			       	visit_date= splits[11];
			       		       	
			       		String[] monthyear= visit_date.split("/",-1);
			       		
			       		if(monthyear.length>=2)
			       		{
			       	
					       	month = monthyear[0].toString();
					       	yeardate = monthyear[2].toString();
					       	
					       	if(!yeardate.isEmpty() && !month.isEmpty())
					       	{
					       		String[] year = yeardate.split(" ",-1);
						        //String Last2Year = year[0].substring(year[0].length()-2);
						       	
						        word.set(month);
						       
						       	output.collect(word, new Text(year[0].substring(year[0].length()-2) +"-"+ 1));
					       	}
					       	
				       	}
			       	
		       	 }
	       		
	       	}	       
	        
	          
    
     }

		
// Reducer Class  --------- sums up the values

	    public static class AvgReduce extends MapReduceBase implements Reducer<Text, Text, Text, FloatWritable> 
	    {
	    	private String year =null;	    	
	    	
	    	int sum=0;
	        float avg=0f;
	    	
		      public void reduce(Text key, Iterator<Text> values, OutputCollector< Text, FloatWritable> output, Reporter reporter) throws IOException 
		      {
		    	   HashMap<String,String> pairMap = new HashMap<String,String> ();
		    	  	        
			        while (values.hasNext())                               
			        {
			        	String[] val = values.next().toString().split("-",-1);
				        year = val[0].toString();
					   int count = Integer.parseInt(val[1]);
			        	sum = sum + count;
			        	pairMap.put(year,"1");				       			       
			        }
			        
			        avg = sum/pairMap.size();
			        output.collect(key, new FloatWritable(avg));
		      }
	    }	
	
	
	    	    
	
	public static void main(String[] args)  throws IOException, ClassNotFoundException, InterruptedException
	{
		JobConf conf = new JobConf(HW1_part4.class); // Create a JobConf object
        conf.setJobName("Homework4"); // job name*/


        conf.setOutputKeyClass(Text.class); // see output.collect in map --> this and the following line are reduce input pair so reduce should have Text-IntWritable pair as input
        conf.setOutputValueClass(Text.class); // see output.collect

        conf.setMapperClass(AvgMap.class); // specifies the mapper class
        conf.setReducerClass(AvgReduce.class); // specifies the reducer


        conf.setInputFormat(org.apache.hadoop.mapred.TextInputFormat.class); // input type is text, key is line no., value is the line words
        conf.setOutputFormat(org.apache.hadoop.mapred.TextOutputFormat.class); // output type is text

        FileInputFormat.setInputPaths(conf, new Path(args[0])); // specifies the Input directory /home/<anyname_or_netid>/input
        FileOutputFormat.setOutputPath(conf, new Path(args[1])); // specifies the Output directory /home/<anyname_or_netid>/output


       JobClient.runJob(conf);
	}

}
