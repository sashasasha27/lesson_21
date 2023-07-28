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
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.context.ContextConfiguration;

import static com.consol.citrus.actions.ExecuteSQLAction.Builder.sql;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes = {EndpointConfig.class})
public class BaseTest extends TestNGCitrusSpringSupport {

    @Autowired
    protected HttpClient yellowDuckService;

    protected void sendGetRequest(TestCaseRunner runner, HttpClient URL, String path, String queName, String queValue) {
        runner.$(http()
                .client(String.valueOf(URL))
                .send()
                .get(path)
                .queryParam(queName,queValue));
    }

    protected void sendPostRequest(TestCaseRunner runner, HttpClient URL, String path, String fN, String fV, String sN, String sV, String tN, String tV, String foN,
                                   String foV, String fhN, String fhV, String ssN, String ssV) {
        runner.$(http()
                .client(String.valueOf(URL))
                .send()
                .post(path)
                .queryParam(fN, fV)
                .queryParam(sN,sV)
                .queryParam(tN,tV)
                .queryParam(foN,foV)
                .queryParam(fhN, fhV)
                .queryParam(ssN,ssV));
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

    protected void validateStringRequest(TestCaseRunner runner, HttpClient URL, MessageType messageType, String payload) {
        runner.$(http()
                .client(URL)
                .receive()
                .response()
                .message().type(messageType)
                .body(payload));
    }

    protected void validateJSONRequest(TestCaseRunner runner, HttpClient URL, MessageType messageType, String path) {
        runner.$(http()
                .client(URL)
                .receive()
                .response()
                .message().type(messageType)
                .body(new ClassPathResource(path)));
    }
    protected void validateRequest(TestCaseRunner runner, HttpClient URL, MessageType messageType, ObjectMappingPayloadBuilder builder) {
        runner.$(http()
                .client(URL)
                .receive()
                .response()
                .message().type(messageType)
                .body(new ObjectMappingPayloadBuilder( new ObjectMapper())));
    }

    @Autowired
    protected SingleConnectionDataSource testDb;
    protected void databaseUpdate(TestCaseRunner runner, String sql) {
        runner.$(sql(testDb)
                .statement(sql));
    }


}