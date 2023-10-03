import com.privateInvestigator.util.SentenceGrouping;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.privateInvestigator.constants.Constants;

public class PrivateInvestigator {

    public static void main(String[] args){
        List<String> listOfSentences = new ArrayList<>();
        SentenceGrouping sentenceGrouping = new SentenceGrouping();
        System.out.println("Starting the process ");
        System.out.println("Stage 1: Read the file");
        try(BufferedReader br = new BufferedReader(new FileReader(Constants.INPUT_FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                listOfSentences.add(line);
            }
        } catch(IOException e){
            e.printStackTrace();
            System.out.println("An error has been caught : "+e.getMessage());
        }
        System.out.println(" Stage 2: Group the words");
        List<String> groupedSentences = sentenceGrouping.filterSentences(listOfSentences);
        System.out.println(" Stage 3: Write the response to a file");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Constants.OUTPUT_FILENAME))){
            for (String sentence : groupedSentences) {
                bw.write(sentence);
                bw.newLine();
            }
        } catch(IOException e){
            e.printStackTrace();
            System.out.println("An error has been caught : "+e.getMessage());
        }
        System.out.println("Completed. Check the output.");
    }
}
