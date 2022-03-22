package lesson1;

public class Main {

    public static void main(String[] args) {
        Course c = new Course(new Cross(80), new Water(5), new Wall(5)); // Создаем полосу препятствий
        Team team = new Team("Heroes", new Human("Alex"), new Cat("Barsik"), new Dog("Bobik")); // Создаем команду
        c.doIt(team); // Просим команду пройти полосу

        System.out.println("\nWinners:");
        team.passedTheDistance();

        System.out.println("\nResult:");// Показываем результаты
        team.showResults();
    }
}
