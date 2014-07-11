package leapmotionauthentication;

import java.io.IOException;
import com.leapmotion.leap.*;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author achan
 */
public class LeapMotionAuthentication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        long end = start + 900000;

        AuthenticateListener listener = new AuthenticateListener();
        Controller controller = new Controller();
        Features.initialize(controller, listener);
        
        controller.removeListener(listener);

        while (System.currentTimeMillis() < end) {

        }

    }

}
