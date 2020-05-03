package com.raven.effect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Utils {

    private static final boolean USESUN2D;
    private static final Method GETUSABLEBOUNDS;

    static {
        //Check if sun.java2d.SunGraphicsEnvironment.getUsableBounds()
        //is available.
        boolean found = false;
        Method getMethod = null;
        try {
            Class sunGE = Class.forName("sun.java2d.SunGraphicsEnvironment");
            Method[] meths = sunGE.getDeclaredMethods();
            for (Method meth : meths) {
                if (meth.getName().equals("getUsableBounds") && Arrays.equals(meth.getParameterTypes(), new Class[]{java.awt.GraphicsDevice.class}) && meth.getExceptionTypes().length == 0 && meth.getReturnType().equals(java.awt.Rectangle.class)) {
                    //We found it!
                    getMethod = meth;
                    found = true;
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            //It seems not
            found = false;
        }
        USESUN2D = found;
        GETUSABLEBOUNDS = getMethod;
    }

    public static Rectangle getScreenSize() {
        Rectangle screen;
        if (USESUN2D) {
            try {
                Frame frame = new Frame();
                GraphicsConfiguration config = frame.getGraphicsConfiguration();
                screen = (Rectangle) GETUSABLEBOUNDS.invoke(null, config.getDevice());
                frame.dispose();
            } catch (HeadlessException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                //If something doesn't work, fallback to Toolkit
                Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
                screen = new Rectangle(0, 0, size.width, size.height);
            }
        } else { //Do it the traditional way
            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            screen = new Rectangle(0, 0, size.width, size.height);
        }
        return screen;
    }

    public static boolean isTranslucencySupported() {
        boolean nativeTrans;
        if (System.getProperty("java.version").contains("1.6")) {
            nativeTrans = false;
        } else {
            nativeTrans = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT);
        }
        return nativeTrans;
    }

    public static boolean isDark(Color color) {
        //return (color.getRed()*0.299 + color.getGreen()*0.587 + color.getBlue()*0.114) < (0.6*255);
        //return (color.getRed() + color.getGreen() + color.getBlue())/3 < (0.63*255);
        return (color.getRed() * 0.2125 + color.getGreen() * 0.7154 + color.getBlue() * 0.0721) < (0.535 * 255);
        //return (color.getRed()*0.21 + color.getGreen()*0.72 + color.getBlue()*0.07) < (0.54*255);
    }

    public static Color darken(Color color) {
        int r = wrapU8B(color.getRed() - 30);
        int g = wrapU8B(color.getGreen() - 30);
        int b = wrapU8B(color.getBlue() - 30);
        return new Color(r, g, b, color.getAlpha());
    }

    public static Color brighten(Color color) {
        int r = wrapU8B(color.getRed() + 30);
        int g = wrapU8B(color.getGreen() + 30);
        int b = wrapU8B(color.getBlue() + 30);
        return new Color(r, g, b, color.getAlpha());
    }

    private static int wrapU8B(int i) {
        return Math.min(255, Math.max(0, i));
    }

    public static Color applyAlphaMask(Color color, int bitMask) {
        return new Color(color.getRGB() & 0x00FFFFFF | (bitMask & 0xFF000000), true);
    }
}
