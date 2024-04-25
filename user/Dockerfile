# 该镜像需要依赖的基础镜像
FROM java:8
# 将当前目录下的jar包复制到docker容器的/目录下
ADD ./user-starter/target/user-starter-1.0-SNAPSHOT.jar /home/apps/user/user-starter-1.0-SNAPSHOT.jar

# 声明服务运行在8080端口
EXPOSE 8082
# 指定docker容器启动时运行jar包
CMD ["java", "-jar", "-DSpring.profiles.active=pro","/home/apps/user/user-starter-1.0-SNAPSHOT.jar"]
