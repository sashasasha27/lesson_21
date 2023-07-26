package autotests.tests;

import autotests.EndpointConfig;
import com.consol.citrus.TestCaseRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.net.http.HttpClient;

import static com.consol.citrus.http.actions.HttpActionBuilder.http;

@ContextConfiguration(classes = {EndpointConfig.class})
public class BaseTest {

    @Autowired
    protected HttpClient yellowDuckService;

    protected void sendGetRequest(TestCaseRunner runner, HttpClient URL, String path, String queName, String queValue) {
        runner.$(http()
                .client(URL.toString())
                .send()
                .get(path)
                .queryParam(queName,queValue));
    }


}