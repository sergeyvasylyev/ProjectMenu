package com.vasylyev;

import com.vasylyev.config.AppConfig;
import com.vasylyev.view.MainMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MainMenu menu = (MainMenu) context.getBean("menu");
        menu.showMenu();
    }
}
