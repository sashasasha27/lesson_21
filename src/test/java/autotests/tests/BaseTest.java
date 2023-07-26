package autotests.tests;

import autotests.EndpointConfig;
import autotests.payloads.DuckProperties;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import com.consol.citrus.testng.CitrusParameters;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes = {EndpointConfig.class})
public class BaseTest {

    @Autowired
    protected HttpClient yellowDuckService;

    protected void sendRequest(TestCaseRunner runner, HttpClient URL, String path, String queName, String queValue) {
        runner.$(http()
                .client(String.valueOf(URL))
                .send()
                .get(path)
                .queryParam(queName,queValue));
    }

    protected void sendPostRequest(TestCaseRunner runner, HttpClient URL, String path, String fN, String fV, String sN, String sV, String tN, String tV,
                                   String foN, String foV, String fhN, String fhV, String ssN, String ssV) {
        runner.$(http()
                .client(String.valueOf(URL))
                .send()
                .get(path)
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
                .get(path));
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

    /*  DuckProperties duckProperties1 = new DuckProperties().color("yellow").height(0.05).material("rubber").sound("quack").wingsState("ACTIVE");
    DuckProperties duckProperties2 = new DuckProperties().color("green").height(1.0).material("plastic").sound("meow").wingsState("FIXED");
    @Test(dataProvider = "duckList")
    @CitrusTest
    @CitrusParameters({"payload", "response", "runner"})
    public void successfulDuckCreate(Object payload, String response, @Optional @CitrusResource TestCaseRunner runner) {
        createDuck(runner, payload);
        validateResponse(runner, response); }
    @DataProvider(name = "duckList") public Object[][] DuckProvider() {
        return new Object[][] {
                {duckProperties1, "getDuckPropertiesTest/duckYellowProperties.json", null}, {duckProperties2, "getDuckPropertiesTest/duckGreenProperties.json", null}
        };
    }///////в работе

*/
}