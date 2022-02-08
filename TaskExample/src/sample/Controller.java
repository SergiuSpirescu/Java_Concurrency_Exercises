package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class Controller {

//    private Task<ObservableList<String>> task;

    @FXML
    private ListView listView;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label progressLabel;

    private Service<ObservableList<String>>  service;

    public void initialize() {
//        task = new Task<ObservableList<String>>() {
//            @Override
//            protected ObservableList<String> call() throws Exception {
////                Thread.sleep(1000);
////                String[] names = {"Tim Buchalka",
////                        "Sergiu Spirescu",
////                        "Tommy Vercetti",
////                        "Marjacu Dragos",
////                        "Nae Popescu",
////                        "Koreanu"};
////
////                ObservableList<String> employees = FXCollections.observableArrayList();
////
////                for(int i=0; i<6; i++) {
////                    employees.add(names[i]);
////                    updateMessage("Loading... " + names[i]);
////                    updateProgress(i + 1, 6);
//////                    System.out.println("Added the name: " + names[i]);
////                    Thread.sleep(600);
////                }
////
//////                Platform.runLater(new Runnable() {
//////                    @Override
//////                    public void run() {
//////                        listView.setItems(employees);
//////                    }
//////                });
////             return employees;
//            }
//        };

        service = new EmployeeService();

        listView.itemsProperty().bind(service.valueProperty());
        progressBar.progressProperty().bind(service.progressProperty());
        progressLabel.textProperty().bind(service.messageProperty());

        service.setOnRunning(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                progressBar.setVisible(true);
                progressLabel.setVisible(true);
            }
        });

        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                progressLabel.setVisible(false);
                progressBar.setVisible(false);
            }
        });

//        progressBar.setVisible(false);
//        progressLabel.setVisible(false);
        progressLabel.visibleProperty().bind(service.runningProperty());
        progressBar.visibleProperty().bind(service.runningProperty());
    }

    @FXML
    public void buttonPressed () {

        if (service.getState() == Service.State.SUCCEEDED) {
        service.reset();
        service.start();
        } else if(service.getState() == Service.State.READY) {
                service.start();
            }
        }
}
