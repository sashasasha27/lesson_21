package autotests.clients;

import autotests.EndpointConfig;
import autotests.payloads.DuckProperties;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes = {EndpointConfig.class})
public class DuckCRUDClient extends TestNGCitrusSpringSupport {
    @Autowired
    protected HttpClient yellowDuckService;

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

    public void duckDelete(TestCaseRunner runner, String id){
        runner.$(http().client(yellowDuckService)
                .send()
                .delete("/api/duck/delete")
                .queryParam("id", id));
    }

    public void duckGetAllIds(TestCaseRunner runner){
        runner.$(http().client(yellowDuckService)
                .send()
                .get("/api/duck/getAllIds"));
    }

    public void duckUpdate(TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState){
        runner.$(http().client(yellowDuckService)
                .send()
                .put("/api/duck/update")
                .queryParam("color", color)
                .queryParam("height", height)
                .queryParam("id", id)
                .queryParam("material", material)
                .queryParam("sound", sound)
                .queryParam("wingsState", wingsState));
    }

    @Description("Валидация String-ой")
    public void validateResponse(TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState){
        runner.$(http()
                .client(yellowDuckService)
                .receive()
                .response(HttpStatus.OK)
                .message().type(MessageType.JSON)
                .body("{\n" + " \"color\": \"" + color + "\",\n" +
                        " \"height\": " + height + ",\n" +
                        " \"id\": " + id + ",\n" +
                        " \"material\": \"" + material + "\",\n" +
                        " \"sound\": \"" + sound + "\",\n" +
                        " \"wingsState\": \"" + wingsState + "\"\n" + "}"));

    }
    @Description("Валидация json-ом")
    public void validateResponse(TestCaseRunner runner, String expectedPayload) {
        runner.$(http()
                .client(yellowDuckService)
                .receive()
                .response(HttpStatus.OK)
                .message().type(MessageType.JSON)
                .body(new ClassPathResource("getDuckPropertiesTest/duckYellowProperties.json")));
    }


    public void validateResponse(TestCaseRunner runner, Object expectedPayload){
        DuckProperties duckProperties = new DuckProperties().color("yellow").height(1).id("1")
                .material("rubber").sound("quack").wingsState("ACTIVE");
        runner.$(http()
                .client(yellowDuckService)
                .receive()
                .response(HttpStatus.OK)
                .message().type(MessageType.JSON)
                .body(new ObjectMappingPayloadBuilder(duckProperties, new ObjectMapper())));
    }


}