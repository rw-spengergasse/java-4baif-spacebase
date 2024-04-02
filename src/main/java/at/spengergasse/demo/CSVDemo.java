package at.spengergasse.demo;

import at.spengergasse.ApplicationException;
import at.spengergasse.SpaceBase;
import at.spengergasse.SpaceShip;

public class CSVDemo {
    public static void main(String[] args) throws ApplicationException {
         var spaceShip1 = new SpaceShip();
         spaceShip1.moveTo(10, 200);

         var spaceShip2 = new SpaceShip();
        spaceShip1.moveTo(20, 400);

         var spaceShip3 = new SpaceShip();
        spaceShip1.moveTo(30, 600);

         var spaceBase = new SpaceBase();
         spaceBase.multiDocking(spaceShip1, spaceShip2, spaceShip3);

//         spaceBase.saveCSV("/Users/dalai/scratch/spacebase.csv");

         spaceBase.loadCSV("/Users/dalai/scratch/spacebase.csv");
    }
}
