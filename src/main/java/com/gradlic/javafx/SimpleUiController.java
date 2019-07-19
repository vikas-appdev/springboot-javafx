package com.gradlic.javafx;

import org.springframework.stereotype.Component;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

@Component
public class SimpleUiController {
	
	private final HostServices hostServices;
	
	
	SimpleUiController(HostServices hostServices) {
		this.hostServices = hostServices;
	}

	@FXML
	public Label label;
	
	@FXML
	public Button button;
	
	@FXML
	public void initialize() {
		this.button.setOnAction(actionevent -> 
		this.label.setText(this.hostServices.getDocumentBase()));
	}

}
