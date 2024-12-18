Notes on implementation
-----------------------

### Testing Controllers

#### Used annotations and classes

* `@AutoConfigureJsonTesters` tells Spring to configure beans of type
  JacksonTester for some fields we declare in the test. In our case,
  we use @Autowired to inject two JacksonTester beans from the test
  context. Spring Boot, when instructed via this annotation, takes
  care of building these utility classes. A JacksonTester may be used to
  serialize and deserialize objects using the same configuration (i.e.,
  ObjectMapper ) as the app would do in runtime.
* `@WebMvcTest` , with the controller class as a parameter, makes Spring
  treat this as a presentation layer test. Thus, it’ll load only the relevant
  configuration around the controller: validation, serializers, security,
  error handlers, etc.
* `@MockBean` comes with the Spring Boot Test module and helps you
  develop proper unit tests by allowing you to mock other layers and
  beans you’re not testing.
* The `MockMvc` class is what we use in Spring to simulate requests to the
  presentation layer when we make a test that doesn’t load a real server.
  It’s provided by the test context so we can just inject it in our test.

#### Validation constraints

* [Constraints Docs](https://docs.jboss.org/hibernate/beanvalidation/spec/2.0/api/javax/validation/constraints/package-summary.html)
* [Custom Error Handling](https://thepracticaldeveloper.com/custom-error-handling-rest-controllers-spring-boot/)

#### Dealing with jackson's InvalidDefinitionException

We get InvalidDefinitionException because of Lazy Initialization of field "user" in ChallengeAttempt.
This user is not queried at the moment of serialization so the exception is thrown:
```
com.fasterxml.jackson.databind.exc.InvalidDefinitionException: 
No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor
```
In order to customize the JSON serialization:
* add dependency for jackson-datatype-hibernate5
```xml
<dependency>
    <groupId>com.fasterxml.jackson.datatype</groupId>
    <artifactId>jackson-datatype-hibernate5</artifactId>
</dependency>
```
* create a bean for Hibernate module for Jackson
```java
@Configuration
public class JsonConfiguration {
    @Bean
    public Module hibernateModule() {
        return new Hibernate5Module();
    }
}
```
