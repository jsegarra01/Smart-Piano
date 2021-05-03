package Business.Entities;

public class Keys {
    private int keyCode;
    private String tileKey;
    private boolean pressed = false;


    public Keys(int keyCode, String tileKey){
        this.keyCode = keyCode;
        this.tileKey = tileKey;
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
}
