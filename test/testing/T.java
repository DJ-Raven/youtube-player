package testing;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import com.raven.youtube.service.YoutubeService;

public class T {

    public static void main(String[] args) {

        try {
            for (String v : YoutubeService.getInstance().searchSuggestion("java, khmer")) {
                System.out.println(v);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
