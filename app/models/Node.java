package models;

import org.joda.time.DateTime;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by ab on 10/07/14.
 */
@Entity
public class Node extends Model {

    @Id
    public Long id;

    public String name;

    public DateTime lastActivity;

    public static Finder<Long, Node> find = new Finder<Long, Node>(Long.class, Node.class);

    public static Node findByName(String name) {
        return find.where().eq("name", name).findUnique();
    }

    public static List<Node> findAll() {
        return find.order("name").findList();
    }
}
