package fileMenager;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Maciej-Plonka
 * @version 1.7
 */
public class FileManager {
    /**
     * user file
     */
    private static final File userFile = new File("/Users/maciejmac/Downloads/WebLab3/src/main/java/pl/polsl/lab1/votesystem/fileMenager/Users.txt");


    /**
     * candidate file
     */
    private static final File candidateFile = new File("/Users/maciejmac/Downloads/WebLab3/src/main/java/pl/polsl/lab1/votesystem/fileMenager/Candidate.txt");

    /**
     * Getter of user file
     * @return user file
     */
    public static File getUserFile(){
        return userFile;
    }

    /**
     * Getter of candidates file
     * @return candidate file
     */
    public static File getCandidateFile(){
        return candidateFile;
    }
    /**
     *
     * @param file from which we will read data;
     * @return list of lists of String representing each line of file
     *
     */


    public static List<List<String>> Reader(File file) {
        List<List<String>> myList = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            while(in.ready()) {
                String line = in.readLine();
                String[] parts = line.split(" ");
                ArrayList<String> lineList = new ArrayList<>();
                Collections.addAll(lineList, parts);
                myList.add(lineList);
            }
        }
        catch(Exception e) {
            System.out.println("Error reading File");
        }
        return myList;
    }

    /**
     * Handle adding user to end of file
     * @param user that should be added to file;
     * @param add - file name parameter;
     * @throws IOException
     */

    public static void addToFile(String user,File add) throws IOException {
        FileWriter fileWriter = new FileWriter(add, true);
        BufferedWriter write = new BufferedWriter(fileWriter);
        write.newLine();
        write.write(user);
        write.flush();
        write.close();
    }

    /**
     * Handle updating list of candidates
     * @param line that should be added to file;
     * @param add - file name parameter;
     */

    public static void ToFile(String line, File add) throws IOException {
        FileWriter fileWriter = new FileWriter(add);
        BufferedWriter write = new BufferedWriter(fileWriter);
        write.write(line);
        write.flush();
        write.close();
    }

}