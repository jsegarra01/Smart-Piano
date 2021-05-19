package Business.Entities;

public class Keys {
    private int keyCode;
    private String tileKey;
    private String nameKey;
    private boolean pressed = false;
    private long duration;
    private long startTime;


    public Keys(int keyCode, String tileKey, String nameKey){
        this.keyCode = keyCode;
        this.tileKey = tileKey;
        this.nameKey = nameKey;
    }

    public Keys(int keyCode, long duration, long startTime){
        this.keyCode = keyCode;
        this.duration = duration;
        this.startTime = startTime;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public String getTileKey() {
        return tileKey;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public String getNameKey() {
        return nameKey;
    }

    public long getDuration() {
        return duration;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }
}
