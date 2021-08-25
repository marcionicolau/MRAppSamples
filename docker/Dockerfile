FROM hadoop-base:3.3.1-jdk8-alpine

COPY MRAppSamples-1.0-SNAPSHOT.jar /opt/hadoop/applications/MRApp.jar

ENV JAR_FILEPATH="/opt/hadoop/applications/MRApp.jar"


ADD run.sh /run.sh
ADD bootstrap.sh /bootstrap.sh

RUN chmod a+x /run.sh \
    && dos2unix /run.sh \
    && chmod a+x /bootstrap.sh \
    && dos2unix /bootstrap.sh

ENTRYPOINT [ "/bootstrap.sh" ]
CMD [ "wc" ]