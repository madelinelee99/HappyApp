package hu.ait.android.happy.data;

import com.orm.SugarRecord;

import java.io.Serializable;

import hu.ait.android.happy.R;

/**
 * Created by miadeng on 5/21/16.
 */
public class Todo extends SugarRecord implements Serializable {

    public enum ItemType {
        HEALTHY(0, R.drawable.healthy),
        ADVENTURE(1, R.drawable.sports), SOCIAL(2, R.drawable.social);
        private int value;
        private int iconId;

        private ItemType(int value, int iconId) {
            this.value = value;
            this.iconId = iconId;
        }

        public int getValue() {
            return value;
        }

        public int getIconId() {
            return iconId;
        }

        public static ItemType fromInt(int value) {
            for (ItemType p : ItemType.values()) {
                if (p.value == value) {
                    return p;
                }
            }
            return HEALTHY;
        }
    }
    private String Name;
    private String Cat;
    private String Date;
    private String Location;
    private boolean Done;
    private ItemType itemType;


    public Todo() {

    }


    public Todo(String name, String cat, String date, String location, boolean done, ItemType itemType) {

        this.Name = name;
        this.Cat = cat;
        this.Date = date;
        this.Location = location;
        this.Done = done;
        this.itemType = itemType;
    }


    public String getName() {return Name;}

    public void setName(String name) { Name = name; }

    public ItemType getItemType() { return itemType; }

    public void setItemType(ItemType itemType) { this.itemType = itemType; }

    public boolean isDone() { return Done; }

    public void setDone(boolean done) { Done = done; }

    public String getDate() { return Date; }

    public void setDate(String date) {Date = date; }

    public String getLocation() { return Location;}

    public void setLocation(String location) { Location  = location; }

    public String getCat() { return Cat; }

    public void setCat(String cat) { Cat = cat; }
}
