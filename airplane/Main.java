import ProgramController.Programcontroller;
import ProgramController.SaveFile;
import Views.RegisterMenu;

public class Main {

    public static void main(String[] args) {

        Programcontroller programcontroller = new Programcontroller();
        SaveFile.load(programcontroller);
        RegisterMenu registerMenu = new RegisterMenu(programcontroller);
        registerMenu.run();
    }
}