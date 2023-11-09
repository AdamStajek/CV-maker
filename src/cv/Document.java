package cv;
import com.google.gson.*;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.io.PrintStream;
import java.util.List;
import java.util.ArrayList;
public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>();

    Document(String title) {
        this.title = title;
    }

    Document setTitle(String title) {
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl) {
        this.photo = new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle) {
        Section s = new Section(sectionTitle);
        sections.add(s);
        return s;
    }

    Document addSection(Section s) {
        sections.add(s);
        return this;
    }


    void writeHTML(PrintStream out) {
        out.printf("<!DOCTYPE html> <html> <body> <header> <h1>%s</h1> </header>", this.title);

        photo.writeHTML(out);
        for (Section s : sections) {
            s.writeHTML(out);
        }
        out.print("</body>\n </html>");
    }

    String toJson() {
        RuntimeTypeAdapterFactory<Paragraph> adapter =
                RuntimeTypeAdapterFactory
                        .of(Paragraph.class)
                        .registerSubtype(Paragraph.class)
                        .registerSubtype(ParagraphWithList.class);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapter).setPrettyPrinting().create();
        return gson.toJson(this);
    }

    Document fromJson(String jsonString) {
        RuntimeTypeAdapterFactory<Paragraph> adapter =
                RuntimeTypeAdapterFactory
                        .of(Paragraph.class)
                        .registerSubtype(Paragraph.class)
                        .registerSubtype(ParagraphWithList.class);
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapter).setPrettyPrinting().create();
        return gson.fromJson(jsonString, Document.class);
    }

}
