package application;

import framework.Injector;

public class App {

	public static void main(String[] args) {
		Injector.startApplication(App.class);
		Injector.getService(CustomerComponent.class).FullName();
		System.out.println("done ✔");
		System.out.println("Thank you for using our DI Framework ❤");
	}

}
