package console;

import domain.Block;
import domain.Building;
import domain.House;
import service.Service;
import util.Reader;

import java.io.IOException;

public class MainConsole {
    private final Service<House, Block> service;


    public MainConsole(Service<House, Block> service) {
        this.service = service;
    }

    public static void printMenu() {
        System.out.println("1.Add bloc");
        System.out.println("2.Add casa");
        System.out.println("3.Delete building");
        System.out.println("4.Afisare de restaorat ");
        System.out.println("5.Afisare desc ");
    }

    public void run() {
        printMenu();
        while (true) {
            try {
                var opt = Reader.read_ln("Optiunea:");
                switch (opt) {
                    case "1" -> {
                        var b = Reader.read_ln("Bloc(an/nrAp/apOcup):").split("/");
                        if(Integer.parseInt(b[1]) == 0 || Integer.parseInt(b[1]) < Integer.parseInt(b[2])){
                            throw new Exception("Nu poti avea asa ceva");
                        }
                        this.service.addEnt2(new Block(b));
                    }
                    case "2" -> {
                        var b = Reader.read_ln("Casa(an/istoric(false/true)):").split("/");
                        this.service.addEnt1(new House(b));
                    }
                    case "3" -> {
                        int indice = 1;
                        for (var ent1 : this.service.returnAll1()) {
                            System.out.println(indice + ((House) ent1).toString());
                            indice++;
                        }
                        for (var ent1 : this.service.returnAll2()) {
                            System.out.println(indice + ent1.toString());
                            indice++;
                        }
                        var nr = Integer.parseInt(Reader.read_ln("Numarul cladiri:"));
                        if (nr <= 0 || nr > indice - 1) {
                            throw new Exception("Ai ales ceva ce nu exista!");
                        }
                        nr = nr - 1;
                        if (nr < this.service.returnAll1().size()) {
                            if(!this.service.returnAll1().get(nr).canBeDestoryed())
                                throw new Exception("Nu poti demola cladirea!");
                            this.service.delEnt1(this.service.returnAll1().get(nr));
                        }
                        if (nr >= this.service.returnAll1().size())
                            if(!this.service.returnAll2().get(nr - this.service.returnAll1().size()).canBeDestoryed())
                                throw new Exception("Nu poti demola cladirea!");
                            this.service.delEnt2(this.service.returnAll2().get(nr-this.service.returnAll1().size()));
                    }
                    case "4" -> {
                        this.service.afisareTotalaDupaAn();
                    }
                    case "5" -> {
                        this.service.afisareTotalDesc();
                    }
                    default -> {
                        return;
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } catch (RuntimeException e){
                System.out.println(e);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
}
}
