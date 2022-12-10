## Testing in Spring Boot

* `@SpringBootTest` annotation
    * starts Spring context
    * makes all configured beans available
* `@WebMvcTest` annotation
    * focuses on controller-layer tests
* To integrate String in JUnit tests via the `@ExtendWith` annotation 
    * use `SpringExtension`
* `@MockBean` annotation replaces or adds a bean in the Spring context
