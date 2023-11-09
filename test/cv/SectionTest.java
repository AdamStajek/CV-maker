package cv;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

class SectionTest {

    @org.junit.jupiter.api.Test
    void writeHTML(){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new Section("edukacja").addParagraph(new Paragraph("agh")).writeHTML(ps);
        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assertTrue(result.contains("edukacja"));
        assertTrue(result.contains("/h2>"));
        assertTrue(result.contains("</section>"));
        assertTrue(result.contains("<section"));
        assertTrue(result.contains("agh"));
        assertTrue(result.contains("<p"));


    }
}