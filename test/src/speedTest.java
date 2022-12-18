import java.io.IOException;


//Lets test how fast your computer can process your code!
public class speedTest
{

    //main method
    public static void main(String[] args) throws IOException
    {
        //Some variables to help us out!
        long time, time2, finalTime;
        int i;

        //Get the current time in milliseconds
        time = System.currentTimeMillis();

        //Output time to the screen
        System.out.println("The Current Time is: " + time);

        //Here is some code for the computer to run.
        //You may have to change 500 to a higher number
        //if your computer is really really fast
        for(i = 0; i < 500; i++)
        {
            System.out.print(".");
        }

        //Get the time at the end of the program
        time2 = System.currentTimeMillis();

        //Output the end time and the difference in times!
        System.out.println("");
        System.out.println("The End Time is: "+time2);


        finalTime = time2 - time;
        System.out.println("It took " +finalTime+ " Milliseconds");
    }

}


