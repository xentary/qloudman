package models;

import org.joda.time.DateTime;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by ab on 10/07/14.
 */
@Entity
public class Command extends Model {

    @Id
    public String id;

    @ManyToOne
    public Node node;

    public DateTime created;

    public String message;

    public String result;
    public String returnValue;

    public static Finder<String, Command> find = new Finder<>(String.class, Command.class);
}
