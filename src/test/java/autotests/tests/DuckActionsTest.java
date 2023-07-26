package autotests.tests;

import autotests.clients.DuckActionsClient;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.message.MessageType;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.consol.citrus.actions.EchoAction.Builder.echo;
import static com.consol.citrus.dsl.MessageSupport.MessageBodySupport.fromBody;
import static com.consol.citrus.dsl.MessageSupport.MessageHeaderSupport.fromHeaders;
import static com.consol.citrus.http.actions.HttpActionBuilder.http;

public class DuckActionsTest extends DuckActionsClient {
    @Test(description = "1 - Проверка того, что уточка поплыла")
    @CitrusTest
    public void successfulSwim(@Optional @CitrusResource TestCaseRunner runner){
        duckCreate(runner,"red","1", "1","rubber","quack","FIXED");
        duckSwim(runner, "1");
        validateResponse(runner, "{\n"+"\"message\":\"I'm swimming\"\n"+"}");
        runner.$(echo("test variables: \"${errorMessage}\" and \"${type}\""));
    }
    public void extractDataFromResponse(TestCaseRunner runner) {
        runner.$(http().client(yellowDuckService)
            .receive()
            .response(HttpStatus.OK)
            .message()
            .type(MessageType.JSON)
            .extract(fromBody().expression("$.message", "errorMessage"))
            .extract(fromHeaders().header("contentType", "type")));
    }

    @Test(description = "2 - Проверка того, что уточка поплыла")
    @CitrusTest
    public void successfulSwim2(@Optional @CitrusResource TestCaseRunner runner){
        duckCreate(runner,"red","1", "3","rubber","quack","FIXED");
        duckSwim(runner, "3");
        validateResponse(runner, "{\n"+"\"message\":\"I'm swimming\"\n"+"}");
        runner.$(echo("test variables: \"${errorMessage}\" and \"${type}\""));
    }

    @Test(description = "3 - Проверка того, что уточка поплыла")
    @CitrusTest
    public void successfulSwim3(@Optional @CitrusResource TestCaseRunner runner){
        duckCreate(runner,"red","1", "4","rubber","quack","FIXED");
        duckSwim(runner, "3");
        validateResponse(runner, "{\n"+"\"message\":\"I'm swimming\"\n"+"}");
        runner.$(echo("test variables: \"${errorMessage}\" and \"${type}\""));
    }

    @Test(description = "1 - Проверка того, как отображаются свойства уточки")
    @CitrusTest
    public void successfulProperties1(@Optional @CitrusResource TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState) {
        duckCreate(runner,"yellow","0", "1","rubber","quack","ACTIVE");
        duckProperties(runner, "1");
        validateResponse(runner, "{\n" + " \"color\": \"" + color + "\",\n" +
                " \"height\": " + height + ",\n" +
                " \"id\": " + id + ",\n" +
                " \"material\": \"" + material + "\",\n" +
                " \"sound\": \"" + sound + "\",\n" +
                " \"wingsState\": \"" + wingsState + "\"\n" + "}");
    }

    @Test(description = "2 - Проверка того, как отображаются свойства уточки")
    @CitrusTest
    public void successfulProperties2(@Optional @CitrusResource TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState) {
        duckCreate(runner,"yellow","3", "2","rubber","quack","ACTIVE");
        duckProperties(runner, "2");
        validateResponse(runner, "{\n" + " \"color\": \"" + color + "\",\n" +
                " \"height\": " + height + ",\n" +
                " \"id\": " + id + ",\n" +
                " \"material\": \"" + material + "\",\n" +
                " \"sound\": \"" + sound + "\",\n" +
                " \"wingsState\": \"" + wingsState + "\"\n" + "}");
    }

    @Test(description = "3 - Проверка того, как отображаются свойства уточки")
    @CitrusTest
    public void successfulProperties3(@Optional @CitrusResource TestCaseRunner runner, String color, String height, String id, String material, String sound, String wingsState) {
        duckCreate(runner,"red","10", "3","rubber","quack","FIXED");
        duckProperties(runner, "3");
        validateResponse(runner, "{\n" + " \"color\": \"" + color + "\",\n" +
                " \"height\": " + height + ",\n" +
                " \"id\": " + id + ",\n" +
                " \"material\": \"" + material + "\",\n" +
                " \"sound\": \"" + sound + "\",\n" +
                " \"wingsState\": \"" + wingsState + "\"\n" + "}");
    }
    @Test(description = "1 - Проверка того, что уточка полетела")
    @CitrusTest
    public void successfulFly1(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner,"red","1", "1","rubber","quack","ACTIVE");
        duckFly(runner, "1");
        validateResponse(runner, "{\n"+"\"message\":\"I'm flying\"\n"+"}");
        runner.$(echo("test variables: \"${errorMessage}\" and \"${type}\""));
    }

    @Test(description = "2 - Проверка того, что уточка полетела")
    @CitrusTest
    public void successfulFly2(@Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner,"red","1", "2","rubber","quack","FIXED");
        duckFly(runner, "2");
        validateResponse(runner, "{\n"+"\"message\":\"I can't fly\"\n"+"}");
        runner.$(echo("test variables: \"${errorMessage}\" and \"${type}\""));
    }

    @Test(description = "1 - Проверка  того, что уточка крякает")
    @CitrusTest
    public void successfulQuack1(@Optional @CitrusResource TestCaseRunner runner, String sound) {
        duckCreate(runner,"red","1", "1","rubber","quack","FIXED");
        duckQuack(runner, "1", "1","1");
        validateResponse(runner, "{\n"+"\"sound\":\"quack\"\n"+"}");
    }

    @Test(description = "2 - Проверка  того, что уточка крякает")
    @CitrusTest
    public void successfulQuack2(@Optional @CitrusResource TestCaseRunner runner, String sound) {
        duckCreate(runner,"red","1", "1","rubber","quack","FIXED");
        duckQuack(runner, "1","5","1");
        validateResponse(runner, "{\n"+"\"sound\":\"quack, quack, quack, quack, quack\"\n"+"}");
    }

    @Test(description = "3 - Проверка  того, что уточка крякает")
    @CitrusTest
    public void successfulQuack3(@Optional @CitrusResource TestCaseRunner runner, String sound) {
        duckCreate(runner,"red","1", "1","rubber","quack","FIXED");
        duckQuack(runner, "1","1","5");
        validateResponse(runner, "{\n"+"\"sound\":\"quack-quack-quack-quack-quack\"\n"+"}");
    }

    @Test(description = "4 - Проверка  того, что уточка крякает")
    @CitrusTest
    public void successfulQuack4(@Optional @CitrusResource TestCaseRunner runner, String sound) {
        duckCreate(runner,"red","1", "2","rubber","quack","FIXED");
        duckQuack(runner, "2","1","1");
        validateResponse(runner, "{\n"+"\"sound\":\"@ignore@\"\n"+"}");
    }

    @Test(description = "5 - Проверка  того, что уточка крякает")
    @CitrusTest
    public void successfulQuack5(@Optional @CitrusResource TestCaseRunner runner, String sound) {
        duckCreate(runner,"red","1", "2","rubber","quack","FIXED");
        duckQuack(runner, "2","1","1");
        validateResponse(runner, "{\n"+ sound +":\"quack\"\n"+"}");
    }

}
