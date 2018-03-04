import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.objects.NativeArray.sort;

public class Main {
    static int condition = 0;

    public static void main(String[] args) {
        //while(condition == 0) {
        //   Main main = new Main();
        //    List<Barn> barnsList = main.barnAnimal();
        //    main.Menu(barnsList);
        // }

        Random random = new Random();
        int iD = random.nextInt(899)+100;
        for (int i = 0; i < 100; i++) {
            System.out.println(random.nextInt(899)+100);
        }
    }

    //poszukiwanie plików
    public File[] checkingTheLocation() {
        String dirPathname = "D:\\Akademia kodu\\2.03.2018 Zajęcia\\Farmer\\Stodoły";
        File directory = new File(dirPathname);
        File[] files = directory.listFiles();
        return files;
    }

    //odczyt z plików i tworzenie zwierząt z stoodołą
    public Barn readFileString(File file) {

        String name[] = file
                .toString()
                .replace("D:\\Akademia kodu\\2.03.2018 Zajęcia\\Farmer\\Stodoły\\", "")
                .replace(".txt", "")
                .split("-");

        StringBuilder stringBuilder = new StringBuilder();

        FileInputStream fileInputStream = null;

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

        String animalText = stringBuilder.toString();
        animalText = animalText.substring(0, animalText.length() - 0);

        String[] animalsTable = animalText.split(",");
        List<Animal> animalList = new ArrayList<>();
        for (String animal : animalsTable) {
            String[] animalTable = animal.split("-");
            animalList.add(new Animal(animalTable[0], Integer.parseInt(animalTable[1]), Boolean.parseBoolean(animalTable[2])));
        }

        Barn barn = new Barn(name[0], Integer.parseInt(name[1]), animalList);
        return barn;
    }

    //Lista stodół ze zwierzętami
    public List<Barn> barnAnimal() {
        Main main = new Main();
        File[] file = main.checkingTheLocation();

        List<Barn> barnList = new LinkedList<>();
        if (file.length >= 1) barnList.add(main.readFileString(file[0]));
        if (file.length >= 2) barnList.add(main.readFileString(file[1]));
        if (file.length >= 3) barnList.add(main.readFileString(file[2]));
        if (file.length >= 4) barnList.add(main.readFileString(file[3]));
        if (file.length >= 5) barnList.add(main.readFileString(file[4]));
        if (file.length >= 6) barnList.add(main.readFileString(file[5]));
        if (file.length >= 7) barnList.add(main.readFileString(file[6]));
        if (file.length >= 8) barnList.add(main.readFileString(file[7]));
        if (file.length >= 9) barnList.add(main.readFileString(file[8]));
        if (file.length >= 10) barnList.add(main.readFileString(file[9]));
        return barnList;
    }

    //suma wszystkich zwierząt
    public List<Animal> animalSumList(List<Barn> barnList) {
        List<Animal> animalSum = new LinkedList<>();
        for (int i = 0; i < barnList.size(); i++) {
            List<Animal> animal1 = barnList.get(i).getList();
            for (int i1 = 0; i1 < barnList.get(i).getList().size(); i1++) {
                animalSum.add(animal1.get(i1));
            }
        }
        Collections.sort(animalSum, Comparator.comparingInt(p -> p.getAge()));

        return animalSum;
    }

    //Najpopularniejsze zwierzęw stajni
    public String mostPopularAnimalInBarn(List<Barn> barnsList) {
        Main main = new Main();
        List<Animal> listAnimal = main.animalSumList(barnsList);
        int mostPopularAnimalInBarn = 0;
        int positionMostPopularAnimalInBarnAtListAnimal = 0;
        for (int i = 0; i < listAnimal.size(); i++) {
            int counter = 1;
            int i1 = i + 1;
            for (; i1 < listAnimal.size(); i1++) {
                if (listAnimal.get(i).getName().equals(listAnimal.get(i1).getName())) counter++;
            }
            if (mostPopularAnimalInBarn < counter) {
                mostPopularAnimalInBarn = counter;
                positionMostPopularAnimalInBarnAtListAnimal = i;
            }
        }
        return listAnimal.get(positionMostPopularAnimalInBarnAtListAnimal).getName();
    }

    //Menu
    public void Menu(List<Barn> barns) {
        Scanner scanner = new Scanner(System.in);

        Main main = new Main();
        List<Animal> listAnimal = main.animalSumList(barns);

        List<Barn> barnsList = main.barnAnimal();

        OperationOnBarn operationOnBarn = new OperationOnBarn();

        System.out.println("1. Wyświetl 5 najmłodszych zwierząt");
        System.out.println("2. Wyświetl 5 najstarszych zwierząt");
        System.out.println("3. Stodoła z największą ilością zwierząt");
        System.out.println("4. Wyświetl najliczinejszy gatunek");
        System.out.println("5. Szczepione zwierzęta");
        System.out.println("6. Nie szczepione zwierzęta");
        System.out.println("7. Dodaj stodołe");
        System.out.println("8. Usuń stodołe");
        System.out.println("9. Dodaj zwierzę");
        System.out.println("10. Usuń zwierzę");
        System.out.println("0. Koniec programu");

        String textString = scanner.nextLine();
        int textStringInt = Integer.parseInt(textString);

        switch (textStringInt) {
            case 1:
                main.animalSumList(barnsList).stream().limit(5).forEach(System.out::println);
                break;
            case 2:
                for (Animal animal : listAnimal.subList(listAnimal.size() - 5, listAnimal.size())) {
                    System.out.println(animal);
                }
                break;
            case 3:
                System.out.println(barnsList.get(mostNumerousBarn(barnsList)).getName());
                break;
            case 4:
                System.out.println(main.mostPopularAnimalInBarn(barnsList));
                break;
            case 5:
                for (Animal animal : listAnimal) {
                    if (animal.isVaccinated() == true) System.out.println(animal);
                }
                break;
            case 6:
                for (Animal animal : listAnimal) {
                    if (animal.isVaccinated() == false) System.out.println(animal);
                }
                break;
            case 7:
                operationOnBarn.addBarn(scanner);
                break;
            case 8:
                operationOnBarn.deleteBarn(barnsList, scanner);
                break;
            case 9:
                operationOnBarn.addAnimal(barnsList, scanner);
                break;
            case 10:
                operationOnBarn.removeAnimal(barnsList, scanner);
                break;
            case 0:
                condition++;
                break;
        }
        System.out.println("----------------------------------------------");

    }

    //Najliczniejsza stodoła
    public int mostNumerousBarn(List<Barn> barnsList) {
        int biggestBarn = 0;
        int counter = 0;
        for (int i = 0; i < barnsList.size(); i++) {
            List<Animal> animalAtBarn = barnsList.get(i).getList();
            if (counter < animalAtBarn.size()) {
                counter = animalAtBarn.size();
                biggestBarn = i;
            }
        }
        return biggestBarn;
    }
}
