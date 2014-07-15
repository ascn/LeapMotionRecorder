package leapmotionauthentication;

import com.leapmotion.leap.*;

/**
 *
 * @author achan
 */

public class Features {
    
    public static void initialize(Controller controller, Listener listener) {        
        controller.addListener(listener);
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        if (controller.policyFlags() != Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES) {
            controller.setPolicyFlags(Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES);
        }
    }

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
    public static float[] getHandWidths(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        float wid;
        float[] handWidths = {0, 0};
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
    public static float[] getRotDir(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        float pitch, yaw, roll;
        float[] rotDir = {0, 0, 0, 0, 0, 0};
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
                rotDir[4] = pitch;
                rotDir[5] = yaw;
                rotDir[6] = roll;
            }
        }
        return rotDir;
    }

    // Returns a float array of the speed of the hands in millimeters per second
    // First element is the right hand
    public static float[] getHandVel(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        Vector vel;
        float velMag;
        float[] handVel = {0, 0};
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
    public static float[] getGrabStrength(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        float handGrab;
        float[] grabStrengths = {0, 0};
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
    public static float[] getPinchStrength(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        float handPinch;
        float[] pinchStrengths = {0, 0};
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
    public static float[] getArmWidth(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        Arm arm;
        float armWidth;
        float[] armWidths = {0, 0};
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
    public static float[] getFingLen(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        FingerList allFingers;
        Finger finger;
        float fingerLength;
        float[] fingLen = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
    public static float[] getFingWid(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        FingerList allFingers;
        Finger finger;
        float fingerWidth;
        float[] fingWid = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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

    public static float[] getFingVel(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        FingerList allFingers;
        Finger finger;
        Vector vel;
        float velMag;
        float[] fingVel = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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

    public static float[] getBoneLen(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        FingerList allFingers;
        Finger finger;
        Bone bone;
        float length;
        float[] boneLen = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < allHands.count(); i++) {
            hand = allHands.get(i);
            if (hand.isRight() == true) {
                allFingers = hand.fingers();
                for (int j = 0; j < allFingers.count(); j++) {
                    finger = allFingers.get(j);
                    for (Bone.Type boneType : Bone.Type.values()) { // Problem: BONE.TYPE IS NOT INT, CANNOT BE CAST TO INT
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

    public static float[] getBoneWidth(Controller controller) {
        Frame currentFrame = controller.frame();
        HandList allHands = currentFrame.hands();
        Hand hand;
        FingerList allFingers;
        Finger finger;
        Bone bone;
        float width;
        float[] boneWidth = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
    public static float[] getGestProp(Controller controller) {
        Frame currentFrame = controller.frame();
        GestureList allGestures = currentFrame.gestures();
        Gesture gesture;
        float[] gestProp = {0, 0, 0};
        int type;
        float radius;
        float dir;
        float elapTime;
        for (int i = 0; i < allGestures.count(); i++) {
            gesture = allGestures.get(i);
            switch (gesture.type()) {
                case TYPE_CIRCLE:
                    type = 0;
                    CircleGesture circle = new CircleGesture(gesture);
                    radius = circle.radius();
                    elapTime = circle.duration();
                    gestProp[0] = type;
                    gestProp[1] = radius;
                    gestProp[2] = elapTime;
                case TYPE_SWIPE:
                    type = 1;
                    SwipeGesture swipe = new SwipeGesture(gesture);
                    Vector swipeDirection = swipe.direction();
                    dir = swipeDirection.magnitude();
                    elapTime = swipe.duration();
                    gestProp[0] = type;
                    gestProp[1] = dir;
                    gestProp[2] = elapTime;
                case TYPE_SCREEN_TAP:
                    type = 2;
                    ScreenTapGesture screenTap = new ScreenTapGesture(gesture);
                    Vector tapDirection = screenTap.direction();
                    dir = tapDirection.magnitude();
                    elapTime = screenTap.duration();
                    gestProp[0] = type;
                    gestProp[1] = dir;
                    gestProp[2] = elapTime;
                case TYPE_KEY_TAP:
                    type = 3;
                    KeyTapGesture keyTap = new KeyTapGesture(gesture);
                    tapDirection = keyTap.direction();
                    dir = tapDirection.magnitude();
                    elapTime = keyTap.duration();
                    gestProp[0] = type;
                    gestProp[1] = dir;
                    gestProp[2] = elapTime;
            }
        }
        return gestProp;
    }

}
