#OS+JVM
FROM amazoncorretto:19
#Jar dosyası
COPY build/libs/AuthMicroservice-v0.1.jar app.jar
#Çift tıklama
ENTRYPOINT ["java","-jar","/app.jar"]

#Dockerfile dosyası ile image oluşturmak için g

#docker build -t berresugulsecgin/java12configservergit:v001 .

# docker run -p 9099:9090 -e JAVA12_SECRETKEY=12345 -e JAVA12_ISSUER=Java12 berresugulsecgin/java12authmicroservice:v001
