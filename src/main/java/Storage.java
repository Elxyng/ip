import tasklist.Deadline;
import tasklist.Event;
import tasklist.Task;
import tasklist.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {

    static void writeToFile(String fileContent) throws FileNotFoundException {
        File f = new File("/TIC4001 IP/data/duke.txt");

        PrintWriter pw = new PrintWriter(f);
        pw.println(fileContent);
        pw.close();
    }

    static void loadFile() throws IOException {
        File directory = new File("/TIC4001 IP/data");
        File f = new File(directory + "/duke.txt");
        if (! directory.exists()){
            directory.mkdir();
            f.createNewFile();
        }

        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            loadFormat(sc.nextLine());
        }
    }

    static void loadFormat(String fileContent) {
        System.out.println(fileContent);
        String[] storeArray = fileContent.split(" \\| ");
        try {
            if (storeArray[0].equals("T")){
                Task t= new ToDo(storeArray[2], isDone(Integer.parseInt(storeArray[1])));
                Duke.taskList.add(t);
            }
            else if (storeArray[0].equals("D")){
                try{
                    Task t= new Deadline(storeArray[2], isDone(Integer.parseInt(storeArray[1])),storeArray[3]);
                    Duke.taskList.add(t);
                }catch (ArrayIndexOutOfBoundsException e){
                    Task t= new Deadline(storeArray[2], isDone(Integer.parseInt(storeArray[1])));
                    Duke.taskList.add(t);
                }
            }
            else if (storeArray[0].equals("E")){
                try{
                    Task t= new Event(storeArray[2], isDone(Integer.parseInt(storeArray[1])),storeArray[3]);
                    Duke.taskList.add(t);
                }catch (ArrayIndexOutOfBoundsException e){
                    Task t= new Event(storeArray[2], isDone(Integer.parseInt(storeArray[1])));
                    Duke.taskList.add(t);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Boolean isDone (Integer value) {
        return value == 1;
    }
}