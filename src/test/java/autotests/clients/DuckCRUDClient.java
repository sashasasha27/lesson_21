package autotests.clients;

import autotests.EndpointConfig;
import autotests.payloads.DuckProperties;
import autotests.tests.BaseTest;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes = {EndpointConfig.class})
public class DuckCRUDClient extends BaseTest {
    @Autowired
    protected HttpClient yellowDuckService;

    public void duckCreate(TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState) {
        sendPostRequest(runner,
                yellowDuckService,
                "/api/duck/create",
                "color", color,
                "height", height,
                "id", id,
                "material", material,
                "sound", sound,
                "wingsState", wingsState);
    }

    public void duckDelete(TestCaseRunner runner, String id){
        sendRequest(runner,
                yellowDuckService,
                "/api/duck/delete",
                "id",
                id);
    }

    public void duckGetAllIds(TestCaseRunner runner){
        sendDeleteRequest(runner,
                yellowDuckService,
                "/api/duck/getAllIds");
    }

    public void duckUpdate(TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState){
        sendPostRequest(runner,
                yellowDuckService,
                "/api/duck/update",
                "color", color,
                "height", height,
                "id", id,
                "material", material,
                "sound", sound,
                "wingsState", wingsState);
    }

    @Description("Валидация String-ой")
    public void validateResponse(TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState){
        validateStringRequest(runner,yellowDuckService,MessageType.JSON,"{\n" + " \"color\": \"" + color + "\",\n" +
                        " \"height\": " + height + ",\n" +
                        " \"id\": " + id + ",\n" +
                        " \"material\": \"" + material + "\",\n" +
                        " \"sound\": \"" + sound + "\",\n" +
                        " \"wingsState\": \"" + wingsState + "\"\n" + "}");
    }

    @Description("Валидация json-ом")
    public void validateResponse(TestCaseRunner runner, String expectedPayload) {
        validateJSONRequest(runner,
                yellowDuckService,
                MessageType.JSON,
                "getDuckPropertiesTest/duckYellowProperties.json" );
    }

    public void validateResponse(TestCaseRunner runner, Object expectedPayload){
        DuckProperties duckProperties = new DuckProperties().color("yellow").height(1).id("1")
                .material("rubber").sound("quack").wingsState("ACTIVE");
        validateRequest(runner,
                yellowDuckService,
                MessageType.JSON,
                new ObjectMappingPayloadBuilder(duckProperties, new ObjectMapper()));
    }


}