package com.example.laba22;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField getX1;

    @FXML
    private TextField getX2;

    @FXML
    private TextField getY1;

    @FXML
    private TextField getY2;

    @FXML
    private Canvas mainCanvas;

    @FXML
    private Button execute;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        execute.setOnAction(actionEvent -> {
            GraphicsContext ctx = mainCanvas.getGraphicsContext2D();
            ctx.clearRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
            int x1 = Integer.parseInt(getX1.getText());
            int y1 = Integer.parseInt(getY1.getText());
            int x2 = Integer.parseInt(getX2.getText());
            int y2 = Integer.parseInt(getY2.getText());
            draw(x1, y1, x2, y2, Color.RED, Color.BLUE);
        });
        getX1.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                execute.fire();
            }
        });
        getY1.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                execute.fire();
            }
        });
        getX2.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                execute.fire();
            }
        });
        getY2.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                execute.fire();
            }
        });

    }


    void draw(int x1, int y1, int x2, int y2, Color color1, Color color2) {
        GraphicsContext graphicsContext = mainCanvas.getGraphicsContext2D();
        PixelWriter pixelWriter = graphicsContext.getPixelWriter();
        int dx = x2 - x1;
        int dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncrement = dx / (float) steps;
        float yIncrement = dy / (float) steps;
        float x = x1;
        float y = y1;
        double distance = Math.sqrt(dx * dx + dy * dy);
        for (int i = 0; i <= steps; i++) {
            double pointDistance = Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
            double fraction = pointDistance / distance;
            int red = (int) (color1.getRed() * (1 - fraction) * 255 + color2.getRed() * fraction * 255);
            int green = (int) (color1.getGreen() * (1 - fraction) * 255 + color2.getGreen() * fraction * 255);
            int blue = (int) (color1.getBlue() * (1 - fraction) * 255 + color2.getBlue() * fraction * 255);
            Color color = Color.rgb(red, green, blue);
            pixelWriter.setColor((int) x, (int) y, color);
            x += xIncrement;
            y += yIncrement;
        }

        }
    }
