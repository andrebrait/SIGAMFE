package com.sigamfe.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.controlsfx.dialog.ExceptionDialog;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ExceptionHandler {

	@Pointcut("execution(public * com.sigamfe.business.*.*(..))")
	public void businessMethods() {

	}

	@AfterThrowing(pointcut = "businessMethods()", throwing = "ex")
	public void handler(Throwable ex) {
		showErrorAlert(ex);
	}

	private void showErrorAlert(Throwable ex) {
		ExceptionDialog dialog = new ExceptionDialog(ex);
		dialog.setTitle("Erro");
		dialog.setHeaderText("Ocorreu um erro!");
		dialog.setContentText(ex.getMessage());
		dialog.showAndWait();
	}
}
