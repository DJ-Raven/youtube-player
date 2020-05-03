package com.raven.swing;

import com.raven.effect.MaterialColor;
import com.raven.effect.SafePropertySetter;
import com.raven.effect.Utils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.interpolators.SplineInterpolator;
import org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource;

public class RTextField extends JTextField {

    public static final int HINT_OPACITY_MASK = 0x99000000;
    public static final int LINE_OPACITY_MASK = 0x66000000;
    private final FloatingLabel floatingLabel = new FloatingLabel(this);
    private final Line line = new Line(this);
    private String hint = "";
    private Color accentColor = MaterialColor.BLUE_500;
    private ArrayList<String> dataComplete;
    private boolean number = false;
    private boolean floating = false;
    private boolean autoFormat = false;
    private boolean autoComplete = false;
    private int borderType = 0;
    private final String formating = "#,##0.##";
    private int length = -1;

    public RTextField() {
        super();
        execute();
    }

    public RTextField(String text) {
        this();
        sendText(text);
    }

    private void execute() {
        setBorder(null);
        setPreferredSize(new Dimension(150, 40));
        setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 14));
        setSelectionColor(MaterialColor.BLUE_200);
        setSelectedTextColor(MaterialColor.GREY_900);
        floatingLabel.setText("");
        setOpaque(false);
        setBackground(MaterialColor.TRANSPARENT);
        setCaret(new DefaultCaret() {
            @Override
            protected synchronized void damage(Rectangle r) {
                RTextField.this.repaint();
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ce) {
                floatingLabel.update();
                line.update();
            }
        });
        getCaret().setBlinkRate(500);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {

                borderType = 0;
                if (number) {
                    checkKhmerKey(evt);
                    if (Character.isDigit(evt.getKeyChar())) {
                        if (length >= 0 && getSelectionStart() == getSelectionEnd()) {
                            if (getText().replace(",", "").length() >= length) {
                                evt.consume();
                            }
                        }
                    } else {
                        evt.consume();
                    }
                } else if (floating) {
                    checkKhmerKey(evt);
                    if (Character.isDigit(evt.getKeyChar())) {
                        if (length >= 0 && getSelectionStart() == getSelectionEnd()) {
                            if (!getText().equals("") && String.valueOf(Double.valueOf("0" + getText().replace(",", "")).intValue()).length() >= length) {
                                if (getText().indexOf(46) < 0 || getCaretPosition() <= getText().indexOf(46)) {
                                    evt.consume();
                                }
                            }
                        }
                    } else if (evt.getKeyChar() == 46) {
                        if (getText().equals("")) {
                            setText("0");
                        }
                        if (getText().indexOf(46) >= 0) {
                            evt.consume();
                        }
                    } else {
                        evt.consume();
                    }
                } else {
                    if (length >= 0 && getSelectionStart() == getSelectionEnd()) {
                        if (getText().length() >= length) {
                            evt.consume();
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent evt) {
                if ((number || floating) && autoFormat) {
                    if ((Character.isDigit(evt.getKeyChar()) || evt.getKeyChar() == 8 || evt.getKeyChar() == 46 || evt.isControlDown()) && !getText().equals("")) {
                        textFormating();
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent evt) {
                if (autoComplete) {
                    switch (evt.getKeyCode()) {
                        case KeyEvent.VK_BACK_SPACE:
                            break;
                        case KeyEvent.VK_ENTER:
                            setText(getText());
                            break;
                        default:
                            EventQueue.invokeLater(() -> {
                                String text = getText();
                                if (!text.equals("")) {
                                    autoComplete(text);
                                }
                            });
                    }
                }
            }
        });
    }

    private void textFormating() {
        int count = getText().length();
        int care = getCaretPosition();
        int carelast;
        double num;
        String last;
        if (getText().contains(".")) {
            num = Double.valueOf(("0" + getText().trim()).split("\\.")[0].replace(",", ""));
            if (getText().split("\\.").length == 2) {
                carelast = countString(getText().split("\\.")[1], ',');
                last = "." + getText().trim().split("\\.")[1].replace(",", "");
                care += carelast;
            } else {
                last = ".";
            }
        } else {
            num = Double.valueOf(getText().trim().replace(",", ""));
            last = "";
        }
        DecimalFormat df = new DecimalFormat(formating);
        setText(df.format(num) + last);
        int newCount = getText().length();
        care += (newCount - count);
        if (care <= -1) {
            care = 0;
        }
        setCaretPosition(care);
    }

    private void autoComplete(String text) {
        String complete = "";
        int start = text.length();
        int lest = text.length();
        int a;
        for (a = 0; a < dataComplete.size(); a++) {
            if (dataComplete.get(a).startsWith(text)) {
                complete = dataComplete.get(a);
                lest = complete.length();
                break;
            }
        }
        if (lest > start) {
            setText(complete);
            setCaretPosition(start);
            moveCaretPosition(lest);
        }
    }

    private void checkKhmerKey(KeyEvent evt) {
        if (evt.getKeyChar() >= 6112 && evt.getKeyChar() <= 6121) {
            switch (evt.getKeyChar()) {
                case 6112:
                    evt.setKeyChar('0');
                    break;
                case 6113:
                    evt.setKeyChar('1');
                    break;
                case 6114:
                    evt.setKeyChar('2');
                    break;
                case 6115:
                    evt.setKeyChar('3');
                    break;
                case 6116:
                    evt.setKeyChar('4');
                    break;
                case 6117:
                    evt.setKeyChar('5');
                    break;
                case 6118:
                    evt.setKeyChar('6');
                    break;
                case 6119:
                    evt.setKeyChar('7');
                    break;
                case 6120:
                    evt.setKeyChar('8');
                    break;
                case 6121:
                    evt.setKeyChar('9');
            }
        } else if (evt.getKeyChar() == 44) {
            evt.setKeyChar('.');
        }
    }

    public int countString(char text) {
        char texts[] = getText().toCharArray();
        int count = 0;
        for (int i = 0; i < texts.length; i++) {
            if (texts[i] == text) {
                count++;
            }
        }
        return count;
    }

    public int countString(String st, char text) {
        char texts[] = st.toCharArray();
        int count = 0;
        for (int i = 0; i < texts.length; i++) {
            if (texts[i] == text) {
                count++;
            }
        }
        return count;
    }

    public void addCompleteText(String text) {
        if (dataComplete == null) {
            dataComplete = new ArrayList<>();
        }
        for (int i = 0; i < dataComplete.size(); i++) {
            if (dataComplete.get(i).equals(text)) {
                return;
            }
        }
        dataComplete.add(text);
    }

    private void sendText(String text) {
        setText(text);
    }

    public String getLabel() {
        return floatingLabel.getText();
    }

    public void setLabel(String label) {
        floatingLabel.setText(label);
        repaint();
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
        repaint();
    }

    public Color getAccent() {
        return accentColor;
    }

    public void setAccent(Color accentColor) {
        this.accentColor = accentColor;
        floatingLabel.setAccent(accentColor);
    }

    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        if (floatingLabel != null) {
            floatingLabel.updateForeground();
        }
    }

    @Override
    public void setText(String s) {
        super.setText(s);
        floatingLabel.update();
        line.update();
    }

    @Override
    protected void processFocusEvent(FocusEvent e) {
        super.processFocusEvent(e);
        floatingLabel.update();
        line.update();
    }

    @Override
    protected void processKeyEvent(KeyEvent e) {
        super.processKeyEvent(e);
        floatingLabel.update();
        line.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.translate(0, -3);
        super.paintComponent(g);
        g2.translate(0, 3);
        if (!getHint().isEmpty() && getText().isEmpty() && (getLabel().isEmpty() || isFocusOwner()) && floatingLabel.isFloatingAbove()) {
            g.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 14));
            g2.setColor(Utils.applyAlphaMask(getForeground(), HINT_OPACITY_MASK));
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            g.drawString(getHint(), 0, metrics.getAscent() + 3);
        }
        floatingLabel.paint(g2);
        g2.setColor(Utils.applyAlphaMask(getForeground(), LINE_OPACITY_MASK));
        g2.fillRect(0, getHeight() - 9, getWidth(), 1);
        g2.setColor(accentColor);
        g2.fillRect((int) ((getWidth() - line.getWidth()) / 2), getHeight() - 10, (int) line.getWidth(), 2);
    }

    @Override
    protected void paintBorder(Graphics g) {
        //intentionally left blank
    }

    public boolean isNumber() {
        return number;
    }

    public void setNumber(boolean number) {
        if (number) {
            setFloating(false);
            setAutoFormat(true);
            setAutoComplete(false);
            setText("0");
        } else {
            setText("");
        }
        this.number = number;
    }

    public boolean isFloating() {
        return floating;
    }

    public void setFloating(boolean floating) {
        if (floating) {
            setNumber(false);
            setAutoFormat(true);
            setAutoComplete(false);
            setText("0");
        } else {
            setText("");
        }
        this.floating = floating;
    }

    public boolean isAutoFormat() {
        return autoFormat;
    }

    public void setAutoFormat(boolean autoFormat) {
        this.autoFormat = autoFormat;
    }

    public boolean isAutoComplete() {
        return autoComplete;
    }

    public void setAutoComplete(boolean autoComplete) {
        this.autoComplete = autoComplete;
        if (autoComplete) {
            dataComplete = new ArrayList<>();
            setFloating(false);
            setNumber(false);
        }
    }

    public int getBorderType() {
        return borderType;
    }

    public void setBorderType(int borderType) {
        this.borderType = borderType;
    }

    public String getFormating() {
        return formating;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getNumber() {
        return getText().replace(",", "");
    }

    public double getDouble() {
        return Double.valueOf(getNumber());
    }

    public int getInteger() {
        return Double.valueOf(getNumber()).intValue();
    }

    public long getLong() {
        return Double.valueOf(getNumber()).longValue();
    }

    public boolean isEmpty() {
        return getText().equals("");
    }

    public static class Line {

        private final SwingTimerTimingSource timer;
        private final JComponent target;
        private Animator animator;
        private final SafePropertySetter.Property<Double> width;

        public Line(JComponent target) {
            this.target = target;
            this.timer = new SwingTimerTimingSource();
            timer.init();
            width = SafePropertySetter.animatableProperty(target, 0d);
        }

        public void update() {
            if (animator != null) {
                animator.stop();
            }
            animator = new Animator.Builder(timer)
                    .setDuration(200, TimeUnit.MILLISECONDS)
                    .setEndBehavior(Animator.EndBehavior.HOLD)
                    .setInterpolator(new SplineInterpolator(0.4, 0, 0.2, 1))
                    .addTarget(SafePropertySetter.getTarget(width, width.getValue(), target.isFocusOwner() ? (double) target.getWidth() + 1 : 0d))
                    .build();
            animator.start();
        }

        public double getWidth() {
            return width.getValue();
        }
    }

    public static class FloatingLabel {

        private final SwingTimerTimingSource timer;
        private final JTextField target;
        private Animator animator;
        private final SafePropertySetter.Property<Double> y;
        private final SafePropertySetter.Property<Double> fontSize;
        private final SafePropertySetter.Property<Color> color;
        private String text;
        private Color accentColor = MaterialColor.CYAN_500;

        FloatingLabel(JTextField target) {
            this.target = target;
            this.timer = new SwingTimerTimingSource();
            timer.init();
            y = SafePropertySetter.animatableProperty(target, 36d);
            fontSize = SafePropertySetter.animatableProperty(target, 16d);
            color = SafePropertySetter.animatableProperty(target, MaterialColor.MIN_BLACK);
            updateF();
        }

        private void updateF() {
            updateForeground();
        }

        public void updateForeground() {
            color.setValue(Utils.applyAlphaMask(target.getForeground(), HINT_OPACITY_MASK));
        }

        public Color getAccent() {
            return accentColor;
        }

        public void setAccent(Color accentColor) {
            this.accentColor = accentColor;
        }

        void update() {
            if (animator != null) {
                animator.stop();
            }
            Animator.Builder builder = new Animator.Builder(timer)
                    .setDuration(200, TimeUnit.MILLISECONDS)
                    .setEndBehavior(Animator.EndBehavior.HOLD)
                    .setInterpolator(new SplineInterpolator(0.4, 0, 0.2, 1));
            double targetFontSize = (target.isFocusOwner() || !target.getText().isEmpty()) ? 12d : 16d;
            if (fontSize.getValue() != targetFontSize) {
                builder.addTarget(SafePropertySetter.getTarget(fontSize, fontSize.getValue(), targetFontSize));
            }
            double targetY = target.isFocusOwner() || !target.getText().isEmpty() ? 16d : 36d;
            if (Math.abs(targetY - y.getValue()) > 0.1) {
                builder.addTarget(SafePropertySetter.getTarget(y, y.getValue(), targetY));
            }
            Color targetColor;
            if (target.isFocusOwner()) {
                targetColor = accentColor;
            } else {
                targetColor = Utils.applyAlphaMask(target.getForeground(), HINT_OPACITY_MASK);
            }
            if (!targetColor.equals(color.getValue())) {
                builder.addTarget(SafePropertySetter.getTarget(color, color.getValue(), targetColor));
            }
            animator = builder.build();
            animator.start();
        }

        String getText() {
            return text;
        }

        void setText(String text) {
            this.text = text;
        }

        void paint(Graphics2D g) {
            g.setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 14));
            g.setColor(color.getValue());
            FontMetrics metrics = g.getFontMetrics(g.getFont());
            g.drawString(getText(), 0, metrics.getAscent() + y.getValue().intValue());
        }

        boolean isFloatingAbove() {
            return y.getValue() < 17d;
        }
    }
}
