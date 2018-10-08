FROM java
VOLUME /tmp
COPY btp.jar /
WORKDIR "/"
EXPOSE 8081
CMD ["java","-jar","btp.jar"]