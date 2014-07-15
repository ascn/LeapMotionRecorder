package leapmotionauthentication;

import java.io.IOException;
import com.leapmotion.leap.*;

/**
 *
 * @author achan
 */

public class LeapMotionAuthentication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        // Set timer
        long programTime = 900000; // milliseconds
        long start = System.currentTimeMillis();
        long end = start + programTime;

        // Initialize controller and listener
        AuthenticateListener listener = new AuthenticateListener();
        Controller controller = new Controller();
        controller.addListener(listener);

        while (System.currentTimeMillis() < end) {

        }
        
        controller.removeListener(listener);

    }

}
