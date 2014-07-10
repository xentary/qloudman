package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Command;
import models.Node;
import org.apache.commons.lang3.StringUtils;
import play.mvc.Result;

import java.util.Map;

/**
 * Created by ab on 10/07/14.
 */
public class Api extends BaseApiController {

    public static Result registerNode(String name) {
        Node node = Node.findByName(name);
        if (node != null) {
            return badRequest("Node already registered");
        }

        node = new Node();
        node.name = name;
        node.save();

        return ok();
    }

    public static Result commandResult() {
        Map<String, String[]> result = request().body().asFormUrlEncoded();


        Command cmd = Command.find.byId(StringUtils.trim(result.get("cmdid")[0]));
        cmd.returnValue = StringUtils.trim(result.get("retval")[0]);
        cmd.result = StringUtils.trim(result.get("result")[0]);
        cmd.update();

        return ok();
    }

}
