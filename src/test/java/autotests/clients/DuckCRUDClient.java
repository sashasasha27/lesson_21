package autotests.clients;

import autotests.EndpointConfig;
import autotests.payloads.DuckProperties;
import autotests.tests.BaseTest;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import com.consol.citrus.message.builder.ObjectMappingPayloadBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;

import static com.consol.citrus.actions.ExecuteSQLQueryAction.Builder.query;


@ContextConfiguration(classes = {EndpointConfig.class})
public class DuckCRUDClient extends BaseTest {
    @Autowired
    protected HttpClient yellowDuckService;

    @Step("Эндпоинт для создания уточки")
    public void duckCreate(TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState) {
        sendPostRequest(runner, yellowDuckService,
                "/api/duck/create",
                "color", color,
                "height", height,
                "id", id,
                "material", material,
                "sound", sound,
                "wingsState", wingsState);
    }

    @Step("Эндпоинт для удаления уточки")
    public void duckDelete(TestCaseRunner runner, String id){
        sendGetRequest(runner,
                yellowDuckService,
                "/api/duck/delete",
                "id",
                id);
    }

    @Step("Эндпоинт для получения всех id уточки")
    public void duckGetAllIds(TestCaseRunner runner){
        sendDeleteRequest(runner,
                yellowDuckService,
                "/api/duck/getAllIds");
    }

    @Step("Эндпоинт для обновления уточки")
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

    public void validateDuckInDatabase(TestCaseRunner runner, String id, String color, String height, String material, String sound, String wingsState) {
        runner.$(query(testDb)
                .statement("SELECT * FROM DUCK WHERE ID=" + id)
                .validate("COLOR", color)
                .validate("HEIGHT", height)
                .validate("MATERIAL", material)
                .validate("SOUND", sound)
                .validate("WINGS_STATE", wingsState));
    }


}