package autotests.tests;

import autotests.clients.DuckCRUDClient;
import autotests.payloads.DuckGetAllIds;
import autotests.payloads.DuckProperties;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import java.util.Arrays;

import static com.consol.citrus.actions.EchoAction.Builder.echo;
import static com.consol.citrus.dsl.JsonPathSupport.jsonPath;

public class DuckTest extends DuckCRUDClient {
    @Test(description = "1 - Проверка того, что уточка создается")
    @CitrusTest
    public void successfulCreateDuck1(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "Duck with id = ${duckId} is created");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        validateResponse(runner,yellowDuckService, HttpStatus.OK, "{\n" +
        " \"color\": \"yellow\",\n" +
        " \"height\": 0.05,\n" +
        " \"material\": \"plastic\",\n" +
        " \"sound\": \"quack\",\n" +
        " \"wingsState\": \"ACTIVE\"\n" + "}");
        runner.$(echo("duckId: \"${duckId}\""));
    }

    @Test(description = "2 - Проверка того, что уточка создается")
    @CitrusTest
    public void successfulCreateDuck2(@Optional @CitrusResource TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState){
        duckCreate(runner,"Duck with id = ${duckId} is created");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        validateResponse(runner, yellowDuckService, HttpStatus.OK,"{\n" + " \"color\": \"" + color + "\",\n" +
                " \"height\": " + height + ",\n" +
                " \"id\": " + id + ",\n" +
                " \"material\": \"" + material + "\",\n" +
                " \"sound\": \"" + sound + "\",\n" +
                " \"wingsState\": \"" + wingsState + "\"\n" + "}");
    }

    @Test(description = "3 - Проверка того, что уточка создается")
    @CitrusTest
    public void successfulCreateDuck3(@Optional @CitrusResource TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState){
        duckCreate(runner,"Duck with id = ${duckId} is created");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        validateResponse(runner, yellowDuckService, HttpStatus.OK,"{\n" + " \"color\": \"" + color + "\",\n" +
                " \"height\": " + height + ",\n" +
                " \"id\": " + id + ",\n" +
                " \"material\": \"" + material + "\",\n" +
                " \"sound\": \"" + sound + "\",\n" +
                " \"wingsState\": \"" + wingsState + "\"\n" + "}");
    }

    @Test(description = "4 - Проверка того, что уточка создается")
    @CitrusTest
    public void successfulCreateDuck4(@Optional @CitrusResource TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState) {
        duckCreate(runner,"Duck with id = ${duckId} is created");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        validateResponse(runner, yellowDuckService, HttpStatus.OK,
                "{\n" + " \"color\": \"" + color + "\",\n" +
                " \"height\": " + height + ",\n" +
                " \"id\": " + id + ",\n" +
                " \"material\": \"" + material + "\",\n" +
                " \"sound\": \"" + sound + "\",\n" +
                " \"wingsState\": \"" + wingsState + "\"\n" + "}");
    }

    @Test(description = "Проверка того, что уточка удаляется")
    @CitrusTest
    public void successfulDeleteDuck(@Optional @CitrusResource TestCaseRunner runner) {

        DuckProperties duckProperties = new DuckProperties().id("${duckId}");
        extractId(runner);
        duckDelete(runner, "${duckId}");
        validateResponse(runner, yellowDuckService, HttpStatus.OK,"Duck with id = ${duckId} is deleted" );

    }

    @Test(description = "1 - Проверка того, что уточка обновляется")
    @CitrusTest
    public void successfulUpdateDuck1(@Optional @CitrusResource TestCaseRunner runner) {

        DuckProperties duckProperties = new DuckProperties().color("yellow").height(0.01).id("${duckId}").material("rubber").sound("quack").wingsState("ACTIVE");
        duckUpdate(runner, "Duck with id = ${duckId}");
        validateResponse1(runner, duckProperties.toString());

    }
/*
    @Test(description = "2 - Проверка того, что уточка обновляется")
    @CitrusTest
    public void successfulUpdateDuck2(@Optional @CitrusResource TestCaseRunner runner) {

        duckUpdate(runner, "Duck with id = ${duckId} is updated");
        validateResponse(runner, jsonPath()
                .expression("$.message", "Incorrect sound value"));
    }

    @Test(description = "1 - Проверка того, что получен список всех id уточек")
    @CitrusTest
    public void successfulGetAllIdsDuck1(@Optional @CitrusResource TestCaseRunner runner) {
        duckGetAllIds(runner);
        DuckGetAllIds duckGetAllIds = new DuckGetAllIds().allIds(Arrays.asList("1","2","3"));
        validateResponse(runner, yellowDuckService, HttpStatus.OK, " ");
    }

    @Test(description = "2 - Проверка того, что получен список всех id уточек")
    @CitrusTest
    public void successfulGetAllIdsDuck2(@Optional @CitrusResource TestCaseRunner runner) {
        duckGetAllIds(runner);
        DuckGetAllIds duckGetAllIds = new DuckGetAllIds().allIds(Arrays.asList());
        validateResponse(runner, duckGetAllIds.toString());
    }

    @Test(description = "3 - Проверка того, что получен список всех id уточек")
    @CitrusTest
    public void successfulGetAllIdsDuck3(@Optional @CitrusResource TestCaseRunner runner) {
        duckGetAllIds(runner);
        DuckGetAllIds duckGetAllIds = new DuckGetAllIds().allIds(Arrays.asList("${duckId}"));
        validateResponse(runner, duckGetAllIds.toString());
    }


    @Test(description = "Создание уточки с проверкой данных в БД")
    @CitrusTest
    public void successfulCreateDuck_1(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner,"Duck with id = ${duckId} is created");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        validateDuckInDatabase(runner, "${duckId}", "yellow", "0.3", "metal", "quack", "ACTIVE");
    }
*/
}
