#MapReduce Sample Applications

- Use OpenJDK 8
- Run on top of Docker Orchestration [Github](https://github.com/marcionicolau/docker-hadoop)
- Developed and Build using IntelliJ IDEA Community IDE

## Enable Hadoop Cluster
```
docker-compose -d up --build
```


# Run WordCount

```
docker build -t hadoop-app:0.0.1 .\docker\
docker run --rm --network docker-hadoop_default --env-file hadoop.env hadoop-base:3.3.1-jdk8-alpine hdfs dfs -mkdir -p /inputWord/
docker run --rm --network docker-hadoop_default --env-file hadoop.env hadoop-base:3.3.1-jdk8-alpine hdfs dfs -copyFromLocal -f /opt/hadoop-3.3.1/README.txt /inputWord/
docker run --rm --network docker-hadoop_default --env-file hadoop.env hadoop-app:0.0.1 word
docker run --network docker-hadoop_default --env-file hadoop.env hadoop-base:3.3.1-jdk8-alpine hdfs dfs -cat /outputWord/*
docker run --network docker-hadoop_default --env-file hadoop.env hadoop-base:3.3.1-jdk8-alpine hdfs dfs -rm -r /outputWord
```

# Run Sales Country

```
docker build -t hadoop-app:0.0.1 .\docker\
docker run --rm --network docker-hadoop_default --env-file hadoop.env hadoop-base:3.3.1-jdk8-alpine hdfs dfs -mkdir -p /inputSales/
docker run --rm --network docker-hadoop_default --env-file hadoop.env -v ${PWD}/datasets:/datasets hadoop-base:3.3.1-jdk8-alpine hdfs dfs -
copyFromLocal -f /datasets/sales/SalesJan2009.csv /inputSales/
docker run --rm --network docker-hadoop_default --env-file hadoop.env hadoop-app:0.0.1 sales
docker run --network docker-hadoop_default --env-file hadoop.env hadoop-base:3.3.1-jdk8-alpine hdfs dfs -cat /outputSales/*
docker run --network docker-hadoop_default --env-file hadoop.env hadoop-base:3.3.1-jdk8-alpine hdfs dfs -rm -r /outputSales
```

# Noaa Weather

```
docker build -t hadoop-app:0.0.1 .\docker\
docker run --rm --network docker-hadoop_default --env-file hadoop.env hadoop-base:3.3.1-jdk8-alpine hdfs dfs -mkdir -p /inputWeather/
docker run --rm --network docker-hadoop_default --env-file hadoop.env -v ${PWD}/datasets:/datasets hadoop-base:3.3.1-jdk8-alpine hdfs dfs -
copyFromLocal -f /datasets/weather/* /inputWeather/
docker run --rm --network docker-hadoop_default --env-file hadoop.env hadoop-app:0.0.1 weather
docker run --network docker-hadoop_default --env-file hadoop.env hadoop-base:3.3.1-jdk8-alpine hdfs dfs -cat /outputWeather/*
docker run --network docker-hadoop_default --env-file hadoop.env hadoop-base:3.3.1-jdk8-alpine hdfs dfs -rm -r /outputWeather
```