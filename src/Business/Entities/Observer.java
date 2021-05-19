package Business.Entities;

import Presentation.Ui_Views.PianoFrame;

public abstract class Observer {
    protected PianoFrame subject;
    public abstract void update();
}
