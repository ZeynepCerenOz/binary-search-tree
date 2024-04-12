import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        String fileName ="random_data.csv";
        BSTNameSurname bstNs=new BSTNameSurname();
        BSTID bstId=new BSTID();

        try {
            BufferedReader br=new BufferedReader(new FileReader(fileName));
            String line;

            br.readLine();
            while ((line=br.readLine())!=null){
                String[] fields=line.split(",");


                int id=Integer.parseInt(fields[0].trim());
                String name=fields[1].trim();
                String surname=fields[2].trim();
                int age=Integer.parseInt(fields[3].trim());
                double GPA=Double.parseDouble(fields[4].trim());

                Student student=new Student(id,name,surname,age,GPA);

                bstNs.insertByNameSurname(student);
                bstId.insertByID(student);
                //System.out.println(fields[0]+" "+fields[1]+" "+fields[2]+" "+fields[3]+" "+fields[4]);

            }
            br.close();

           bstId.findStudentByID(344156);

            //first surname, then name is written to the argument
            bstNs.findStudentByName("Baker Nicole Dunn");


            //bstId.findStudentIntervalById(947822,948962);

            //first surname, then name is written to each argument
            //bstNs.findStudentIntervalByName("Blackburn Brian Garcia","Blackburn William Lambert");

        } catch (IOException e) {
            System.out.println("file read error"+e.getMessage());
        }



    }
}
