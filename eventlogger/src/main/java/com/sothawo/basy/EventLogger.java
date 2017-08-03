/*
 * (c) Copyright 2017 sothawo
 */
package com.sothawo.basy;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Properties;
import java.util.UUID;

/**
 * @author P.J. Meisch (pj.meisch@sothawo.com)
 */
public class EventLogger extends Application {
    private static final Logger logger = LoggerFactory.getLogger(EventLogger.class);

    private final ObservableList<TableEvent> tableEvents = FXCollections.observableList(new LinkedList<>());

    private EventStoreConsumer eventStoreConsumer;

    public EventLogger() {
        final Properties props = new KafkaProperties();
        props.put("group.id", UUID.randomUUID().toString());
        eventStoreConsumer = new KafkaEventStoreConsumer(props, KafkaTopics.ACCOUNT_EVENTS);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        final BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));
        layout.setCenter(createTableView());

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setTitle("basy events");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> eventStoreConsumer
                .consume(events ->
                        Platform.runLater(() ->
                                events.forEach(event -> tableEvents.add(0, new TableEvent(event))))))
                .start();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if (eventStoreConsumer != null) {
            eventStoreConsumer.close();
        }
    }

    @NotNull
    private TableView<TableEvent> createTableView() {
        final TableView<TableEvent> tableView = new TableView<>();

        final TableColumn<TableEvent, String> tableColumn1 = new TableColumn<>("type");
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("eventClass"));
        final TableColumn<TableEvent, String> tableColumn2 = new TableColumn<>("created");
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("creationTime"));
        final TableColumn<TableEvent, String> tableColumn3 = new TableColumn<>("description");
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableView.getColumns().addAll(tableColumn1, tableColumn2, tableColumn3);
        tableView.setItems(tableEvents);
        return tableView;
    }


}
