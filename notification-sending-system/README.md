# notification-sending-system

---

For Docker container, to be packaged well:
```shell
mvn clean package install spring-boot:repackage -DskipTests
```

```shell
docker-compose up --build --remove-orphans
```

For remote debugging in Dockerfile:

```shell
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -Djava.security.egd=file:/dev/./urandom
```

---
