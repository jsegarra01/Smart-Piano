package Business.Entities;

public class Keys {
    private int keyCode;
    private String tileKey;
    private String nameKey;
    private boolean pressed = false;


    public Keys(int keyCode, String tileKey, String nameKey){
        this.keyCode = keyCode;
        this.tileKey = tileKey;
        this.nameKey = nameKey;
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

    public void setTileKey(String tileKey) {
        this.tileKey = tileKey;
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

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }
}
