package cv;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class UnorderedList{
    List<ListItem> listItems = new ArrayList<>();

    void addItem(String text){
        this.listItems.add(new ListItem(text));
    }

    void writeHTML(PrintStream out){
        out.print("<ul>");
        for(ListItem el: listItems){
            el.writeHTML(out);
        }
        out.print("</ul>");
    }
}
