package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class EmployeeService extends Service<ObservableList<String>> {

    @Override
    protected Task<ObservableList<String>> createTask() {
        return new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws Exception {
                String[] names = {"Tim Buchalka",
                        "Sergiu Spirescu",
                        "Tommy Vercetti",
                        "Marjacu Dragos",
                        "Nae Popescu",
                        "Koreanu"};

                ObservableList<String> employees = FXCollections.observableArrayList();

                for(int i=0; i<6; i++) {
                    employees.add(names[i]);
                    updateMessage("Loading... " + names[i]);
                    updateProgress(i + 1, 6);
//                    System.out.println("Added the name: " + names[i]);
                    Thread.sleep(200);
                }

//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        listView.setItems(employees);
//                    }
//                });
                return employees;
            }
        };
    }
}
