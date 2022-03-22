package lesson1;

public class Course {
    Barrier[] course = new Barrier[3];
    // Создаем полосу препятствий
    public Course(Barrier b1, Barrier b2, Barrier b3) {
        course[0] = b1;
        course[1] = b2;
        course[2] = b3;
    }

    // Метод, который просит всю команду пройти полосу препятствий
    public void doIt(Team t) {
        System.out.println("\nTeam \"" + t.nameTeam + "\":\n");
        // Для каждого участника команды
        for (Competitor com : t.getTeammates()) {
            // Для каждого препятствия вызываем метод его прохождения текущим участником команды
            for (Barrier b: course) {
                b.doIt(com);
            }
        }
    }
}
