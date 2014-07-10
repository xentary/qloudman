package controllers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import models.Command;
import models.Node;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.UUID;

/**
 * Created by ab on 10/07/14.
 */
@Security.Authenticated(Secured.class)
public class Sender extends Controller {

    public static Result index(String node, String command) throws Exception {
        String QUEUE_NAME = "node-" + node;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "self-update";
        if (StringUtils.equalsIgnoreCase(command, "update")) {
            message = "system-update";
        }


        Node agent = Node.findByName(node);
        Command command1 = new Command();
        command1.id = UUID.randomUUID().toString();
        command1.node = agent;
        command1.created = new DateTime();
        command1.message = message;
        command1.save();

        message += "|" + command1.id;

        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "' to " + QUEUE_NAME);



        return redirect("/");
    }

}
