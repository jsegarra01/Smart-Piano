package Business.Entities;

import Presentation.Manager.PianoFrameManager;

public abstract class Observer {
    protected ChangeTime subject;
    public abstract void update();
}
