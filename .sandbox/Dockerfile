FROM openjdk:11
ARG VERSION

# Use BuildTools to get our Spigot JAR
WORKDIR /build

RUN wget https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar
RUN java -jar BuildTools.jar --rev ${VERSION} && cp "spigot-${VERSION}.jar" spigot.jar

# Run the actual server in its own directory
WORKDIR /server
ENTRYPOINT ["java", "-jar", "/build/spigot.jar", "--nogui"]