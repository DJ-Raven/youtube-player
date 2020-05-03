package com.raven.effect;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTargetAdapter;
import org.jdesktop.core.animation.timing.interpolators.AccelerationInterpolator;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;

public class Effect {

    private final List<RippleAnimation> ripples = new ArrayList();
    private final JComponent target;
    private final SwingTimerTimingSource timer;
    private Color fcolor;

    private Effect(JComponent component) {
        this.target = component;

        this.timer = new SwingTimerTimingSource();
        this.timer.init();
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (RippleAnimation rippleAnimation : this.ripples) {
            float rippleOpacity = (rippleAnimation.rippleOpacity.getValue()).floatValue();
            Point rippleCenter = rippleAnimation.rippleCenter;
            int rippleRadius = (rippleAnimation.rippleRadius.getValue());
            Color fg = fcolor;
            g2.setColor(new Color(fg.getRed() / 255.0F, fg.getGreen() / 255.0F, fg.getBlue() / 255.0F, rippleOpacity));
            g2.fillOval(rippleCenter.x - rippleRadius, rippleCenter.y - rippleRadius, 2 * rippleRadius, 2 * rippleRadius);
        }
    }

    public void addRipple(Point point, int maxRadius) {
        RippleAnimation ripple = new RippleAnimation(point, maxRadius);
        this.ripples.add(ripple);
        ripple.start();
    }

    public static Effect applyTo(final JComponent target) {
        Effect rippleEffect = new Effect(target);
        target.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    rippleEffect.addRipple(e.getPoint(), target.getWidth());
                }
            }
        });
        return rippleEffect;
    }

    public static Effect applyFixedTo(final JComponent target) {
        Effect rippleEffect = new Effect(target);
        target.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    rippleEffect.addRipple(new Point(24, 24), target.getWidth() / 2);
                }
            }
        });
        return rippleEffect;
    }

    public class RippleAnimation {

        private final Point rippleCenter;
        private final int maxRadius;
        private final SafePropertySetter.Property<Integer> rippleRadius = SafePropertySetter.animatableProperty(Effect.this.target, 25);
        private final SafePropertySetter.Property<Double> rippleOpacity = SafePropertySetter.animatableProperty(Effect.this.target, 0.0D);

        private RippleAnimation(Point rippleCenter, int maxRadius) {
            this.rippleCenter = rippleCenter;
            this.maxRadius = maxRadius;
        }

        void start() {
            Animator rippleAnimator = new Animator.Builder(Effect.this.timer).setDuration(1000L, TimeUnit.MILLISECONDS).setEndBehavior(Animator.EndBehavior.HOLD).setInterpolator(new AccelerationInterpolator(0.2D, 0.19D)).addTarget(SafePropertySetter.getTarget(this.rippleRadius, new Integer[]{0, this.maxRadius / 2, this.maxRadius, this.maxRadius})).addTarget(SafePropertySetter.getTarget(this.rippleOpacity, new Double[]{0.0D, 0.4D, 0.3D, 0.0D})).addTarget(new TimingTargetAdapter() {
                @Override
                public void end(Animator source) {
                    Effect.this.ripples.remove(Effect.RippleAnimation.this);
                }
            }).build();
            rippleAnimator.start();
        }
    }

    public void setFcolor(Color fcolor) {
        this.fcolor = fcolor;
    }

}
