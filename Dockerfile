FROM openjdk:17-jdk
RUN mkdir -p /home/app
WORKDIR /app
COPY demo.jar ./demo.jar

#ENV TZ=Asia/Shanghai
#RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
#RUN apk add --update font-adobe-100dpi ttf-dejavu fontconfig

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "demo.jar"]