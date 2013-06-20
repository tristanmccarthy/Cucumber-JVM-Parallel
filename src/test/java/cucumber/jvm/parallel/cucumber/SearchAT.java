package cucumber.jvm.parallel.cucumber;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(features = "target/test-classes/cucumber/examples/", tags = {"@search"}, format = {"html:target/cucumber-report/search"})
public class SearchAT {
}
