package MoPhoneList.phoneBook;
import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws FileNotFoundException {

        String[] names = new String[6];
        String[] numbers = new String[6];

        System.out.println("\n\t\t\t\t\tWelcome to your contacts book\n");

        String resetAnswer;
        Scanner mainScan = new Scanner(System.in);
        System.out.println("Do you want to reset your phonebook?");
        resetAnswer = mainScan.next();
        if (resetAnswer.equalsIgnoreCase("yes")){
            askInfo(names, numbers);
            updateArray(names, numbers);//important if I want to save the changes
        }

        readTheFile(names, numbers);

        //System.out.println(Arrays.toString(names));
        //System.out.println(Arrays.toString(numbers));

        while (true) {
            //System.out.println("\n\t\t\t\t\tWelcome to your contacts book\n");
            System.out.println("Press 1 to view list");
            System.out.println("press 2 to change a contact");
            System.out.println("press 3 to exit");
            //System.out.println("press 0 to reset your phonebook");

            int userAnswer = mainScan.nextInt();

            switch (userAnswer) {

                case 0:
                    System.out.println("Are you sure? You can exit by pressing -1.");
                    String conf = mainScan.next();
                    if (conf.equalsIgnoreCase("yes")){
                        askInfo(names, numbers);
                        updateArray(names, numbers);//important if I want to save the changes
                    }

                case 1:
                    System.out.println("you want to view your contacts\n");
                    viewList(names, numbers);
                    break;

                case 2:
                    System.out.println("you want to add a contact\n");
                    modifyArray(names, numbers);

                    //System.out.println(Arrays.toString(names));
                    //System.out.println(Arrays.toString(numbers));

                    updateArray(names, numbers);

                    readTheFile(names, numbers);

                    //System.out.println(Arrays.toString(names));
                    //System.out.println(Arrays.toString(numbers));
                    break;

                default:
                    System.out.println("your are exiting");
                    return;

            }
        }

    }

    private static void viewList(String[] names, String[] numbers) {
        for(int k = 0; k < names.length; k++){
            System.out.printf("#%1d-Name: %-10s No.: %-10s\n", (k+1), names[k], numbers[k]);
        }
    }

    private static void askInfo(String[] names, String[] numbers) {
        Scanner info = new Scanner(System.in);
        String name;
        String number;
        int exitBotton = 0;
        for(int j = 0; j < names.length; j++){
            System.out.println("Enter contact #"+(j+1)+" name");
            name = info.next();
            if(name.equals("-1")){
                break;
            }else {
                names[j] = name;
                System.out.println("Enter number");
                number = info.next();
            }
            if(number.equals("-1")){
                break;
            }else {
                numbers[j] = number;
            }
        }
    }

    private static void updateArray(String[] names, String[] numbers) throws FileNotFoundException {
        //correct formating the array to the file
        PrintStream filePrint = new PrintStream(new File("contacts001.txt"));//kan den laves til class constant???
        for(int i = 0; i < names.length; i++){
            filePrint.printf("Name: %-10s No. %-10s\n", names[i], numbers[i]);
        }
    }

    private static void modifyArray(String[] names, String[] numbers) {
        int accesContact;
        String newName;
        String newNum;
        Scanner modify = new Scanner(System.in);
        System.out.println("Which contact do you like to change? 1 - 6");
        accesContact = modify.nextInt();
        System.out.println("Enter new name");
        newName = modify.next();
        System.out.println("Enter new number");
        newNum = modify.next();
        names[accesContact-1] = newName;
        numbers[accesContact-1] = newNum;
    }

    private static void readTheFile(String[] names, String[] numbers) throws FileNotFoundException {
        //1.Create a Scanner
        Scanner fileScan = new Scanner(new File("contacts001.txt"));
        //2. While loop
        int count = 0;
        while(fileScan.hasNextLine()) {
            String line = fileScan.nextLine();
            Scanner lineScan = new Scanner(line);
            String nameDot = lineScan.next();
            String name = lineScan.next();
            String numDot = lineScan.next();
            String number = lineScan.next();
            names[count] = name;
            numbers[count] = number;
            count++;
        }
    }
}
