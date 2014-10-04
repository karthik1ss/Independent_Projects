
Hadoop Program Execution steps 

1. source hadoop-setup.sh

2. copy source .csv files into HDFS 

hadoop fs -put WhiteHouse-WAVES-2012-Released-through-October-2012.csv /home/sxs129531/whitehouse/input/file1.csv
hadoop fs -put WhiteHouse-WAVES-Released-0611.csv /home/sxs129531/whitehouse/input/file2.csv
hadoop fs -put WhiteHouse-WAVES-Released-1210.csv /home/sxs129531/whitehouse/input/file3.csv
hadoop fs -put WhiteHouse-WAVES-Released-through-December-2011.csv /home/sxs129531/whitehouse/input/file4.csv


2. copy HW1_1.java,HW1_2.java,HW1_3.java,HW1_4.java into home directory using winscp


3. Create jar file for java file 

mkdir javatemp,javatemp1,javatemp2,javatemp3

javac -classpath /usr/local/hadoop-1.0.4/hadoop-core-1.0.4.jar -d javatemp HW1_1.java
javac -classpath /usr/local/hadoop-1.0.4/hadoop-core-1.0.4.jar -d javatemp1 HW1_2.java
javac -classpath /usr/local/hadoop-1.0.4/hadoop-core-1.0.4.jar -d javatemp2 HW1_3.java
javac -classpath /usr/local/hadoop-1.0.4/hadoop-core-1.0.4.jar -d javatemp3 HW1_4.java


jar -cvf HW1_1.jar -C javatemp/ .
jar -cvf HW1_2.jar -C javatemp1/ .
jar -cvf HW1_3.jar -C javatemp2/ .
jar -cvf HW1_4.jar -C javatemp3/ .



4. To run the job : 

hadoop jar HW1_1.jar HW1_1 /home/sxs129531/whitehouse/input /home/sxs129531/temp /home/sxs129531/whitehouse/output
hadoop jar HW1_2.jar HW1_2 /home/sxs129531/whitehouse/input /home/sxs129531/temp /home/sxs129531/whitehouse/output
hadoop jar HW1_3.jar HW1_3 /home/sxs129531/whitehouse/input /home/sxs129531/temp /home/sxs129531/whitehouse/output
hadoop jar HW1_4.jar HW1_4 /home/sxs129531/whitehouse/input /home/sxs129531/whitehouse/output


5. To read the output:

 hadoop fs -cat /home/sxs129531/whitehouse/output/part-r-00000


6. To run it again - delete temp , whitehouse/output folder 

hadoop fs -rmr /home/sxs129531/temp
hadoop fs -rmr /home/sxs129531/whitehouse/output




