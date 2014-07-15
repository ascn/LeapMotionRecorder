package leapmotionauthentication;

import com.leapmotion.leap.*;
import java.util.Scanner;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author achan
 */

public class AuthenticateListener extends Listener {

	public static String fileName;

        @Override
	public void onInit(Controller controller) {
		System.out.println("Initialized");
		Scanner in = new Scanner(System.in);

		System.out.println("Input file name: ");
		fileName = in.nextLine();

        CSVWriter writer = null;
            try {
                writer = new CSVWriter(new FileWriter(fileName), ',', CSVWriter.NO_QUOTE_CHARACTER);
            } catch (IOException ex) {
                Logger.getLogger(AuthenticateListener.class.getName()).log(Level.SEVERE, null, ex);
            }

        String headerStr = "timestamp # numHands # wid_RH # pitch_RH # yaw_RH"
                + " # roll_RH # vel_RH # grab_RH # pinch_RH # wid_RA # wid_LH"
                + " # pitch_LH # yaw_LH # roll_LH # vel_LH # grab_LH # pinch_LH"
                + " # wid_LA # len_Rfing1 # len_Rfing2 # len_Rfing3 # len_Rfing4"
                + " # len_Rfing5 # wid_Rfing1 # wid_Rfing2 # wid_Rfing3"
                + " # wid_Rfing4 # wid_Rfing5 # vel_Rfing1 # vel_Rfing2"
                + " # vel_Rfing3 # vel_Rfing4 # vel_Rfing5 # len_Lfing1"
                + " # len_Lfing2 # len_Lfing3 # len_Lfing4 # len_Lfing5"
                + " # wid_Lfing1 # wid_Lfing2 # wid_Lfing3 # wid_Lfing4"
                + " # wid_Lfing5 # vel_Lfing1 # vel_Lfing2 # vel_Lfing3"
                + " # vel_Lfing4 # vel_Lfing5 # len_RBmet1 # len_RBmet2"
                + " # len_RBmet3 # len_RBmet4 # len_RBmet5 # len_RBprox1"
                + " # len_RBprox2 # len_RBprox3 # len_RBprox4 # len_RBprox5"
                + " # len_RBint1 # len_RBint2 # len_RBint3 # len_RBint4"
                + " # len_RBint5 # len_RBdist1 # len_RBdist2 # len_RBdist3"
                + " # len_RBdist4 # len_RBdist5 # len_LBmet1 # len_LBmet2"
                + " # len_LBmet3 # len_LBmet4 # len_LBmet5 # len_LBprox1"
                + " # len_LBprox2 # len_LBprox3 # len_LBprox4 # len_LBprox5"
                + " # len_LBint1 # len_LBint2 # len_LBint3 # len_LBint4"
                + " # len_LBint5 # len_LBdist1 # len_LBdist2 # len_LBdist3"
                + " # len_LBdist4 # len_LBdist5  # wid_RBmet1 # wid_RBmet2"
                + " # wid_RBmet3 # wid_RBmet4 # wid_RBmet5 # wid_RBprox1"
                + " # wid_RBprox2 # wid_RBprox3 # wid_RBprox4 # wid_RBprox5"
                + " # wid_RBint1 # wid_RBint2 # wid_RBint3 # wid_RBint4"
                + " # wid_RBint5 # wid_RBdist1 # wid_RBdist2 # wid_RBdist3"
                + " # wid_RBdist4 # wid_RBdist5 # wid_LBmet1 # wid_LBmet2"
                + " # wid_LBmet3 # wid_LBmet4 # wid_LBmet5 # wid_LBprox1"
                + " # wid_LBprox2 # wid_LBprox3 # wid_LBprox4 # wid_LBprox5"
                + " # wid_LBint1 # wid_LBint2 # wid_LBint3 # wid_LBint4"
                + " # wid_LBint5 # wid_LBdist1 # wid_LBdist2 # wid_LBdist3"
                + " # wid_LBdist4 # wid_LBdist5 # gest_circRad # gest_circVel"
                + " # gest_swipeDir # gest_swipeVel # gest_KTapDir  # gest_KTime # gest_STapDir # gest_STime ";

        String[] header = headerStr.split(" # ");
        writer.writeNext(header);
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(AuthenticateListener.class.getName()).log(Level.SEVERE, null, ex);
            }

	}

        @Override
	public void onConnect(Controller controller) {
		System.out.println("Connected");
	}

        @Override
	public void onDisconnect(Controller controller) {
		System.out.println("Disconnected");
	}

        @Override
	public void onExit(Controller controller) {
		System.out.println("Exited");
	}
    
        @Override
	public void onFrame(Controller controller) {

            String[] data = new String[136];

            // Get latest frame
            Frame currentFrame = Features.getFrame(controller);

            // For debugging purposes
            System.out.println("Frame ID: " + currentFrame.id());
            System.out.println("Timestamp: " + currentFrame.timestamp());
            System.out.println("numHands: " + currentFrame.hands().count());

            // Retrieve essential data
            Long timestamp        = Features.getTimestamp(controller);
            int numHands          = Features.countHands(controller);
            float[] handWidths    = Features.getHandWidths(controller);
            float[] orientation   = Features.getRotDir(controller);
            float[] handVel       = Features.getHandVel(controller);
            float[] grabStrength  = Features.getGrabStrength(controller);
            float[] pinchStrength = Features.getPinchStrength(controller);
            float[] armWidth      = Features.getArmWidth(controller);
            float[] fingLen       = Features.getFingLen(controller);
            float[] fingWid       = Features.getFingWid(controller);
            float[] fingVel       = Features.getFingVel(controller);
            float[] boneLen       = Features.getBoneLen(controller);
            float[] boneWid       = Features.getBoneWidth(controller);
            float[] gestProp      = Features.getGestProp(controller);

            // Write timestamp to data
            data[0] = String.valueOf(timestamp);
            
            // Write number of hands to data
            data[1] = String.valueOf(numHands);
            
            // Write hand width to data
            data[2] = String.valueOf(handWidths[0]);
            data[10] = String.valueOf(handWidths[1]);
            
            // Write pitch, yaw, and roll to data
            data[3] = String.valueOf(orientation[0]);
            data[4] = String.valueOf(orientation[1]);
            data[5] = String.valueOf(orientation[2]);
            data[11] = String.valueOf(orientation[3]);
            data[12] = String.valueOf(orientation[4]);
            data[13] = String.valueOf(orientation[5]);
            
            // Write hand velocity to data
            data[6] = String.valueOf(handVel[0]);
            data[14] = String.valueOf(handVel[1]);
            
            // Write grab strength to data
            data[7] = String.valueOf(grabStrength[0]);
            data[15] = String.valueOf(grabStrength[1]);
            
            // Write pinch strength to data
            data[8] = String.valueOf(pinchStrength[0]);
            data[16] = String.valueOf(pinchStrength[1]);
            
            // Write arm width to data
            data[9] = String.valueOf(armWidth[0]);
            data[17] = String.valueOf(armWidth[1]);
            
            // Write finger lengths to data
            for (int i = 0; i < 5; i++) {
                data[i + 18] = String.valueOf(fingLen[i]);
            }
            for (int i = 0; i < 5; i++) {
                data[i + 33] = String.valueOf(fingLen[i + 5]);
            }
            
            // Write finger widths to data
            for (int i = 0; i < 5; i++) {
                data[i + 23] = String.valueOf(fingWid[i]);
            }
            for (int i = 0; i < 5; i++) {
                data[i + 38] = String.valueOf(fingWid[i + 5]);
            }
            
            // Write finger velocities to data
            for (int i = 0; i < 5; i++) {
                data[i + 28] = String.valueOf(fingVel[i]);
            }
            for (int i = 0; i < 5; i++) {
                data[i + 43] = String.valueOf(fingVel[i + 5]);
            }
            
            // Write bone lengths to data
            for (int i = 0; i < 20; i++) {
                data[i + 48] = String.valueOf(boneLen[i]);
            }
            for (int i = 0; i < 20; i++) {
                data[i + 68] = String.valueOf(boneLen[i + 20]);
            }
            
            // Write bone widths to data
            for (int i = 0; i < 20; i++) {
                data[i + 88] = String.valueOf(boneWid[i]);
            }
            for (int i = 0; i < 20; i++) {
                data[i + 108] = String.valueOf(boneWid[i + 20]);
            }
            
            // Write gesture info to data
            int type = (int) gestProp[0];
            switch (type) {
                case 0: //circle
                    data[128] = String.valueOf(gestProp[1]);
                    data[129] = String.valueOf(gestProp[2]);
                case 1: //swipe
                    data[130] = String.valueOf(gestProp[1]);
                    data[131] = String.valueOf(gestProp[2]);
                case 2: //screenTap
                    data[132] = String.valueOf(gestProp[1]);
                    data[133] = String.valueOf(gestProp[2]);
                case 3: //keyTap
                    data[134] = String.valueOf(gestProp[1]);
                    data[135] = String.valueOf(gestProp[2]);
            }
            FileWriter mFileWriter = null;
            try {
                mFileWriter = new FileWriter(fileName, true);
            } catch (IOException ex) {
                Logger.getLogger(AuthenticateListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            CSVWriter mCSVWriter = new CSVWriter(mFileWriter);
            mCSVWriter.writeNext(data);
            try {
                mCSVWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(AuthenticateListener.class.getName()).log(Level.SEVERE, null, ex);
            }

	}

}
