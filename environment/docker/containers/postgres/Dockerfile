FROM postgres:9.6.3


RUN apt-get -y update
RUN apt-get -y install wget

# Downloading Flyway
RUN wget https://repo1.maven.org/maven2/org/flywaydb/flyway-commandline/4.2.0/flyway-commandline-4.2.0-linux-x64.tar.gz

RUN mkdir -p /opt/sds/flyway \
    && tar -xzf flyway-commandline-4.2.0-linux-x64.tar.gz --strip 1 -C /opt/sds/flyway \
    && rm flyway-commandline-4.2.0-linux-x64.tar.gz \
    && ln -s /opt/sds/flyway/flyway /usr/local/bin


ADD scripts/flyway /tmp/flyway/scripts
RUN chmod a+x /tmp/flyway/scripts/*
