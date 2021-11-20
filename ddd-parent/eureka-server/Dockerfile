FROM centos:7.5.1804
MAINTAINER pader "5ycode@sina.com"

# set environment
ENV BASE_DIR="/home/appuser" \
    CLASSPATH=".:/home/appuser/conf:$CLASSPATH" \
    JAVA_HOME="/usr/lib/jvm/java-1.8.0-openjdk" \
    USER="appuser" \
    JAVA="/usr/lib/jvm/java-1.8.0-openjdk/bin/java" \
    JVM_XMS="256m" \
    JVM_XMX="256m" \
    JVM_XMN="78m" \
    JVM_MS="128m" \
    JVM_MMS="256m" \
    REMOTE_DEBUG="n" \
    TOMCAT_ACCESSLOG_ENABLED="false" \
    APPNAME="eureka-server" \
    PORT=8765 \
    TIME_ZONE="Asia/Shanghai"

ARG EUREKA_VERSION=0.1
ARG HOT_FIX_FLAG=""

WORKDIR $BASE_DIR

RUN set -x \
    && yum update -y \
    && yum install -y java-1.8.0-openjdk java-1.8.0-openjdk-devel wget iputils nc  vim libcurl
ADD target/eureka-server.jar /home/appuser/
# RUN wget  https://maven.aliyun.com/nexus/content/groups/public/com/yxkong/eureka-server/${EUREKA_VERSION}/eureka-server-${EUREKA_VERSION}.jar -P /home/appuser
RUN yum autoremove -y wget \
    && ln -snf /usr/share/zoneinfo/$TIME_ZONE /etc/localtime && echo $TIME_ZONE > /etc/timezone \
    && yum clean all



ADD bin/docker-startup.sh bin/docker-startup.sh


# set startup log dir
RUN mkdir -p logs \
	&& cd logs \
	&& touch start.out \
	&& ln -sf /dev/stdout start.out \
	&& ln -sf /dev/stderr start.out
RUN chmod +x bin/docker-startup.sh

EXPOSE 8848
ENTRYPOINT ["bin/docker-startup.sh"]