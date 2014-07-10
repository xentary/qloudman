package controllers;

import models.Node;
import play.mvc.Result;

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

}
