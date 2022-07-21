package nithra.tamil.word.game.solliadi;

import java.util.ArrayList;

/**
 * Created by NITHRA-1 on 11/11/16.
 */

public class Group {

    private String Name;
    private ArrayList<Child> Items;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public ArrayList<Child> getItems() {
        return Items;
    }

    public void setItems(ArrayList<Child> Items) {
        this.Items = Items;
    }

}