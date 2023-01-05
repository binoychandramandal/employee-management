import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileManagement {
    public static void main(String[] args) throws Exception {
        String filePath = "/Users/binoy.mandal/all/java course/security-demo";
        File file1 = new File(filePath);
        File[] files = file1.listFiles();
//        for (File file:files){
//            if(file.isDirectory())
//            System.out.println(file.getName());
//        }

        File f1 = new File(filePath + "/hello");
        //  f1.mkdir();
        //f1.mkdirs()
        System.out.println(f1.getParent());


        File f2 = new File(filePath + "/hello1/hello2");
        f2.mkdirs();


        // write on a file
        FileOutputStream fos = new FileOutputStream(filePath + "/hello/abc.txt");
        fos.write("This is my first file writing \n".getBytes());
        fos.write("This is my Second file writing ".getBytes());
        fos.close();

        //Reading content
        FileInputStream fis = new FileInputStream(filePath + "/hello/abc.txt");
        byte[] contentBytes = fis.readAllBytes();
        System.out.println(new String(contentBytes));


        //reading using bufferedReader
        System.out.println("---------------------");
        BufferedReader bufferedReader  =new BufferedReader(new FileReader(filePath + "/hello/abc.txt"));

        String line=null;
        while((line=bufferedReader.readLine())!=null){
            System.out.println(line);
        }

      List<String> lines = Files.readAllLines(Paths.get(filePath + "/hello/abc.txt"));
       for(String l : lines){
           System.out.println(l);
       }
    }
}
