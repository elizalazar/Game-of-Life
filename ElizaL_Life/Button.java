import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Button extends Label
{
    private String name;

    public Button(String str)
    {
        super(str, 32);
        name = str;
    }

    public void act()
    {
        if (Greenfoot.mouseClicked(this))
        {
            //controls
            if (name.equals("PAUSE"))
            {
                ((GameOfLife)getWorld()).pause();
                name="RUN";
                setValue(name);
            }
            else if (name.equals("RUN"))
            {
                ((GameOfLife)getWorld()).pause();
                name="PAUSE";
                setValue(name);
            }
            else if (name.equals("RANDOM"))
                ((GameOfLife)getWorld()).random();

            else if (name.equals("CLEAR"))
                ((GameOfLife)getWorld()).clear();
            //patterns
            else if (name.equals("Glider"))
            {
                ((GameOfLife)getWorld()).setCurrentPattern(1);
            }
            else if (name.equals("10-In-A-Row"))
            {
                ((GameOfLife)getWorld()).setCurrentPattern(2);
            }
            else if (name.equals("Pulsar"))
            {
                ((GameOfLife)getWorld()).setCurrentPattern(3);
            }
            else if (name.equals("Toaster"))
            {
                ((GameOfLife)getWorld()).setCurrentPattern(4);
            }
            else if (name.equals("Toad"))
            {
                ((GameOfLife)getWorld()).setCurrentPattern(5);
            }
            else if (name.equals("Washer"))
            {
                ((GameOfLife)getWorld()).setCurrentPattern(6);
            }
        }
    }
}
