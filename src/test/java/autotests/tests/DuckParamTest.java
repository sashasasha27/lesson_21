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

public class DuckParamTest extends DuckCRUDClient {
    DuckProperties duckProperties1 = new DuckProperties().color("yellow").height(0.05).material("rubber").sound("quack").wingsState("ACTIVE");
    DuckProperties duckProperties2 = new DuckProperties().color("green").height(1.0).material("plastic").sound("meow").wingsState("FIXED");
    DuckProperties duckProperties3 = new DuckProperties().color("blue").height(2.0).material("metal").sound("crya").wingsState("FIXED");
    DuckProperties duckProperties4 = new DuckProperties().color("red").height(3.0).material("plastic").sound("meow").wingsState("ACTIVE");
    DuckProperties duckProperties5 = new DuckProperties().color("brown").height(4.0).material("rubber").sound("woof").wingsState("FIXED");


    @Test(dataProvider = "duckList")
    @CitrusTest
    @CitrusParameters({"payload", "response", "runner"})
    public void createDuckList(Object payload, String response, @Optional @CitrusResource TestCaseRunner runner) {
        duckCreate(runner, payload);
        validateResponse1(runner, response);
    }

    @DataProvider(name = "duckList")
    public Object[][] DataProvider() {
        return new Object[][]{
                {duckProperties1, "getDuckPropertiesTest/duckYellowProperties.json", null},
                {duckProperties2, "getDuckPropertiesTest/duckGreenProperties.json", null},
                {duckProperties3, "getDuckPropertiesTest/duckBluePropertis.json", null},
                {duckProperties4, "getDuckPropertiesTest/duckRedProperties.json", null},
                {duckProperties5, "getDuckPropertiesTest/duckBrownProperties.json", null},
        };
    }



}
