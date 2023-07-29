package autotests.tests;

import autotests.clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.actions.EchoAction.Builder.echo;
import static com.consol.citrus.actions.ExecuteSQLAction.Builder.sql;
import static com.consol.citrus.container.FinallySequence.Builder.doFinally;


public class DuckActionsTest extends DuckActionsClient {

    @Test(description = "1 - Проверка того, что уточка поплыла")
    @CitrusTest
    public void successfulSwim(@Optional @CitrusResource TestCaseRunner runner){

        duckCreate(runner,"red","1.0","rubber","quack","FIXED");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        duckSwim(runner, "${duckId}");
        validateResponse(runner, yellowDuckService,HttpStatus.OK,"{\n"+"\"message\":\"I'm swimming\"\n"+"}");

    }

    @Test(description = "2 - Проверка того, что уточка поплыла")
    @CitrusTest
    public void successfulSwim2(@Optional @CitrusResource TestCaseRunner runner){
        duckCreate(runner,"red","1.0","rubber","quack","FIXED");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        duckSwim(runner, "${duckId}");
        validateResponse(runner, yellowDuckService,HttpStatus.OK, "{\n"+"\"message\":\"I'm swimming\"\n"+"}");
    }

    @Test(description = "3 - Проверка того, что уточка поплыла")
    @CitrusTest
    public void successfulSwim3(@Optional @CitrusResource TestCaseRunner runner){
        duckCreate(runner,"red","1.0","rubber","quack","FIXED");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        duckSwim(runner, "${duckId}");
        validateResponse(runner, yellowDuckService,HttpStatus.OK,"{\n"+"\"message\":\"I'm swimming\"\n"+"}");

    }

    @Test(description = "1 - Проверка того, как отображаются свойства уточки")
    @CitrusTest
    public void successfulProperties1(@Optional @CitrusResource TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState) {
        duckCreate(runner,"yellow","0.0","rubber","quack","ACTIVE");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        duckProperties(runner, "${duckId}");
        validateResponse(runner, yellowDuckService,HttpStatus.OK,"{\n" + " \"color\": \"" + color + "\",\n" +
                " \"height\": " + height + ",\n" +
                " \"id\": " + id + ",\n" +
                " \"material\": \"" + material + "\",\n" +
                " \"sound\": \"" + sound + "\",\n" +
                " \"wingsState\": \"" + wingsState + "\"\n" + "}");
    }

    @Test(description = "2 - Проверка того, как отображаются свойства уточки")
    @CitrusTest
    public void successfulProperties2(@Optional @CitrusResource TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState) {
        duckCreate(runner,"yellow","3.0", "rubber","quack","ACTIVE");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        duckProperties(runner, "${duckId}");
        validateResponse(runner, yellowDuckService, HttpStatus.OK,"{\n" + " \"color\": \"" + color + "\",\n" +
                " \"height\": " + height + ",\n" +
                " \"id\": " + id + ",\n" +
                " \"material\": \"" + material + "\",\n" +
                " \"sound\": \"" + sound + "\",\n" +
                " \"wingsState\": \"" + wingsState + "\"\n" + "}");
    }

    @Test(description = "3 - Проверка того, как отображаются свойства уточки")
    @CitrusTest
    public void successfulProperties3(@Optional @CitrusResource TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState) {
        duckCreate(runner,"red","10.0", "rubber","quack","FIXED");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        duckProperties(runner, "${duckId}");
        validateResponse(runner, yellowDuckService,HttpStatus.OK,"{\n" + " \"color\": \"" + color + "\",\n" +
                " \"height\": " + height + ",\n" +
                " \"id\": " + id + ",\n" +
                " \"material\": " + material + "\",\n" +
                " \"sound\": " + sound + "\",\n" +
                " \"wingsState\": " + wingsState + "\"\n" + "}");
    }
    @Test(description = "1 - Проверка того, что уточка полетела")
    @CitrusTest
    public void successfulFly1(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner,"red","1.0", "rubber","quack","ACTIVE");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        duckFly(runner, "${duckId}");
        validateResponse(runner, yellowDuckService, HttpStatus.OK,"{\n"+"\"message\":\"I'm flying\"\n"+"}");
    }

    @Test(description = "2 - Проверка того, что уточка полетела")
    @CitrusTest
    public void successfulFly2(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner,"red","1.0", "rubber","quack","FIXED");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        duckFly(runner, "${duckId}");
        validateResponse(runner, yellowDuckService, HttpStatus.OK,"{\n"+"\"message\":\"I can't fly\"\n"+"}");
    }

    @Test(description = "1 - Проверка  того, что уточка крякает")
    @CitrusTest
    public void successfulQuack1(@Optional @CitrusResource TestCaseRunner runner, String sound) {
        duckCreate(runner,"red","1.0", "rubber","quack","FIXED");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        duckQuack(runner, "${duckId}", "1","1");
        validateResponse(runner, yellowDuckService, HttpStatus.OK,"{\n"+"\"sound\":\"quack\"\n"+"}");
    }

    @Test(description = "2 - Проверка  того, что уточка крякает")
    @CitrusTest
    public void successfulQuack2(@Optional @CitrusResource TestCaseRunner runner, String sound) {
        duckCreate(runner,"red","1.0", "rubber","quack","FIXED");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        duckQuack(runner, "${duckId}","5","1");
        validateResponse(runner, yellowDuckService, HttpStatus.OK,"{\n"+"\"sound\":\"quack, quack, quack, quack, quack\"\n"+"}");
    }

    @Test(description = "3 - Проверка  того, что уточка крякает")
    @CitrusTest
    public void successfulQuack3(@Optional @CitrusResource TestCaseRunner runner, String sound) {
        duckCreate(runner,"red","1.0", "rubber","quack","FIXED");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        duckQuack(runner, "${duckId}","1","5");
        validateResponse(runner, yellowDuckService, HttpStatus.OK,"{\n"+"\"sound\":\"quack-quack-quack-quack-quack\"\n"+"}");
    }

    @Test(description = "4 - Проверка  того, что уточка крякает")
    @CitrusTest
    public void successfulQuack4(@Optional @CitrusResource TestCaseRunner runner, String sound) {
        duckCreate(runner,"red","1.0", "rubber","quack","FIXED");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        duckQuack(runner, "${duckId}","1","1");
        validateResponse(runner, yellowDuckService, HttpStatus.OK,"{\n"+"\"sound\":\"@ignore@\"\n"+"}");
    }

    @Test(description = "5 - Проверка  того, что уточка крякает")
    @CitrusTest
    public void successfulQuack5(@Optional @CitrusResource TestCaseRunner runner, String sound) {
        duckCreate(runner,"red","1.0", "rubber","quack","FIXED");
        extractId(runner);
        runner.$(echo("duckId: \"${duckId}\""));
        duckQuack(runner, "${duckId}","1","1");
        validateResponse(runner, yellowDuckService, HttpStatus.OK,"{\n"+ sound +":\"quack\"\n"+"}");
    }

    @Test(description = "Проверка того, что уточка летит. БД")
    @CitrusTest
    public void successfulDuckFly_1(@Optional @CitrusResource TestCaseRunner runner) {
        runner.variable("duckId","${duckId}");
        runner.$(doFinally().actions(runner.$(sql(testDb)
                .statement("DELETE FROM DUCK WHERE ID=${duckId}"))));
        databaseUpdate(runner,"insert into DUCK (id, color, height, material, sound, wings_state)\n" +
                "values (${duckId}, 'orange', 3.0, 'cheese', 'hrum','ACTIVE');");
        duckFly(runner,"${duckId}");
        validateResponseResources(runner, "getDuckPropertiesTest/actionsYellowDuck.json");
    }

}
