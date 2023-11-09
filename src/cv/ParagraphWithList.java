package cv;

import java.io.PrintStream;

public class ParagraphWithList extends Paragraph{
    UnorderedList list = new UnorderedList();

    ParagraphWithList addListItem(String text){
        list.addItem(text);
        return this;
    }

    ParagraphWithList setContent(String Content){
        this.content = Content;
        return this;
    }


    void writeHTML(PrintStream out){
        out.print("<p>");
        out.printf("%s", this.content);
        list.writeHTML(out);
        out.print("</p>");
    }

}
