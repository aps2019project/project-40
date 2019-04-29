package models;
import org.json.*;

public class Item {
    private String description;
    protected Spell spell;
    public String getInfo(){
        return description;
    }
}
