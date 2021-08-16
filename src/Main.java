// Filip Garcia
public class Main {

    public static void main(String[] args) {
        IO io = new IO();
        Registry registry = new Registry();
        System.out.println("Welcome to the dog auction program where you can give a rescue dog a lovely new home!");
        System.out.println();

        registry.printCommands();
        System.out.println("------------------------------");
        System.out.println();
        io.testingSetup();
        io.command();
    }
}
