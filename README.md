# spring-boot-guru

Reusable Spring Boot 3 building blocks: CRUD base controller, JPA `GenericSpecification`, pagination DTOs, Caffeine-backed cache helpers, and a common API envelope.

**Repository:** [github.com/thanthtooaung-coding/spring-boot-guru](https://github.com/thanthtooaung-coding/spring-boot-guru)

## Consume via JitPack

1. Create a **Git tag** (e.g. `v0.1.0`) on this repo after pushing your code.
2. In the consuming project, add [JitPack](https://jitpack.io) and the dependency:

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.thanthtooaung-coding</groupId>
  <artifactId>spring-boot-guru</artifactId>
  <version>v0.1.0</version>
</dependency>
```

Replace `v0.1.0` with your tag or a **commit hash**.

## Consume via GitHub Packages (Maven)

CI deploys tagged releases (`v*`) to [GitHub Packages](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry).

```xml
<repositories>
  <repository>
    <id>github</id>
    <url>https://maven.pkg.github.com/thanthtooaung-coding/spring-boot-guru</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.alvin.springbootguru</groupId>
  <artifactId>spring-boot-guru</artifactId>
  <version>0.1.0</version>
</dependency>
```

Use the **same version** as the Git tag without the `v` prefix (e.g. tag `v0.1.0` → version `0.1.0`). Authenticate with a GitHub PAT (`read:packages`) in `~/.m2/settings.xml` under server id `github`.

## GHCR (optional)

The workflow pushes an image to `ghcr.io/thanthtooaung-coding/spring-boot-guru/spring-boot-guru` containing the library JAR at `/opt/spring-boot-guru/spring-boot-guru.jar`.

## Use in a Spring Boot app

- Add the dependency (above).
- Configure **datasource / JPA / cache** in your own `application.properties` or environment variables (this library does not ship credentials).
- Put your `@SpringBootApplication` in your package; use `@EntityScan` / `@EnableJpaRepositories` for your entities.
- Extend `BaseCrudController`, `BaseServiceImpl` / `CachingBaseServiceImpl`, `BaseRepository`, `MasterEntity`, etc., from `com.alvin.springbootguru.common.*`.

Auto-configuration loads `com.alvin.springbootguru.autoconfigure.SpringBootGuruAutoConfiguration` via `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`.
