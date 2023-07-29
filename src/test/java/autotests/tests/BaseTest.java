package autotests.tests;

import autotests.EndpointConfig;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.context.ContextConfiguration;

import static com.consol.citrus.actions.ExecuteSQLAction.Builder.sql;
import static com.consol.citrus.actions.ExecuteSQLQueryAction.Builder.query;
import static com.consol.citrus.dsl.MessageSupport.MessageBodySupport.fromBody;
import static com.consol.citrus.dsl.MessageSupport.MessageHeaderSupport.fromHeaders;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes = {EndpointConfig.class})
public class BaseTest extends TestNGCitrusSpringSupport {

    @Autowired
    protected HttpClient yellowDuckService;

    @Autowired
    protected SingleConnectionDataSource testDb;

    public void validateDuckInDatabase(TestCaseRunner runner, String id, String color, String height, String material, String sound, String wingsState) {
        runner.$(query(testDb)
                .statement("SELECT * FROM DUCK WHERE ID=" + id)
                .validate("COLOR", color)
                .validate("HEIGHT", height)
                .validate("MATERIAL", material)
                .validate("SOUND", sound)
                .validate("WINGS_STATE", wingsState));
    }

    protected void sendGetRequest(TestCaseRunner runner, HttpClient URL, String path, String queName, String queValue) {
        runner.$(http()
                .client(URL)
                .send()
                .get(path)
                .queryParam(queName,queValue));
    }

    protected void sendPostRequest(TestCaseRunner runner, HttpClient URL, String path, String body) {
        runner.$(http()
                .client(URL)
                .send()
                .post(path)
                .message().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body));
    }
     protected void sendPostRequest(TestCaseRunner runner, HttpClient URL, String path, Object body) {
        runner.$(http().client(URL)
                .send()
                .post(path)
                .message().contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new ObjectMappingPayloadBuilder(body, new ObjectMapper())));

     }


    protected void sendDeleteRequest(TestCaseRunner runner, HttpClient URL,String path) {
        runner.$(http()
                .client(String.valueOf(URL))
                .send()
                .delete(path));
    }

    protected void sendGetRequest(TestCaseRunner runner, HttpClient URL, String path, String queN1, String queV1, String queN2, String queV2,
                                  String queN3, String queV3) {
        runner.$(http()
                .client(String.valueOf(URL))
                .send()
                .get(path)
                .queryParam(queN1, queV1)
                .queryParam(queN2, queV2)
                .queryParam(queN3, queV3));
    }

    protected void validateStringResponse(TestCaseRunner runner, HttpClient URL, MessageType messageType, String payload) {
        runner.$(http()
                .client(URL)
                .receive()
                .response()
                .message().type(messageType)
                .body(payload));
    }


    protected void validateResponse(TestCaseRunner runner, HttpClient URL, HttpStatus status, String body) {
        runner.$(http()
                .client(URL)
                .receive()
                .response(status)
                .message().type(MessageType.JSON)
                .body(body));
    }

    protected void validateResponse1(TestCaseRunner runner, HttpClient URL, HttpStatus status, String body) {
        runner.$(http()
                .client(URL)
                .receive()
                .response(status)
                .message().type(MessageType.JSON)
                .body(new ClassPathResource(body)));
    }
    protected void databaseUpdate(TestCaseRunner runner, String sql) {
        runner.$(sql(testDb)
                .statement(sql));
    }

    public void extractId(TestCaseRunner runner) {
        runner.$(http().client(yellowDuckService)
                .receive()
                .response(HttpStatus.OK)
                .message()
                .type(MessageType.JSON)
                .extract(fromBody().expression("$.id", "duckId"))
                .extract(fromHeaders().header("id", "duckId")));
    }



}