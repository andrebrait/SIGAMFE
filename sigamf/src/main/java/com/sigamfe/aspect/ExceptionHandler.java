package com.sigamfe.aspect;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ExceptionHandler {

	@AfterThrowing(pointcut = "execution(public * com.sigamfe.*.*(..))", throwing = "exception")
	public void handler(JoinPoint joinPoint, Throwable exception) {
		showErrorAlert(exception);
	}

	private void showErrorAlert(Throwable ex) {
		final Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setHeaderText("Ocorreu um erro!");
		alert.setContentText(ex.getMessage());

		// Create expandable Exception.
		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		final String exceptionText = sw.toString();

		final Label label = new Label("O stack trace do erro foi:");

		final TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		final GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();
	}
}
