import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class Cell extends Actor
{
    GreenfootImage liveImg, deadImg;
    boolean live = false; //dead is live=false;
    boolean update = false; //alt btwn update and calc
    boolean nextLive=false;

    public Cell()
    {
        this(false);
    }

    public Cell(boolean a)
    {
        live=a;
        liveImg = new GreenfootImage(25, 25);
        liveImg.setColor(Color.MAGENTA);
        liveImg.fill();
        deadImg = new GreenfootImage(25, 25);
        deadImg.setColor(Color.LIGHT_GRAY);
        deadImg.fill();
        setImage(deadImg);
    }

    public void setLive(boolean a)
    {
        live = a;
        nextLive = live;
    }

    public boolean isLive()
    {
        return live;
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            ((GameOfLife)getWorld()).radar(this);
        }
        if (!((GameOfLife)getWorld()).paused())
        {
            if (update)
                update();
            else calculate();
            update=!update;
        }
        if (live)
            setImage(liveImg);
        else setImage(deadImg);
    }

    private void update()
    {
        live = nextLive;
    }

    private void calculate()
    {
        List <Cell> neighbours = getNeighbours(1, true, Cell.class);
        int count=0;
        for(Cell c:neighbours)
        {
            if (c.live)
                count++;
        }
        //1: if live and less than 2 live neighbours, dead
        if (live && count<2)
            nextLive=false;
        //2: if live and 2/3 live neighbours, live
        else if (live && count<=3)
            nextLive=true;
        //3: if live and more than 3 live neighbours, dead
        else nextLive=false;
        //4: if dead and exactly 3 live neighbours, live
        if (!live && count==3)
            nextLive=true;
    }
}
