package Business.Entities;

import Presentation.Manager.PianoFrameManager;

/**
 * TODO: QUE FOT AIXO?
 */
public abstract class Observer {
    protected ChangeTime subject;
    public abstract void update();
}
