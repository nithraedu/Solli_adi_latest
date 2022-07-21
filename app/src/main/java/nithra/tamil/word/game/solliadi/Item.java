package nithra.tamil.word.game.solliadi;

/**
 * Created by arunrk on 29/5/17.
 */

public class Item {

    int id;
    String item;
    String count;
    String validity;
    Boolean isSelected;

    public Item() {}

    public Item(int id, String item, String count) {
        this.id = id;
        this.item = item;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

}
