import static org.assertj.core.api.Assertions.assertThat;

import app.Application;
import app.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes= Application.class)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void indexRequestShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Welcome");
    }

    @Test
    public void listLearningOutcomesRequestShouldReturnList() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/listLearningOutcomes",
                String.class)).contains("Learning Outcomes");
    }

    @Test
    public void addLearningOutcomeRequestShouldReturnForm() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/addLearningOutcome",
                String.class)).contains("Add Learning Outcome:");
    }

    @Test
    public void listCategoriesRequestShouldReturnList() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/listCategories",
                String.class)).contains("Pick Category to filter Learning Outcomes:");
    }

    @Test
    public void listLearningOutcomesByCategoryRequestShouldReturnList() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/listLearningOutcomesByCategory?id=1",
                String.class)).contains("Learning Outcomes");
    }
}