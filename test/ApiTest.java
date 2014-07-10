import controllers.routes;
import models.Node;
import org.junit.Test;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.Helpers;
import play.test.WithApplication;
import static play.test.Helpers.*;

import static org.fest.assertions.Assertions.*;

/**
 * Created by ab on 10/07/14.
 */
public class ApiTest extends WithApplication {

    @Test
    public void testRegisterNode() {
        String name = "test-client-1";

        Result result = callAction(routes.ref.Api.registerNode(name));

        assertThat(status(result)).isEqualTo(OK);

        Node n = Node.findByName(name);

        assertThat(n).isNotNull();
        assertThat(n.name).isEqualTo(name);
    }

    @Test
    public void testRegisterDuplicateNode() {
        String name = "test-client-1";

        Result result = callAction(routes.ref.Api.registerNode(name));

        assertThat(status(result)).isEqualTo(OK);

        result = callAction(routes.ref.Api.registerNode(name));

        assertThat(status(result)).isEqualTo(BAD_REQUEST);
    }

    @Override
    protected FakeApplication provideFakeApplication() {
        return Helpers.fakeApplication(Helpers.inMemoryDatabase());
    }

}
