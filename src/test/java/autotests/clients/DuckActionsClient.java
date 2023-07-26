package autotests.clients;

import autotests.EndpointConfig;
import autotests.tests.BaseTest;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes = {EndpointConfig.class})
public class DuckActionsClient extends BaseTest {
    @Autowired
    protected HttpClient yellowDuckService;

    public void duckSwim(TestCaseRunner runner, String id) {
        sendRequest(runner,
                yellowDuckService,
                "/api/duck/action/swim",
                "id",
                id);
    }

    public void duckFly(TestCaseRunner runner, String id) {
      sendRequest(runner,
              yellowDuckService,
              "/api/duck/action/fly",
              "id",
              id);
    }

    public void duckProperties(TestCaseRunner runner, String id) {
        sendRequest(runner,
                yellowDuckService,
                "/api/duck/action/properties",
                "id",
                id);
    }

    public void duckQuack(TestCaseRunner runner, String id, String repetitionCount, String soundCount) {
        sendGetRequest(runner,
                yellowDuckService,
                "/api/duck/action/quack",
                "id", id,
                "repetitionCount", repetitionCount,
                "soundCount", soundCount);
    }

    public void duckCreate(TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState) {
        runner.$(http().client(yellowDuckService)
                .send()
                .post("/api/duck/create")
                .message()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("{\n" +
                        "  \"color\":"+ color +"\",\n" +
                        "  \"height\":"+ height +"\",\n" +
                        "  \"height\":"+ id +"\",\n" +
                        "  \"material\":"+ material + "\",\n" +
                        "  \"sound\":"+ sound +"\",\n" +
                        "  \"wingsState\":" + wingsState +"\",\n" +
                        "}"));
    }

    @Description("Валидация полученного ответа String")
    public void validateResponse(TestCaseRunner runner,String color, String height, String id, String material, String sound, String wingsState) {
        validateStringRequest(runner,
                yellowDuckService,
                MessageType.JSON,
                "{\n" + " \"color\": \"" + color + "\",\n" +
                        " \"height\": " + height + ",\n" +
                        " \"id\": " + id + ",\n" +
                        " \"material\": \"" + material + "\",\n" +
                        " \"sound\": \"" + sound + "\",\n" +
                        " \"wingsState\": \"" + wingsState + "\"\n" + "}"
                );

    }
    @Description("Валидация полученного ответа json")
    public void validateResponse(TestCaseRunner runner, String expectedPayload) {
        validateJSONRequest(runner,
                yellowDuckService,
                MessageType.JSON,
                "getDuckPropertiesTest/actionsYellowDuck.json" );
    }

    public void validateResponse(TestCaseRunner runner, Object expectedPayload) {
        validateRequest(runner,
                yellowDuckService,
                MessageType.JSON,
                new ObjectMappingPayloadBuilder(new ObjectMapper()));
    }

}