package com.sigamfe.aspect;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import com.sigamfe.configuration.constants.Titles;
import com.sigamfe.exception.BusinessException;
import com.sigamfe.exception.GenericException;
import com.sigamfe.exception.ValidationException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

@Configuration
@Aspect
public class ExceptionHandler {

	@AfterThrowing(pointcut = "execution(public * com.sigamfe.business.*.*(..))", throwing = "ex")
	public void handler(Throwable ex) {
		showErrorAlert(ex);
	}

	private static void showErrorAlert(Throwable ex) {

		Alert alert = new Alert(AlertType.ERROR);

		if (ex instanceof GenericException) {
			GenericException ge = (GenericException) ex;
			StringBuilder sb = new StringBuilder();
			if (ge instanceof ValidationException) {
				ValidationException ve = (ValidationException) ge;
				alert.setTitle(Titles.DIALOG_ERROR_VALIDATION);
				alert.setHeaderText("As seguintes validações falharam:");
				if (CollectionUtils.isNotEmpty(ge.getExcecoesFilhas())) {
					for (GenericException g : ge.getExcecoesFilhas()) {
						ValidationException v = (ValidationException) g;
						sb.append(v.getCampo());
						sb.append(":");
						sb.append(StringUtils.SPACE);
						sb.append(v.getMessage());
						sb.append(".");
						sb.append(StringUtils.LF);
					}
				} else {
					sb.append(ve.getCampo() + ":" + StringUtils.SPACE + ve.getMessage() + ".");
				}
			} else if (ge instanceof BusinessException) {
				alert.setTitle(Titles.DIALOG_ERROR_BUSINESS);
				alert.setHeaderText(null);
				sb.append(ge.getMessage());
			} else {
				alert.setTitle(Titles.DIALOG_ERROR);
				alert.setHeaderText("Ocorreu um erro!");
				sb.append(ge.getMessage());
			}
			alert.setContentText(sb.toString());
			alert.showAndWait();
			return;
		}

		if (ex instanceof javax.validation.ValidationException) {
			alert.setTitle(Titles.DIALOG_ERROR_VALIDATION);
			alert.setHeaderText("A seguinte validação falhou:");
			alert.setContentText(ex.getMessage());
		} else {
			// Se não for nenhuma das exceções acima, exibe o diálogo de exceção
			// desconhecida.
			alert.setTitle(Titles.DIALOG_ERROR);
			alert.setHeaderText("Ocorreu um erro desconhecido!");
			alert.setContentText(ex.getMessage());
		}

		// Create expandable Exception.
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("A pilha de exceção foi:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();
	}
}
