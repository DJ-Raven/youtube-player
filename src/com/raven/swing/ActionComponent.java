package com.raven.swing;

import java.awt.Component;
import java.awt.event.MouseEvent;

public abstract interface ActionComponent {

    public abstract void execute(Component component, MouseEvent me);
}
