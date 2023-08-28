package pl.coderslab.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class HelloController {
    private Map<Integer, String> mapOfWorkers = new HashMap<>();
    @GetMapping("/workers")
    public String workersAction(Model model) throws IOException {

        //Classloader, żeby nie używać ścieżki bezwzględnej
        ClassLoader classLoader = getClass().getClassLoader();
        Map<Integer, String> mapOfWorkers = loadWorkersFromFile(classLoader.getResource("Workers.txt").getFile());

        Random r = new Random();
        int randomNumber = r.nextInt(30);
//        System.out.println(randomNumber);
        String selectedWorker = mapOfWorkers.getOrDefault(randomNumber, "brak takiej osoby w spisie");
//        for (Map.Entry<Integer, String> entry : mapOfWorkers.entrySet()) {
//            System.out.println(entry);
//        }
        model.addAttribute("selectedWorker", selectedWorker);
//        System.out.println(selectedWorker);
        return "workers";
    }


    public Map<Integer, String> loadWorkersFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                int workersNumber = Integer.parseInt(parts[0]);
                String workersName = parts[1];
                mapOfWorkers.put(workersNumber, workersName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapOfWorkers;
    }
}
