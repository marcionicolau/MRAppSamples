#!/bin/bash

function runSample() {
  local sample=$1
  local title
  local CLASS_TO_RUN
  local PARAMS

  case $sample in
    wc|word) 
      title="Word Count"
      CLASS_TO_RUN="org.example.mrsamples.WordCount"
      PARAMS="/inputWord /outputWord"
    ;;
    sc|sales)
      title="Sales by Country"
      CLASS_TO_RUN="org.example.mrsamples.SalesCountry"
      PARAMS="/inputSales /outputSales"
    ;;
    nw|weather)
      title="NOAA Weather Max Temperature by Year"
      CLASS_TO_RUN="org.example.mrsamples.NoaaWeather"
      PARAMS="/inputWeather /outputWeather"
  esac


  echo "Running $title"
  ${HADOOP_HOME}/bin/hadoop jar ${JAR_FILEPATH} ${CLASS_TO_RUN} ${PARAMS}
}

runSample "$@"