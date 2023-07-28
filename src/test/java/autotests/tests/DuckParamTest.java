package autotests.tests;

import autotests.clients.DuckCRUDClient;
import autotests.payloads.DuckProperties;
import com.consol.citrus.TestCaseRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.testng.CitrusParameters;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class DuckParamTest extends DuckCRUDClient { //параметризированный тест к 24 лекции
    DuckProperties duckProperties1 = new DuckProperties().color("yellow").height(0.05).id("7").material("rubber").sound("quack").wingsState("ACTIVE");
    DuckProperties duckProperties2 = new DuckProperties().color("green").height(1.0).id("8").material("plastic").sound("meow").wingsState("FIXED");
    DuckProperties duckProperties3 = new DuckProperties().color("blue").height(2.0).id("9").material("metal").sound("crya").wingsState("FIXED");
    DuckProperties duckProperties4 = new DuckProperties().color("red").height(3.0).id("10").material("plastic").sound("meow").wingsState("ACTIVE");
    DuckProperties duckProperties5 = new DuckProperties().color("brown").height(4.0).id("11").material("rubber").sound("woof").wingsState("FIXED");


    @Test(dataProvider = "duckList")
    @CitrusTest
    @CitrusParameters({"color", "height", "id", "material", "sound", "wingsState", "response", "runner"})
    public void successfulDuckCreate( String color, String height, String id, String material, String sound, String wingsState, String response, @Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, "yellow", "0.05","7","rubber","quack","ACTIVE");
        validateResponse(runner, response);
        duckCreate(runner, "green", "1.0","8","plastic","meow","FIXED");
        validateResponse(runner, response);
        duckCreate(runner, "blue", "2.0","9","metal","crya","FIXED");
        validateResponse(runner, response);
        duckCreate(runner, "red", "3.0","10","plastic","meow","ACTIVE");
        validateResponse(runner, response);
        duckCreate(runner, "brown", "4.0","11","rubber","woof","FIXED");
        //не понимаю, как передать в общем случае payload


    }

    @DataProvider(name = "duckList")
    public Object[][] DuckProvider() {
        return new Object[][]{
                {duckProperties1, "getDuckPropertiesTest/duckYellowProperties.json", null},
                {duckProperties2, "getDuckPropertiesTest/duckGreenProperties.json", null},
                {duckProperties3, "getDuckPropertiesTest/duckBlueProperties.json", null},
                {duckProperties4, "getDuckPropertiesTest/duckRedProperties.json", null},
                {duckProperties5, "getDuckPropertiesTest/duckBrownProperties.json", null},
        };
    }



}
