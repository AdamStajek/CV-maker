package cv;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

class ParagraphWithListTest {

    @org.junit.jupiter.api.Test
    void writeHTML(){
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new ParagraphWithList().setContent("kursy").addListItem("Java")
                .addListItem("C").writeHTML(ps);
        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assertTrue(result.contains("kursy"));
        assertTrue(result.contains("/p>"));
        assertTrue(result.contains("</ul>"));
        assertTrue(result.contains("</li>"));


    }
}
