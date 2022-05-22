package application.services;

import application.services.CustomerService;
import framework.annotations.Component;

@Component
public class CustomerServiceImpl implements CustomerService {

	@Override
	public String getFullName() {
		return "hamzaaitbenyissa";
	}
}
