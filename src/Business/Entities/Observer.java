package Business.Entities;

import Presentation.Manager.PianoFrameManager;

import javax.swing.*;

public abstract class Observer {
    protected ChangeTime subject;
    public abstract void update();
}
