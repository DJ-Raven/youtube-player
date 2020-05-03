package com.raven.swing;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TextField extends javax.swing.JTextField {

    private ArrayList<String> dataComplete;
    private boolean number = false;
    private boolean floating = false;
    private boolean autoFormat = false;
    private boolean autoComplete = false;
    private int borderType = 0;
    private final String formating = "#,##0.##";
    private int length = -1;
    private boolean drawBorder = true;

    public TextField() {
        execute();
    }

    private void execute() {
        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 10));
        setPreferredSize(new Dimension(150, 31));
        setSelectionColor(new java.awt.Color(173, 173, 173));
        setFont(new java.awt.Font("Khmer SBBIC Serif", 0, 15));
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
                            EventQueue.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    String text = getText();
                                    if (!text.equals("")) {
                                        autoComplete(text);
                                    }
                                }
                            });
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (drawBorder) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            if (borderType == 1) {
                g.setColor(java.awt.Color.RED);
            } else if (borderType == 2) {
                g.setColor(java.awt.Color.ORANGE);
            } else if (isFocusOwner()) {
                g.setColor(new java.awt.Color(109, 109, 253));
            } else {
                g.setColor(java.awt.Color.GRAY);
            }
            g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
        super.paintComponent(g);
    }

    @Override
    public void setText(String string) {
        borderType = 0;
        super.setText(string);
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

    public void error() {
        borderType = 1;
        repaint();
    }

    public void warning() {
        borderType = 2;
        repaint();
    }

    public void simple() {
        borderType = 0;
        repaint();
    }

    public void setCompleteText(List data) {
        this.dataComplete = new ArrayList<>(data);
    }

    public List getCompleteText() {
        return dataComplete;
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

    public boolean isNumber() {
        return number;
    }

    public boolean isFloating() {
        return floating;
    }

    public String getFormating() {
        return formating;
    }

    public int getLength() {
        return length;
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

    public void setAutoFormat(boolean autoFormat) {
        this.autoFormat = autoFormat;
    }

    public void setLength(int length) {
        this.length = length;
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

    public void clearAutoComplete() {
        if (autoComplete) {
            dataComplete.clear();
        }
    }

    public boolean isDrawBorder() {
        return drawBorder;
    }

    public void setDrawBorder(boolean drawBorder) {
        this.drawBorder = drawBorder;
    }
}
