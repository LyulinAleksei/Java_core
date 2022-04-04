package lesson4;

import java.util.*;

class HomeWork4 {

    public static void main(String[] args) {
        task1();
        task2();
    }

    private static void task1() {
        Map<String, Integer> hm = new HashMap<>();
        String[] words = {
                "яблоко", "виноград", "апельсин",
                "банан", "мандарин", "груша",
                "слива", "киви", "арбуз",
                "дыня", "персик", "яблоко",
                "слива", "мандарин", "груша",
                "киви", "арбуз", "дыня", "слива" ,"слива"
        };
        System.out.println(Arrays.toString(words));

        for (String word : words) {
            if (hm.containsKey(word))
                hm.put(word, hm.get(word) + 1);
            else
                hm.put(word, 1);
        }
        System.out.println(hm);
    }

    private static void task2() {
        Directory directory = new Directory();

        directory.add("Антонов", "8999123321");
        directory.add("Антонов", "8912155326");
        directory.add("Бобров", "8917155552");
        directory.add("Бобров", "8913455672");
        directory.add("Антонов", "899999999");
        directory.add("Сидоров", "899111111");
        directory.add("Бобров", "89923231999");
        directory.add("Петров", "8988123113");
        directory.add("Сидоров", "8924325234");
        directory.add("Иванов", "89101234568");

        System.out.println( directory.get("Бобров"));
    }
}

class Directory {
    private final Map<String, List<String>> directory_hm = new HashMap<>();

    public void add(String surname, String phone_number) {
        List<String> phone_number_list;
        if (directory_hm.containsKey(surname)) {
            phone_number_list = directory_hm.get(surname);
        } else {
            phone_number_list = new ArrayList<>();
        }
        phone_number_list.add(phone_number);
        directory_hm.put(surname, phone_number_list);
    }

    public List<String> get(String surname) {
        return directory_hm.get(surname);
    }

}