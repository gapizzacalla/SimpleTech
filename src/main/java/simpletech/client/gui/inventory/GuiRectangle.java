package simpletech.client.gui.inventory;

/**
 * @author vswe
 */
public class GuiRectangle {

    private int xCoord;
    private int yCoord;
    private int width;
    private int height;

    public GuiRectangle(int x, int y, int w, int h) {
        this.xCoord = x;
        this.yCoord = y;
        this.width = w;
        this.height = h;
    }

    public boolean inRect(GuiPlanter gui, int mouseX, int mouseY) {
        mouseX -= gui.getLeft();
        mouseY -= gui.getTop();

        return xCoord <= mouseX && mouseX <= xCoord + width && yCoord <= mouseY && mouseY <= yCoord + height;
    }

    public void setX(int x) {
        this.xCoord = x;
    }

    public void setY(int y) {
        this.yCoord = y;
    }
}
