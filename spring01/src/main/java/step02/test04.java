package step02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import step01.Computer;

public class test04 {

	public static void main(String[] args) {
		ApplicationContext context = new  ClassPathXmlApplicationContext(
				"step02/application-context04.xml");
	}

}
