import console.MainConsole;
import repository.FileRepository;
import service.Service;
import util.FileSystemProprieties;

public class Main {
    public static void main(String[] args) {
        try {
            FileSystemProprieties fileSystemProprieties = new FileSystemProprieties();
            MainConsole mainConsole = new MainConsole(new Service<>(new FileRepository<>(fileSystemProprieties.readProprieties("Cfile"))
                    , new FileRepository<>(fileSystemProprieties.readProprieties("Bfile"))));
            mainConsole.run();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
