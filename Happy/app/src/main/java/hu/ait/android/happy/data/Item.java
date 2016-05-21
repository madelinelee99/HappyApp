package hu.ait.android.happy.data;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by clarabelitz on 5/22/16.
 */
public class Item extends SugarRecord implements Serializable {

    private String itemToDo;

    public Item(){

    }

    public Item(String itemToDo) {this.itemToDo = itemToDo;}


    public String getItemToDo() {
        return itemToDo;
    }

    public void setItemToDo(String itemToDo) {
        this.itemToDo = itemToDo;
    }
}
