package cat.udl.eps.softarch.agridatahub.steps;

import cat.udl.eps.softarch.agridatahub.domain.DatasetRequest;
import cat.udl.eps.softarch.agridatahub.repository.DatasetRequestRepository;
import cat.udl.eps.softarch.agridatahub.repository.UserRepository;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.junit.Assert;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class GenerateDatasetRequestStepDefs {

    final StepDefs stepDefs;
    final UserRepository userRepository;
    final DatasetRequestRepository datasetRequestRepository;

    public GenerateDatasetRequestStepDefs(StepDefs stepDefs, UserRepository userRepository, DatasetRequestRepository datasetRequestRepository) {
        this.stepDefs = stepDefs;
        this.userRepository = userRepository;
        this.datasetRequestRepository = datasetRequestRepository;
    }


    @Given("There is registered user with username \"([^\"]*)\"$")
    public void thereIsRegisteredUserWithUsername(String user) {
        Assert.assertTrue("user \""
                        +  user + "\"should exit",
                userRepository.existsById(user));
    }

    @When("I create a new DatasetRequest with value {string}")
    public void iCreateANewDatasetRequestWithValue(Long id) {

        DatasetRequest DatasetRequest = new DatasetRequest();
        DatasetRequest.setId(id);
        Assert.assertFalse("DatasetRequest shouldn'l exit",
                datasetRequestRepository.existsById(id));

    }

    @And("It has been created a new DatasetRequest with value {string}")
    public void itHasBeenCreatedANewDatasetRequestWithValue(Long id) {

        Assert.assertTrue("DatasetRequest should exit",
                datasetRequestRepository.existsById(id));
    }


}