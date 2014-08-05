package leapmotionauthentication;

import com.leapmotion.leap.*;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Features {

    private final static Float nan = Float.NaN;

    // Returns the latest frame
    public static Frame getFrame(Controller controller) {        
        Frame currentFrame = controller.frame();
        return currentFrame;
    }
    
    // Returns the timestamp of the latest frame
    public static Long getTimestamp(Controller controller) {        
        Frame currentFrame = Features.getFrame(controller);
        return currentFrame.timestamp();        
    }
    
    // Returns the number of hands
    public static int countHands(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        return allHands.count();
    }

    // Returns a float array with the width of the palm in millimeters
    // The first element is the right hand
    public static Float[] getHandWidths(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        Float wid;
        Float[] handWidths = {nan, nan};
        for (int i = 0; i < allHands.count(); i++) {
            hand = allHands.get(i);
            wid = hand.palmWidth();
            if (hand.isRight() == true) {
                handWidths[0] = wid;
            }
            else {
                handWidths[1] = wid;
            }
        }
        return handWidths;
    }

    // Returns a float array of the pitch, yaw, and roll in that order
    // First three elements are the right hand
    public static Float[] getRotDir(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        Float pitch, yaw, roll;
        Float[] rotDir = {nan, nan, nan, nan, nan, nan};
        for (int i = 0; i < allHands.count(); i++) {
            hand = allHands.get(i);
            pitch = hand.direction().pitch();
            yaw = hand.direction().yaw();
            roll = hand.palmNormal().roll();
            if (hand.isRight() == true) {
                rotDir[0] = pitch;
                rotDir[1] = yaw;
                rotDir[2] = roll;
            }
            else {
                rotDir[3] = pitch;
                rotDir[4] = yaw;
                rotDir[5] = roll;
            }
        }
        return rotDir;
    }

    // Returns a float array of the speed of the hands in millimeters per second
    // First element is the right hand
    public static Float[] getHandVel(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        Vector vel;
        Float velMag;
        Float[] handVel = {nan, nan};
        for (int i = 0; i < allHands.count(); i++) {
            hand = allHands.get(i);
            vel = hand.palmVelocity();
            velMag = vel.magnitude();
            if (hand.isRight() == true) {
                handVel[0] = velMag;
            }
            else {
                handVel[1] = velMag;
            }
        }
        return handVel;
    }

    // Returns a float array of the grab strength
    // First element is the right hand
    public static Float[] getGrabStrength(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        Float handGrab;
        Float[] grabStrengths = {nan, nan};
        for (int i = 0; i < allHands.count(); i++) {
            hand = allHands.get(i);
            handGrab = hand.grabStrength();
            if (hand.isRight() == true) {
                grabStrengths[0] = handGrab;
            }
            else {
                grabStrengths[1] = handGrab;
            }
        }
        return grabStrengths;
    }

    // Returns a float array of the pinch strength
    // First element is the right hand
    public static Float[] getPinchStrength(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        Float handPinch;
        Float[] pinchStrengths = {nan, nan};
        for (int i = 0; i < allHands.count(); i++) {
            hand = allHands.get(i);
            handPinch = hand.pinchStrength();
            if (hand.isRight() == true) {
                pinchStrengths[0] = handPinch;
            }
            else {
                pinchStrengths[1] = handPinch;
            }
        }
        return pinchStrengths;
    }

    // Returns a float array of the width of the forearm
    // First element is the right arm
    public static Float[] getArmWidth(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        Arm arm;
        Float armWidth;
        Float[] armWidths = {nan, nan};
        for (int i = 0; i < allHands.count(); i++) {
            hand = allHands.get(i);
            arm = hand.arm();
            armWidth = arm.width();
            if (hand.isRight() == true) {
                armWidths[0] = armWidth;
            }
            else {
                armWidths[1] = armWidth;
            }
        }
        return armWidths;
    }

    // Returns a float array of the lengths of each finger
    // First five elements are the right hand
    // The order goes from thumb to pinky
    public static Float[] getFingLen(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        FingerList allFingers;
        Finger finger;
        Float fingerLength;
        Float[] fingLen = {nan, nan, nan, nan, nan, nan, nan, nan, nan, nan};
        for (int i = 0; i < allHands.count(); i++) {
            hand = allHands.get(i);
            if (hand.isRight() == true) {
                allFingers = hand.fingers();
                for (int j = 0; j < allFingers.count(); j++) {
                    finger = allFingers.get(j);
                    fingerLength = finger.length();
                    fingLen[j] = fingerLength;
                }
            }
            else{
                allFingers = hand.fingers();
                for(int j = 0; j < allFingers.count(); j++) {
                    finger = allFingers.get(j);
                    fingerLength = finger.length();
                    fingLen[j + 5] = fingerLength;
                }
            }
        }
        return fingLen;
    }

    // Returns a float array of the widths of each finger
    // First five elements are the right hand
    // The order goes from thumb to pinky
    public static Float[] getFingWid(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        FingerList allFingers;
        Finger finger;
        Float fingerWidth;
        Float[] fingWid = {nan, nan, nan, nan, nan, nan, nan, nan, nan, nan};
        for (int i = 0; i < allHands.count(); i++) {
            hand = allHands.get(i);
            if (hand.isRight() == true) {
                allFingers = hand.fingers();
                for (int j = 0; j < allFingers.count(); j++) {
                    finger = allFingers.get(j);
                    fingerWidth = finger.width();
                    fingWid[j] = fingerWidth;
                }
            }
            else {
                allFingers = hand.fingers();
                for (int j = 0; j < allFingers.count(); j++) {
                    finger = allFingers.get(j);
                    fingerWidth = finger.width();
                    fingWid[j + 5] = fingerWidth;
                }
            }
        }
        return fingWid;
    }

    public static Float[] getFingVel(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        FingerList allFingers;
        Finger finger;
        Vector vel;
        Float velMag;
        Float[] fingVel = {nan, nan, nan, nan, nan, nan, nan, nan, nan, nan};
        for (int i = 0; i < allHands.count(); i++) {
            hand = allHands.get(i);
            if (hand.isRight() == true) {
                allFingers = hand.fingers();
                for (int j = 0; j < allFingers.count(); j++) {
                    finger = allFingers.get(j);
                    vel = finger.tipVelocity();
                    velMag = vel.magnitude();
                    fingVel[j] = velMag;
                }
            }
            else {
                allFingers = hand.fingers();
                for (int j = 0; j < allFingers.count(); j++) {
                    finger = allFingers.get(j);
                    vel = finger.tipVelocity();
                    velMag = vel.magnitude();
                    fingVel[j + 5] = velMag;
                }
            }
        }
        return fingVel;
    }

    public static Float[] getBoneLen(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        FingerList allFingers;
        Finger finger;
        Bone bone;
        Float length;
        Float[] boneLen = {nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan};
        for (int i = 0; i < allHands.count(); i++) {
            hand = allHands.get(i);
            if (hand.isRight() == true) {
                allFingers = hand.fingers();
                for (int j = 0; j < allFingers.count(); j++) {
                    finger = allFingers.get(j);
                    for (Bone.Type boneType : Bone.Type.values()) {
                        bone = finger.bone(boneType);
                        length = bone.length();
                        switch (boneType) {
                            case TYPE_METACARPAL:
                                boneLen[(4 * j)] = length;
                            case TYPE_PROXIMAL:
                                boneLen[(4 * j) + 1] = length;
                            case TYPE_INTERMEDIATE:
                                boneLen[(4 * j) + 2] = length;
                            case TYPE_DISTAL:
                                boneLen[(4 * j) + 3] = length;
                        }
                    }
                }
            }
            else {
                allFingers = hand.fingers();
                for (int j = 0; j < allFingers.count(); j++) {
                    finger = allFingers.get(j);
                    for (Bone.Type boneType : Bone.Type.values()) {
                        bone = finger.bone(boneType);
                        length = bone.length();
                        switch (boneType) {
                            case TYPE_METACARPAL:
                                boneLen[(4 * j) + 20] = length;
                            case TYPE_PROXIMAL:
                                boneLen[(4 * j) + 21] = length;
                            case TYPE_INTERMEDIATE:
                                boneLen[(4 * j) + 22] = length;
                            case TYPE_DISTAL:
                                boneLen[(4 * j) + 23] = length;
                        }
                    }
                }
            }
        }
        return boneLen;
    }

    public static Float[] getBoneWidth(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        FingerList allFingers;
        Finger finger;
        Bone bone;
        Float width;
        Float[] boneWidth = {nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan, nan};
        for (int i = 0; i < allHands.count(); i++) {
            hand = allHands.get(i);
            if (hand.isRight() == true) {
                allFingers = hand.fingers();
                for (int j = 0; j < allFingers.count(); j++) {
                    finger = allFingers.get(j);
                    for (Bone.Type boneType : Bone.Type.values()) {
                        bone = finger.bone(boneType);
                        width = bone.width();
                        switch (boneType) {
                            case TYPE_METACARPAL:
                                boneWidth[(4 * j)] = width;
                            case TYPE_PROXIMAL:
                                boneWidth[(4 * j) + 1] = width;
                            case TYPE_INTERMEDIATE:
                                boneWidth[(4 * j) + 2] = width;
                            case TYPE_DISTAL:
                                boneWidth[(4 * j) + 3] = width;
                        }
                    }
                }
            }
            else {
                allFingers = hand.fingers();
                for (int j = 0; j < allFingers.count(); j++) {
                    finger = allFingers.get(j);
                    for (Bone.Type boneType : Bone.Type.values()) {
                        bone = finger.bone(boneType);
                        width = bone.width();
                        switch (boneType) {
                            case TYPE_METACARPAL:
                                boneWidth[(4 * j) + 20] = width;
                            case TYPE_PROXIMAL:
                                boneWidth[(4 * j) + 21] = width;
                            case TYPE_INTERMEDIATE:
                                boneWidth[(4 * j) + 22] = width;
                            case TYPE_DISTAL:
                                boneWidth[(4 * j) + 23] = width;
                        }
                    }
                }
            }
        }
        return boneWidth;
    }

    public static Float[] getCircProp(Controller controller) {
        Frame currentFrame = controller.frame();
        GestureList allGestures = currentFrame.gestures();
        Float[] circProp = {nan, nan};

        if (allGestures.count() > 0) {
            Gesture gesture;
            Float radius, elapTime;
            for (int i = 0; i < allGestures.count(); i++) {
                gesture = allGestures.get(i);
                switch (gesture.type()) {
                    case TYPE_CIRCLE:
                        CircleGesture circle = new CircleGesture(gesture);
                        radius = circle.radius();
                        elapTime = circle.durationSeconds();
                        circProp[0] = radius;
                        circProp[1] = elapTime;
                        break;
                }
            }
        }
        return circProp;
    }

    public static Float[] getSwipeProp(Controller controller) {
        Frame currentFrame = controller.frame();
        GestureList allGestures = currentFrame.gestures();
        Float[] swipeProp = {nan, nan};

        if (allGestures.count() > 0) {
            Gesture gesture;
            Float direction, elapTime;
            for (int i = 0; i < allGestures.count(); i++) {
                gesture = allGestures.get(i);
                switch (gesture.type()) {
                    case TYPE_SWIPE:
                        SwipeGesture swipe = new SwipeGesture(gesture);
                        Vector swipeDirection = swipe.direction();
                        direction = swipeDirection.magnitude();
                        elapTime = (float) swipe.duration();
                        swipeProp[0] = direction;
                        swipeProp[1] = elapTime;
                        break;
                }
            }
        }
        return swipeProp;
    }

    public static Float[] getScreenTapProp(Controller controller) {
        Frame currentFrame = controller.frame();
        GestureList allGestures = currentFrame.gestures();
        Float[] screenProp = {nan, nan};

        if (allGestures.count() > 0) {
            Gesture gesture;
            Float direction, elapTime;
            for (int i = 0; i < allGestures.count(); i++) {
                gesture = allGestures.get(i);
                switch (gesture.type()) {
                    case TYPE_SCREEN_TAP:
                        ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
                        Vector tapDirection = screenTap.direction();
                        direction = tapDirection.magnitude();
                        elapTime = (float) screenTap.duration();
                        screenProp[0] = direction;
                        screenProp[1] = elapTime;
                        break;
                }
            }
        }
        return screenProp;
    }

    public static Float[] getKeyTapProp(Controller controller) {
        Frame currentFrame = controller.frame();
        GestureList allGestures = currentFrame.gestures();
        Float[] keyProp = {nan, nan};

        if(allGestures.count() > 0) {
            Gesture gesture;
            Float direction, elapTime;
            for (int i = 0; i < allGestures.count(); i++) {
                gesture = allGestures.get(i);
                switch (gesture.type()) {
                    case TYPE_KEY_TAP:
                        KeyTapGesture keyTap = new KeyTapGesture(gesture);
                        Vector tapDirection = keyTap.direction();
                        direction = tapDirection.magnitude();
                        elapTime = (float) keyTap.duration();
                        keyProp[0] = direction;
                        keyProp[1] = elapTime;
                        break;
                }
            }
        }
        return keyProp;
    }

    public static Float[] getHandConfidence(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        Float handConfidence;
        Float[] handCon = {nan, nan};
        for (int i = 0; i < allHands.count(); i++) {
            hand = allHands.get(i);
            handConfidence = hand.confidence();
            if (hand.isRight() == true) {
                handCon[0] = handConfidence;
            }
            else {
                handCon[1] = handConfidence;
            }
        }
        return handCon;
    }

    public static void write(Controller controller, String fileName) {

        String[] data = new String[138];

        Frame currentFrame = getFrame(controller);

        Long timestamp        = getTimestamp(controller);
        int numHands          = countHands(controller);
        Float[] handWidths    = getHandWidths(controller);
        Float[] orientation   = getRotDir(controller);
        Float[] handVel       = getHandVel(controller);
        Float[] grabStrength  = getGrabStrength(controller);
        Float[] pinchStrength = getPinchStrength(controller);
        Float[] armWidth      = getArmWidth(controller);
        Float[] fingLen       = getFingLen(controller);
        Float[] fingWid       = getFingWid(controller);
        Float[] fingVel       = getFingVel(controller);
        Float[] boneLen       = getBoneLen(controller);
        Float[] boneWid       = getBoneWidth(controller);
        Float[] circProp      = getCircProp(controller);
        Float[] swipeProp     = getSwipeProp(controller);
        Float[] screenProp    = getScreenTapProp(controller);
        Float[] keyProp       = getKeyTapProp(controller);
        Float[] handCon       = getHandConfidence(controller);

        // Write timestamp to data
        data[0] = String.valueOf((Object) timestamp);
           
        // Write number of hands to data
        data[1] = String.valueOf((Object) numHands);
            
        // Write hand width to data
        data[2] = String.valueOf((Object) handWidths[0]);
        data[10] = String.valueOf((Object) handWidths[1]);
            
        // Write pitch, yaw, and roll to data
        data[3] = String.valueOf((Object) orientation[0]);
        data[4] = String.valueOf((Object) orientation[1]);
        data[5] = String.valueOf((Object) orientation[2]);
        data[11] = String.valueOf((Object) orientation[3]);
        data[12] = String.valueOf((Object) orientation[4]);
        data[13] = String.valueOf((Object) orientation[5]);
            
        // Write hand velocity to data
        data[6] = String.valueOf((Object) handVel[0]);
        data[14] = String.valueOf((Object) handVel[1]);
            
        // Write grab strength to data
        data[7] = String.valueOf((Object) grabStrength[0]);
        data[15] = String.valueOf((Object) grabStrength[1]);
            
        // Write pinch strength to data
        data[8] = String.valueOf((Object) pinchStrength[0]);
        data[16] = String.valueOf((Object) pinchStrength[1]);
            
        // Write arm width to data
        data[9] = String.valueOf((Object) armWidth[0]);
        data[17] = String.valueOf((Object) armWidth[1]);
            
        // Write finger lengths to data
        for (int i = 0; i < 5; i++) {
            data[i + 18] = String.valueOf((Object) fingLen[i]);
        }
        for (int i = 0; i < 5; i++) {
            data[i + 33] = String.valueOf((Object) fingLen[i + 5]);
        }
            
        // Write finger widths to data
        for (int i = 0; i < 5; i++) {
            data[i + 23] = String.valueOf((Object) fingWid[i]);
        }
        for (int i = 0; i < 5; i++) {
            data[i + 38] = String.valueOf((Object) fingWid[i + 5]);
        }
            
        // Write finger velocities to data
        for (int i = 0; i < 5; i++) {
            data[i + 28] = String.valueOf((Object) fingVel[i]);
        }
        for (int i = 0; i < 5; i++) {
            data[i + 43] = String.valueOf((Object) fingVel[i + 5]);
        }
            
        // Write bone lengths to data
        for (int i = 0; i < 20; i++) {
            data[i + 48] = String.valueOf((Object) boneLen[i]);
        }
        for (int i = 0; i < 20; i++) {
            data[i + 68] = String.valueOf((Object) boneLen[i + 20]);
        }
            
        // Write bone widths to data
        for (int i = 0; i < 20; i++) {
            data[i + 88] = String.valueOf((Object) boneWid[i]);
        }
        for (int i = 0; i < 20; i++) {
            data[i + 108] = String.valueOf((Object) boneWid[i + 20]);
        }
            
        // Write gesture info to data

        data[128] = String.valueOf((Object) circProp[0]);
        data[129] = String.valueOf((Object) circProp[1]);
        data[130] = String.valueOf((Object) swipeProp[0]);
        data[131] = String.valueOf((Object) swipeProp[1]);
        data[132] = String.valueOf((Object) screenProp[0]);
        data[133] = String.valueOf((Object) screenProp[1]);
        data[134] = String.valueOf((Object) keyProp[0]);
        data[135] = String.valueOf((Object) keyProp[1]);

        data[136] = String.valueOf((Object) handCon[0]);
        data[137] = String.valueOf((Object) handCon[1]);

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