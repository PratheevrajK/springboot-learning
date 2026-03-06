### Spring Container:
- Spring Container is like an Object Factory with two primary functions:
  - Create and manage objects - `Inversion of Control`
  - Inject dependencies to the object - `Dependency Injection`

### Configuring Spring Container:
- XML configuration file - legacy
- Java Annotations - modern
- Java Source Code - modern

### Two recommended types of Injection:
- Constructor Injection
- Setter Injection

### Spring AutoWiring:
- Autowiring is used for injecting dependencies.
- Spring will look for type(class or interface) that matches and injects it automatically.

### @Component - Class level:
- Marks the class as Spring Bean making the class available for Dependency Injection.

### @Autowired - Constructor level:
- Tells the Spring to inject the dependency.

### @SpringBootApplication - Class level:
Bootstrap the application - creates application context, registers all beans and starts the embedded Tomcat server, etc...
- `@EnableAutoConfiguration` Enables SpringBoot's auto-configuration support.
- `@ComponentScan` Enables component scanning of current package. Also, recursively scans sub-packages.
- `@Configuration` Able to register extra beans with `@Bean` or import other configuration classes.
