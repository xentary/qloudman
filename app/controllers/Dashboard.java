package controllers;

import models.Node;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.dashboard.index;

import java.util.List;

/**
 * User: yesnault
 * Date: 22/01/12
 */
@Security.Authenticated(Secured.class)
public class Dashboard extends Controller {

    public static Result index() {
        List<Node> nodes = Node.find.all();
        User user = User.findByEmail(request().username());

        return ok(index.render(user, nodes));
    }
}
