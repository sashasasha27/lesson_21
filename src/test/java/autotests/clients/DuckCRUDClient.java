package autotests.clients;

import autotests.tests.BaseTest;
import com.consol.citrus.TestCaseRunner;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.springframework.http.HttpStatus;

public class DuckCRUDClient extends BaseTest {

    @Step("Эндпоинт для создания уточки")
    public void duckCreate(TestCaseRunner runner, Object response) {
        sendPostRequest(runner, yellowDuckService, "/api/duck/create", response);
    }

    @Step("Эндпоинт для удаления уточки")
    public void duckDelete(TestCaseRunner runner, String id){
        sendGetRequest(runner, yellowDuckService, "/api/duck/delete", "id", id);
    }

    @Step("Эндпоинт для получения всех id уточки")
    public void duckGetAllIds(TestCaseRunner runner){
        sendDeleteRequest(runner, yellowDuckService, "/api/duck/getAllIds");
    }

    @Step("Эндпоинт для обновления уточки")
    public void duckUpdate(TestCaseRunner runner, Object response){
        sendPostRequest(runner, yellowDuckService, "/api/duck/update", response);
    }

    @Description("Валидация")
    public void validateResponse1(TestCaseRunner runner, String response){
        validateResponse1(runner,yellowDuckService, HttpStatus.OK, response);
    }



}