package com.alvin.springbootguru.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Registers shared components (web advice, cache helpers, JPA base types). Add this library as a
 * dependency and define datasource / JPA / cache in your application using env vars or external
 * config — do not embed credentials in the library.
 * <p>
 * Your {@code @SpringBootApplication} should {@code @EntityScan} and {@code @EnableJpaRepositories}
 * for your own packages.
 */
@AutoConfiguration
@ComponentScan(basePackages = {
		"com.alvin.springbootguru.common",
		"com.alvin.springbootguru.config"
})
public class SpringBootGuruAutoConfiguration {
}
