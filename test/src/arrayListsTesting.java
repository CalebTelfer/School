import java.util.ArrayList;

public class arrayListsTesting {
    public static void main (String[] args) {
        ArrayList<String> myArrayList = new ArrayList<String>();

        myArrayList.size(); // returns # of elements
        myArrayList.get(2); // returns 2nd element
        myArrayList.add("First String to add"); // appends to array
        myArrayList.add(1, "Right in the middle!"); // adds to pos 1, does not replace
        myArrayList.remove(3); // remove the 3rd element

        private static void swapRecords(int a, int b){
            String temp = myArrayList.get(a);
            myArrayList.set(a, myArrayList.get(b));
            myArrayList.set(b, temp);
        }

        /*
        isEmpty()
        indexOf(Object O)
        clear()
         */
    }
}
