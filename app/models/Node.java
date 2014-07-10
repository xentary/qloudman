package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ab on 10/07/14.
 */
@Entity
public class Node extends Model {

    @Id
    public Long id;

    public String name;


    public static Finder<Long, Node> find = new Finder<Long, Node>(Long.class, Node.class);

    public static Node findByName(String name) {
        return find.where().eq("name", name).findUnique();
    }
}
