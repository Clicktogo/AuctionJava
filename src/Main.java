// Filip Garcia
public class Main {

    public static void main(String[] args) {
        IO io = new IO();
        Register register = new Register();
        System.out.println("Welcome to the dog auction program!");

        register.printCommands();
        System.out.println("------------------------------");
        System.out.println();
        io.testingSetup();
        io.command();
    }
}
