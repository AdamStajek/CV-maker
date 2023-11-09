package cv;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Section {
    String title;
    List<Paragraph> paragraphs = new ArrayList<>();

    Section(String title){
        this.title = title;
    }

    Section setTitle(String title){
        this.title = title;
        return this;
    }
    Section addParagraph(String paragraphText){
        Paragraph p = new Paragraph(paragraphText);
        paragraphs.add(p);
        return this;
    }
    Section addParagraph(Paragraph p){
        paragraphs.add(p);
        return this;
    }
    void writeHTML(PrintStream out){
        out.print("<section>");
        out.printf("<h2>%s</h2>", this.title);
        for(Paragraph p: paragraphs){
            p.writeHTML(out);
        }
        out.print("</section>");
    }
}
