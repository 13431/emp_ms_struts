package regexp;

import org.junit.Test;
import java.util.regex.*;

public class R {

    @Test public void first() {
        boolean matches = Pattern.compile("1\\d{10}").matcher("22334567891").matches();
        boolean matches1 = Pattern.matches("1\\d{10}", "12334567891");
        System.out.println(matches1);
    }

}
