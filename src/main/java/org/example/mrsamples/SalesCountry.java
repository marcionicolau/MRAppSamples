package org.example.mrsamples;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class SalesCountry {

    public static class SalesMapper extends Mapper<Object, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);

        @Override
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String valueString = value.toString();
            String[] SingleCountryData = valueString.split(",");
            context.write(new Text(SingleCountryData[7]), one);
        }
    }

    public static class SalesReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private final IntWritable result = new IntWritable();
        @Override
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

            int frequencyForCountry = 0;
            for (IntWritable val : values) {
                frequencyForCountry += val.get();
            }
            result.set(frequencyForCountry);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf,"SalesByCountry");

        // Specify data type of output key and value
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Specify names of Mapper and Reducer Class
        job.setJarByClass(SalesCountry.class);
        job.setMapperClass(SalesCountry.SalesMapper.class);
        job.setReducerClass(SalesCountry.SalesReducer.class);

        // Specify input and output paths
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
