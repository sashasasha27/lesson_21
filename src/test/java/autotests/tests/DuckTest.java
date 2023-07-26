package autotests.tests;

import autotests.clients.DuckCRUDClient;
import autotests.payloads.DuckGetAllIds;
import autotests.payloads.DuckProperties;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.consol.citrus.actions.EchoAction.Builder.echo;
import static com.consol.citrus.dsl.JsonPathSupport.jsonPath;

public class DuckTest extends DuckCRUDClient {
    @Test(description = "1 - Проверка того, что уточка создается")
    @CitrusTest
    public void successfulCreateDuck1(@Optional @CitrusResource TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState) {
        duckCreate(runner,"yellow","100", "1","rubber","quack","ACTIVE");
        validateResponse(runner,"{\n" + " \"color\": \"" + color + "\",\n" +
                " \"height\": " + height + ",\n" +
                " \"id\": " + id + ",\n" +
                " \"material\": \"" + material + "\",\n" +
                " \"sound\": \"" + sound + "\",\n" +
                " \"wingsState\": \"" + wingsState + "\"\n" + "}");
        runner.$(echo("test variables: \"${errorMessage}\" and \"${type}\""));
    }

    @Test(description = "2 - Проверка того, что уточка создается")
    @CitrusTest
    public void successfulCreateDuck2(@Optional @CitrusResource TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState){
        duckCreate(runner,"yellow","1", "2","rubber","quack","ACTIVE");
        validateResponse(runner,"{\n" + " \"color\": \"" + color + "\",\n" +
                " \"height\": " + height + ",\n" +
                " \"id\": " + id + ",\n" +
                " \"material\": \"" + material + "\",\n" +
                " \"sound\": \"" + sound + "\",\n" +
                " \"wingsState\": \"" + wingsState + "\"\n" + "}");
    }


    @Test(description = "3 - Проверка того, что уточка создается")
    @CitrusTest
    public void successfulCreateDuck3(@Optional @CitrusResource TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState){
        duckCreate(runner,"yellow","100", "3","rubber","quack","FIXED");
        validateResponse(runner,"{\n" + " \"color\": \"" + color + "\",\n" +
                " \"height\": " + height + ",\n" +
                " \"id\": " + id + ",\n" +
                " \"material\": \"" + material + "\",\n" +
                " \"sound\": \"" + sound + "\",\n" +
                " \"wingsState\": \"" + wingsState + "\"\n" + "}");
    }

    @Test(description = "4 - Проверка того, что уточка создается")
    @CitrusTest
    public void successfulCreateDuck4(@Optional @CitrusResource TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState) {
        duckCreate(runner,"yellow","100", "1","rubber","quack","FIXED");
        validateResponse(runner,"{\n" + " \"color\": \"" + color + "\",\n" +
                " \"height\": " + height + ",\n" +
                " \"id\": " + id + ",\n" +
                " \"material\": \"" + material + "\",\n" +
                " \"sound\": \"" + sound + "\",\n" +
                " \"wingsState\": \"" + wingsState + "\"\n" + "}");
    }

    @Test(description = "Проверка того, что уточка удаляется")
    @CitrusTest
    public void successfulDeleteDuck(@Optional @CitrusResource TestCaseRunner runner) {
        DuckProperties duckProperties = new DuckProperties().id("1");
        validateResponse(runner, duckProperties);
    }

    @Test(description = "1 - Проверка того, что уточка обновляется")
    @CitrusTest
    public void successfulUpdateDuck1(@Optional @CitrusResource TestCaseRunner runner) {
        DuckProperties duckProperties = new DuckProperties().color("yellow").height(0.01).id("1").material("rubber").sound("quack").wingsState("ACTIVE");
        validateResponse(runner, duckProperties);

    }

    @Test(description = "2 - Проверка того, что уточка обновляется")
    @CitrusTest
    public void successfulUpdateDuck2(@Optional @CitrusResource TestCaseRunner runner) {
       duckUpdate(runner, "yellow", "0.01", "4", "rubber", "meow", "FIXED");
       validateResponse(runner, jsonPath()
               .expression("$.message", "Incorrect sound value"));
    }

    @Test(description = "1 - Проверка того, что получен список всех id уточек")
    @CitrusTest
    public void successfulGetAllIdsDuck1(@Optional @CitrusResource TestCaseRunner runner) {
        duckGetAllIds(runner);
        DuckGetAllIds duckGetAllIds = new DuckGetAllIds().allIds(Arrays.asList("1","2","3"));
        validateResponse(runner, duckGetAllIds);
    }

    @Test(description = "2 - Проверка того, что получен список всех id уточек")
    @CitrusTest
    public void successfulGetAllIdsDuck2(@Optional @CitrusResource TestCaseRunner runner) {
        duckGetAllIds(runner);
        DuckGetAllIds duckGetAllIds = new DuckGetAllIds().allIds(Arrays.asList());
        validateResponse(runner, duckGetAllIds);
    }

    @Test(description = "3 - Проверка того, что получен список всех id уточек")
    @CitrusTest
    public void successfulGetAllIdsDuck3(@Optional @CitrusResource TestCaseRunner runner) {
        duckGetAllIds(runner);
        DuckGetAllIds duckGetAllIds = new DuckGetAllIds().allIds(Arrays.asList("1"));
        validateResponse(runner, duckGetAllIds);
    }

}
