package com.raven.effect;

import java.awt.Component;
import java.util.concurrent.atomic.AtomicReference;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.KeyFrames;
import org.jdesktop.core.animation.timing.TimingTarget;
import org.jdesktop.core.animation.timing.TimingTargetAdapter;

public class SafePropertySetter<T> extends TimingTargetAdapter {

    private final AtomicReference<KeyFrames<T>> keyFrames;
    private final boolean isToAnimation;
    private final Getter<T> getter;
    private final Setter<T> setter;

    protected SafePropertySetter(KeyFrames<T> keyFrames, boolean isToAnimation, Getter<T> getter, Setter<T> setter) {
        this.keyFrames = new AtomicReference(keyFrames);
        this.isToAnimation = isToAnimation;
        this.getter = getter;
        this.setter = setter;
    }

    public static <T> TimingTarget getTarget(Setter<T> setter, T... values) {
        return new SafePropertySetter(new KeyFrames.Builder().addFrames(values).build(), false, null, setter);
    }

    public static <T> TimingTarget getTarget(Setter<T> setter, KeyFrames<T> keyFrames) {
        return new SafePropertySetter(keyFrames, false, null, setter);
    }

    public static <T> TimingTarget getTargetTo(Getter<T> getter, Setter<T> setter, T... values) {
        return getTargetTo(getter, setter, new KeyFrames.Builder(values[0]).addFrames(values).build());
    }

    public static <T> TimingTarget getTargetTo(GetterAndSetter<T> getterAndSetter, T... values) {
        return getTargetTo(getterAndSetter, getterAndSetter, values);
    }

    public static <T> TimingTarget getTargetTo(Getter<T> getter, Setter<T> setter, KeyFrames<T> keyFrames) {
        return new SafePropertySetter(keyFrames, true, getter, setter);
    }

    public static <T> TimingTarget getTargetTo(GetterAndSetter<T> getterAndSetter, KeyFrames<T> keyFrames) {
        return getTargetTo(getterAndSetter, getterAndSetter, keyFrames);
    }

    public static <U> Property<U> animatableProperty(Component component, U value) {
        return new Property(component, value);
    }

    @Override
    public void timingEvent(Animator source, double fraction) {
        this.setter.setValue(this.keyFrames.get().getInterpolatedValueAt(fraction));
    }

    @Override
    public void begin(Animator source) {
        if (this.isToAnimation) {
            KeyFrames.Builder<T> builder = new KeyFrames.Builder(this.getter.getValue());
            boolean first = true;
            for (KeyFrames.Frame<T> frame : this.keyFrames.get()) {
                if (first) {
                    first = false;
                } else {
                    builder.addFrame(frame);
                }
            }
            this.keyFrames.set(builder.build());
        }
        double fraction = source.getCurrentDirection() == Animator.Direction.FORWARD ? 0.0D : 1.0D;
        timingEvent(source, fraction);
    }

    public static class Property<T>
            implements SafePropertySetter.GetterAndSetter<T> {

        private final Component component;
        private T value;

        public Property(Component component, T value) {
            this.component = component;
            this.value = value;
        }

        @Override
        public T getValue() {
            return (T) this.value;
        }

        @Override
        public void setValue(T newValue) {
            this.value = newValue;
            if (this.component != null) {
                this.component.repaint();
            }
        }
    }

    public static abstract interface GetterAndSetter<T> extends SafePropertySetter.Getter<T>, SafePropertySetter.Setter<T> {
    }

    public static abstract interface Setter<T> {

        public abstract void setValue(T paramT);
    }

    public static abstract interface Getter<T> {

        public abstract T getValue();
    }
}
