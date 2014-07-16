package leapmotionauthentication;

import com.leapmotion.leap.*;

/**
 *
 * @author achan
 */

public class Features {

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
        Float[] handWidths = {null, null};
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
        Float[] rotDir = {null, null, null, null, null, null};
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
        Float[] handVel = {null, null};
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
        Float[] grabStrengths = {null, null};
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
        Float[] pinchStrengths = {null, null};
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
        Float[] armWidths = {null, null};
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
        Float[] fingLen = {null, null, null, null, null, null, null, null, null, null};
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
        Float[] fingWid = {null, null, null, null, null, null, null, null, null, null};
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
        Float[] fingVel = {null, null, null, null, null, null, null, null, null, null};
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
        Float[] boneLen = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
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
        Float[] boneWidth = {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
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

    // Return a float array with gesture properties
    // Types: circle    = 0
    //        swipe     = 1
    //        screenTap = 2
    //        keyTap    = 3
    // First element is type
    // For a circle: second element is radius
    // Second element is magnitude of direction vector and duration of gesture
    public static Float[] getGestProp(Controller controller) {
        Frame currentFrame = controller.frame();
        GestureList allGestures = currentFrame.gestures();
        Gesture gesture;
        Float[] gestProp = {null, null, null};
        Float type, radius, dir, elapTime;
        for (int i = 0; i < allGestures.count(); i++) {
            gesture = allGestures.get(i);
            switch (gesture.type()) {
                case TYPE_CIRCLE:
                    type = (float) 0;
                    CircleGesture circle = new CircleGesture(gesture);
                    radius = circle.radius();
                    elapTime = (float) circle.duration();
                    gestProp[0] = type;
                    gestProp[1] = radius;
                    gestProp[2] = elapTime;
                case TYPE_SWIPE:
                    type = (float) 1;
                    SwipeGesture swipe = new SwipeGesture(gesture);
                    Vector swipeDirection = swipe.direction();
                    dir = swipeDirection.magnitude();
                    elapTime = (float) swipe.duration();
                    gestProp[0] = type;
                    gestProp[1] = dir;
                    gestProp[2] = elapTime;
                case TYPE_SCREEN_TAP:
                    type = (float) 2;
                    ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
                    Vector tapDirection = screenTap.direction();
                    dir = tapDirection.magnitude();
                    elapTime = (float) screenTap.duration();
                    gestProp[0] = type;
                    gestProp[1] = dir;
                    gestProp[2] = elapTime;
                case TYPE_KEY_TAP:
                    type = (float) 3;
                    KeyTapGesture keyTap = new KeyTapGesture(gesture);
                    tapDirection = keyTap.direction();
                    dir = tapDirection.magnitude();
                    elapTime = (float) keyTap.duration();
                    gestProp[0] = type;
                    gestProp[1] = dir;
                    gestProp[2] = elapTime;
            }
        }
        return gestProp;
    }

}
