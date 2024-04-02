// Basisklasse zum Erweitern
// siehe TODOs
package at.spengergasse;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class SpaceBase {
    private String name;
    private int posX;
    private int posY;
    private List<SpaceShip> shipList;

    public SpaceBase() {
        name = "International Space Station";
        posX = 0;
        posY = 0;
        shipList = new LinkedList<>();
    }

    public SpaceBase(String name, int posX, int posY) throws ApplicationException {
        setName(name);
        setPosX(posX);
        setPosY(posY);
        shipList = new LinkedList<>();
    }

    public int getPosX() {
        return posX;
    }

    private void setPosX(int posX) throws ApplicationException {
        if (posX >= 0) {
            this.posX = posX;
        }
        else {
            throw new ApplicationException("setPosX: posX-value is not valid (" + posX + ")");
        }
    }

    public int getPosY() {
        return posY;
    }

    private void setPosY(int posY) throws ApplicationException {
        if (posX >= 0) {
            this.posY = posY;
        }
        else {
            throw new ApplicationException("setPosY: posY-value is not valid (" + posY + ")");
        }
    }

    public void setName(String name) throws ApplicationException {
        if (name != null) {
            this.name = name;
        }
        else {
            throw new ApplicationException("setName: null-value for name");
        }
    }

    public String getName() {
        return name;
    }

    public boolean docking(SpaceShip spaceship) throws ApplicationException {
        // TODO
        // ..
        shipList.add(spaceship);
        return true;
    }

    // variadic argument
    public void multiDocking(SpaceShip... spaceShips) throws ApplicationException {
        for (SpaceShip spaceShip : spaceShips) {
            docking(spaceShip);
        }
    }

    public SpaceShip moveShipTo(Long id, SpaceBase spaceBase) {
        // TODO
        return null;
    }

    private SpaceShip shipWithId(Long id) {
        // TODO
        return null;
    }

    public List<SpaceShip> arrangeShips() {
        // TODO
        return new LinkedList<>(shipList);
    }

    public List<SpaceShip> arrangeShipsByFuel() {
        // TODO
        return new LinkedList<>(shipList);
    }

    public Long buildShip() throws ApplicationException {
        // TODO
        return 0L;
    }

    // save Binary Data
    // ObjectOutputStream
    // FileOutputStream

    // read Binary Data
    // ObjectInputStream
    // FileInputStream

    // save textual Data
    // FileWriter
    // BufferedWriter

    // reading textual Data
    // FileReader
    // BufferedReader

    public void loadCSV(String pathToFile) throws ApplicationException {

        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            // SpaceShip,102,0,0,1000
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] tokens = line.split(",");
                    if (tokens.length == 5 && tokens[0].equals("SpaceShip")) {

                        Long id = Long.parseLong(tokens[1]);
                        int posX = Integer.parseInt(tokens[2]);
                        int posY = Integer.parseInt(tokens[3]);
                        double fuel = Double.parseDouble(tokens[4]);

                        var spaceship = new SpaceShip(id, posX, posY, fuel);
                        shipList.add(spaceship);
                    } else {
                        // TODO build up error object
                        // System.err.println("Fehler: "+ e.getMessage());
                    }
                }
                catch (NumberFormatException | ApplicationException e) {
                    // TODO build up error object
                    // Intentionally swallow the excpetion
                    System.err.println("Fehler: " + e.getMessage());
                }
            }
        }
        catch (FileNotFoundException e) {
            throw new ApplicationException(
                String.format("File %s is not found",
                    pathToFile), e);
        }
        catch (IOException e) {
            throw new ApplicationException(
                String.format("There was a problem while loading to the file %s",
                    pathToFile), e);
        }
    }

    public void saveCSV(String pathToFile) throws ApplicationException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathToFile))) {

            for (SpaceShip spaceShip : shipList) {
                String csvString = String.format(
                    Locale.ENGLISH,
                    "%s,%d,%d,%d,%.2f%s",
                    spaceShip.getClass().getSimpleName(),
                    spaceShip.getId(), spaceShip.getPosX(),
                    spaceShip.getPosY(), spaceShip.getFuel(),
                    System.lineSeparator());

                bw.write(csvString);
            }
        }
        catch (FileNotFoundException e) {
            throw new ApplicationException(
                String.format("File %s is not found",
                    pathToFile), e);
        }
        catch (IOException e) {
            throw new ApplicationException(
                String.format("There was a problem while saving to the file %s",
                    pathToFile), e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SpaceBase ");
        sb.append("'").append(name).append("'");
        sb.append(", position ").append(posX);
        sb.append("/").append(posY);
        if (!shipList.isEmpty()) {
            sb.append(", ").append(shipList.size()).append(" ").append(shipList.size() > 1 ?
                "ships" : "ship").append(" in docks\n");
            for (SpaceShip spaceship : shipList) {
                sb.append(spaceship).append("\n");
            }
        }
        else {
            sb.append(", no ships");
        }
        return sb.toString();
    }
}
