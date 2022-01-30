package files;

import java.io.File;

public class CopyFiles {
    public static void main(String[] args) {
        File copy = new File("H:\\one");
        File paste = new File("H:\\iti\\one");
        paste.mkdir();
        String []filesList = copy.list();

        for(String name : filesList){
            if(new File(copy, name).isFile()){

            }else{

            }
        }
    }
}
