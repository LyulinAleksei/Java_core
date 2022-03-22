package lesson1;


public class Team {

    String nameTeam ;

    Competitor[] partner = new Competitor[3];
   // Animal[] zoo = {new Cat("Murzik"), new Dog("Izzy")};
    // Формируем команду
    public Team(String nameTeam, Competitor com1, Competitor com2, Competitor com3 ) {
        this.nameTeam  = nameTeam;

        partner[0] = com1;
        partner[1] = com2;
        partner[2] = com3;

    }
    // Вывод информации кто прошел дистанцию
    public void passedTheDistance() {
        for (Competitor com : partner) {
            // Если участник onDistance == true, выводим его
            if (com.isOnDistance()) {
                com.info();
            }
        }
    }

    // Вывод информации о членах команды
    public void showResults() {
        for (Competitor com : partner) {
            com.info();
        }
    }

    // геттер участников команды
    public Competitor[] getTeammates() {
        return partner;
    }
}
