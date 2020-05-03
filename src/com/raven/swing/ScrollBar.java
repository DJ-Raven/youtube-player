package com.raven.swing;

import java.awt.Adjustable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ScrollBar extends JScrollBar {

    private int t = 0;
    private Color color = new Color(153, 153, 153, t);
    private boolean isPress = false;
    private boolean isOver = false;

    private void addEventMount() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                showScroll();
                isOver = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                if (!isPress) {
                    hideScroll();
                }
                isOver = false;
            }

            @Override
            public void mousePressed(MouseEvent me) {
                isPress = true;
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (!isOver) {
                    hideScroll();
                }
                isPress = false;
            }

        });
    }

    public ScrollBar() {
        this(null, Adjustable.VERTICAL);
    }

    public ScrollBar(Component com, int orientation) {
        this(com, orientation, 8, false);
    }

    public ScrollBar(Component com, int orientation, boolean act) {
        this(com, orientation, 8, act);
    }

    public ScrollBar(Component com, int orientation, int size, boolean act) {
        super(orientation);
        if (act) {
            addEventMount();
        } else {
            color = new Color(153, 153, 153);
        }
        if (orientation == Adjustable.VERTICAL) {
            setPreferredSize(new Dimension(size, 100));
        } else {
            setPreferredSize(new Dimension(100, size));
        }
        setUI(new BasicScrollBarUI() {
            @Override
            protected BasicScrollBarUI.ScrollListener createScrollListener() {
                setUnitIncrement(50);
                setBlockIncrement(50);
                return super.createScrollListener();
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                if (com != null) {
                    g.setColor(com.getBackground());
                }
                g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton dummyButton = new JButton();
                dummyButton.setPreferredSize(new Dimension(0, 0));
                return dummyButton;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton dummyButton = new JButton();
                dummyButton.setPreferredSize(new Dimension(0, 0));
                return dummyButton;
            }

            @Override
            protected Dimension getMinimumThumbSize() {
                boolean isVertical = ScrollBar.this.getOrientation() == Adjustable.VERTICAL;
                if (isVertical) {
                    return new Dimension(size, 50);
                } else {
                    return new Dimension(50, size);
                }
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                if (!thumbBounds.isEmpty() && this.scrollbar.isEnabled()) {
                    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    boolean isVertical = ScrollBar.this.getOrientation() == Adjustable.VERTICAL;
                    g.setColor(color);
                    g.fillRoundRect(thumbBounds.x + (isVertical ? 1 : 8), thumbBounds.y + (isVertical ? 8 : 1), thumbBounds.width - (isVertical ? 2 : 16), thumbBounds.height - (isVertical ? 16 : 2), isVertical ? thumbBounds.width : thumbBounds.height, isVertical ? thumbBounds.width : thumbBounds.height);
                }
            }

            @Override
            public void layoutContainer(Container scrollbarContainer) {
                super.layoutContainer(scrollbarContainer);
                incrButton.setBounds(0, 0, 0, 0);
                decrButton.setBounds(0, 0, 0, 0);
            }
        });
    }
    private boolean show = false;
    private Timer time = new Timer(1, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (show) {
                if (t >= 250) {
                    t = 250;
                    color = new Color(153, 153, 153, t);
                    repaint();
                    time.stop();
                } else {
                    t++;
                    color = new Color(153, 153, 153, t);
                    repaint();
                }
            } else {
                if (t <= 0) {
                    t = 0;
                    color = new Color(153, 153, 153, t);
                    repaint();
                    time.stop();
                } else {
                    if (t >= 250) {
                    }
                    t--;
                    color = new Color(153, 153, 153, t);
                    repaint();
                }
            }
        }
    });

    private void showScroll() {
        show = true;
        time.start();
    }

    private void hideScroll() {
        show = false;
        time.start();
    }
}
