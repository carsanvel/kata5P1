package kata5p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MailListReader {

    public static List<String> read(String fileName) {
        List<String> list = new ArrayList();
        
        try {
            IteratorReader iterator = new IteratorReader(new BufferedReader(new FileReader(new File(fileName))));
            for (String line : iterator) {
                if(Mail.isMail(line)) {
                    list.add(line);
                }
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Error");
        }
        return list;
    }

    
}
