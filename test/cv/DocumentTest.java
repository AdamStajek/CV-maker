package cv;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

class DocumentTest {

    @org.junit.jupiter.api.Test
    void writeHTML(){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        Document a = new Document("Jan Kowalski");
        a.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph(
                        new ParagraphWithList().setContent("Kursy")
                                .addListItem("Języka Hiszpańskiego")
                                .addListItem("Szydełkowania"));
        a.setPhoto("https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Calico_tabby_cat_-_Savannah.jpg/1200px-Calico_tabby_cat_-_Savannah.jpg");
        a.writeHTML(ps);


        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assertTrue(result.contains("Przedszkole"));
        assertTrue(result.contains("/h2>"));
        assertTrue(result.contains("</section>"));
        assertTrue(result.contains("<section"));
        assertTrue(result.contains("<p"));
        assertTrue(result.contains("Kursy"));
        assertTrue(result.contains("/p>"));
        assertTrue(result.contains("</ul>"));
        assertTrue(result.contains("</li>"));
        assertTrue(result.contains("<header>"));
        assertTrue(result.contains("Jan Kowalski"));
        assertTrue(result.contains("<img"));
        assertTrue(result.contains("/>"));
        assertTrue(result.contains("src="));


    }

    @org.junit.jupiter.api.Test
    void fromJson(){
        Document cv = new Document("Jana Kowalski - CV");
        cv.setPhoto("https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Calico_tabby_cat_-_Savannah.jpg/1200px-Calico_tabby_cat_-_Savannah.jpg");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph(
                        new ParagraphWithList().setContent("Kursy")
                                .addListItem("Pierwszej pomocy")
                                .addListItem("Szydełkowania")
                );
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Znane technologie")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java"));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        cv.writeHTML(ps);
        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream os2 = new ByteArrayOutputStream();
        PrintStream ps2 = new PrintStream(os2);
        cv.fromJson(cv.toJson()).writeHTML(ps2);
        String result2 = null;
        try {
            result2 = os2.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.print(result);
        assertEquals(result, result2);
        assertTrue(result2.contains("Pierwszej pomocy"));
    }
}