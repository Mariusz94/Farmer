import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class OperationOnBarn {
    public void deleteBarn(List<Barn> barnsList, Scanner scanner) {
        for (Barn barn : barnsList) {
            System.out.println(barn.getName());
        }
        System.out.println("Podaj nazwe stodoły którą chces usunąć");
        String barnToDelete = scanner.nextLine();

        Main main = new Main();
        File[] files = main.checkingTheLocation();

        for (File file : files) {
            if (file.toString().contains(barnToDelete)) {
                file.delete();
            }
        }
    }

    public void addBarn(Scanner scanner) {
        Random random = new Random();
        int iD = random.nextInt(899)+100;

        System.out.println("Podaj nazwe stodoły");
        String nameBarn = scanner.nextLine();
        File file = new File("D:\\Akademia kodu\\2.03.2018 Zajęcia\\Farmer\\Stodoły\\" + nameBarn + "-" + iD + ".txt");

        if (!file.exists() == true) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Jakie zwierzęta mają należećdo stodoły zachowaj format" +
                " np. COW-14-true, oddzielając zwierzęta przecinkami");

        String animalText = scanner.nextLine();
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(animalText.getBytes());

            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void removeAnimal(List<Barn> barnsList, Scanner scanner) {
        for (Barn barn : barnsList) {
            System.out.println(barn.getName());
        }
        System.out.println("Podaj nazwe stodoły z ktorej chcesz usunąc zwierzę");
        String barnToDelete = scanner.nextLine();

        Main main = new Main();
        File[] files = main.checkingTheLocation();

        for (File file : files) {
            if (file.toString().contains(barnToDelete)) {

                FileInputStream fileInputStream = null;

                StringBuilder stringBuilder = new StringBuilder();

                try {
                    fileInputStream = new FileInputStream(file);
                    int read;
                    while ((read = fileInputStream.read()) != -1) {
                        stringBuilder.append((char) read);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Skopiuj liste zwierząt pomijając te które mają zostać usunięte");
                System.out.println(stringBuilder);
                String textToPrint = scanner.nextLine();

                FileOutputStream fileOutputStream =null;

                try {
                    fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(textToPrint.getBytes());

                    fileOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    public void addAnimal(List<Barn> barnsList, Scanner scanner) {
        for (Barn barn : barnsList) {
            System.out.println(barn.getName());
        }
        System.out.println("Podaj nazwe stodoły do której chcesz dodać zwierzę/zwierzęta");
        String barnToDelete = scanner.nextLine();

        Main main = new Main();
        File[] files = main.checkingTheLocation();

        for (File file : files) {
            if (file.toString().contains(barnToDelete)) {


                System.out.println("Podaj zwierzętaa ktore chcesz dodać zachowójąc format (COW-10-true,SHEEP-4-false");

                String textToPrint = "," + scanner.nextLine();

                FileOutputStream fileOutputStream =null;

                try {
                    fileOutputStream = new FileOutputStream(file, true);
                    fileOutputStream.write(textToPrint.getBytes());

                    fileOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

}
