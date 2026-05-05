import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public class GameOfLife extends World
{
    private boolean paused = true;
    private boolean random = false;
    private int currentPattern = 0;

    public GameOfLife()
    {    
        super(30, 30, 26); 
        addCells();
        prepare();
    }

    public void setCurrentPattern(int p)
    {
        currentPattern = p;
    }

    public void radar(Cell c)
    {
        if (currentPattern==0)
        {
            c.setLive(!c.isLive());
        }
        else runPattern(currentPattern, c.getX(), c.getY());
        currentPattern=0;
    }

    public boolean paused()
    {
        return paused;
    }

    public void random()
    {
        random=!random;
        clear();
        List <Cell> cells = getObjects(Cell.class);
        for (Cell c: cells)
        {
            c.setLive(Greenfoot.getRandomNumber(10)<3);
        }
        random=!random;
    }

    public void pause()
    {
        paused=!paused;
    }

    public void clear()
    {
        List <Cell> cells = getObjects(Cell.class);
        for (Cell c: cells)
        {
            c.setLive(false);
        }
    }
    int[][][] patt = 
        //mouseClicked
        {{{0, 0}},
            //glider
            {{0,-1},{1,0},{-1,1},{0,1},{1,1}},
            //10
            {{1,0},{2,0},{3,0},{4,0},{5,0},{-1,0},{-2,0},{-3,0},{-4,0},{-5,0},{0,0}},
            //pulsar
            {{-2,-1},{-3,-1},{-4,-1},{-6,-2},{-1,-2},{-6,-3},{-1,-3},{-6,-4},{-1,-4},{-2,-6},{-3,-6},{-4,-6},
                {2,-1},{3,-1},{4,-1},{6,-2},{1,-2},{6,-3},{1,-3},{6,-4},{1,-4},{2,-6},{3,-6},{4,-6},
                {-2,1},{-3,1},{-4,1},{-6,2},{-1,2},{-6,3},{-1,3},{-6,4},{-1,4},{-2,6},{-3,6},{-4,6},
                {2,1},{3,1},{4,1},{6,2},{1,2},{6,3},{1,3},{6,4},{1,4},{2,6},{3,6},{4,6}},
            //toaster
            {{0,-1},{-2,-1},{-3,-1},{-7,-1},{2,-1},{3,-1},{7,-1},
                {4,0},{-4,0},{-4,1},{4,1},
                {0,2},{-2,2},{-3,2},{-7,2},{2,2},{3,2},{7,2},
                {-2,-2},{-4,-2},{-5,-2},{2,-2},{4,-2},{5,-2},
                {-2,-3},{-4,-3},{2,-3},{4,-3},{0,-3},
                {-2,-4},{-4,-4},{4,-4},{0,-4},{1,-4},{-3,-5},{4,-5},{5,-5},
                {-2,3},{-4,3},{-5,3},{2,3},{4,3},{5,3},
                {-2,4},{-4,4},{2,4},{4,4},{0,4},
                {-2,5},{-4,5},{4,5},{0,5},{1,5},{-3,6},{4,6},{5,6}
            },
            //toad
            {{0,0},{1,0},{-1,0},{0,-1},{1,-1},{2,-1}},
            //washer
            {{0,-2},{-1,-2},{-3,-2},{3,-2},{-3,-1},{3,-1},{-2,-1},{-2,-3},{-1,-3},{2,-3},{2,-3},{1,-3},
                {-2,0},{2,0},
                {0,2},{1,2},{3,2},{-3,2},{3,1},{-3,1},{2,1},{2,3},{1,3},{-2,3},{-2,3},{-1,3}}
        };
    public void runPattern(int yes, int x, int y)
    {
        for (int i=0; i<patt[yes].length; i++)
        {
            List <Cell> cells = getObjectsAt(x+patt[yes][i][0], y+patt[yes][i][1], Cell.class);
            if (cells.size()>0)
                cells.get(0).setLive(true);
        }
    }

    private void addCells()
    {
        for (int i=1; i<29; i++)
            for (int j=1; j<29; j++)
                if (random)
                    addObject(new Cell(Greenfoot.getRandomNumber(10)<3), i, j);
                else addObject(new Cell(), i, j);
    }

    private void prepare()
    {
        Button buttonA = new Button("RUN");
        addObject(buttonA,15,0);
        Button buttonB = new Button("CLEAR");
        addObject(buttonB,25,0);
        Button buttonC = new Button("RANDOM");
        addObject(buttonC,5,0);
        Button button1 = new Button("Glider");
        addObject(button1,2,29);
        Button button2 = new Button("10-In-A-Row");
        addObject(button2,8,29);
        Button button3 = new Button("Pulsar");
        addObject(button3,14,29);
        Button button4 = new Button("Toaster");
        addObject(button4,18,29);
        Button button5 = new Button("Toad");
        addObject(button5,22,29);
        Button button6 = new Button("Washer");
        addObject(button6,27,29);
    }
}
