package com.gradlic.javafx;


import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

@Component
public class StageListener implements ApplicationListener<StageReadyEvent> {
	
	private final String applicationTitle;
	private final Resource fxml;
	private final ApplicationContext ac;
	
	StageListener(@Value("${spring.application.ui.title}") String applicationTitle, @Value("classpath:/ui.fxml") Resource resource,
			ApplicationContext ac) {
		this.applicationTitle = applicationTitle;
		this.fxml = resource;
		this.ac = ac;
		
	}

	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		
		try {
			Stage stage = event.getStage();
			URL url = this.fxml.getURL();
			FXMLLoader loader = new FXMLLoader(url);
			/*loader.setControllerFactory(new Callback<Class<?>, Object>() {
				
				@Override
				public Object call(Class<?> param) {
					return ac.getBean(param);
				}
			});*/
			
			//loader.setControllerFactory(param -> ac.getBean(param));
			loader.setControllerFactory(ac::getBean);
			
			Parent root = loader.load();
			Scene scene = new Scene(root, 600, 600);
			stage.setScene(scene);
			stage.setTitle(this.applicationTitle);
			stage.show();
			
		} catch (IOException e) {
			
			throw new RuntimeException(e);
		}
	}

}
