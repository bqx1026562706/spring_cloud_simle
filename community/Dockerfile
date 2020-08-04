# 指定基础镜像
FROM java:8
# 维护者信息
MAINTAINER bqx
# 定义匿名卷
VOLUME /tmp
#复制文件或修改名称
ADD demo-0.0.1-SNAPSHOT.jar app.jar
# 允许指定的端口
EXPOSE 8990
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]