package ultimateconsole;

public class DrawDimension
{
    public final int x,y;
    public final int width,height;

    public DrawDimension(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public DrawDimension(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.width = 0;
        this.height = 0;
    }
}
