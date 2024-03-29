package cs.webservice.impl;


import com.opensymphony.xwork2.ActionSupport;
import cs.engine.action.CustomerAccountEngineAction;
import cs.engine.spring.SpringEngine;
import cs.model.CustomerAccount;

public class CustomerAccountsWS extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String customerLogin;
	private String customerPassword;
	private double geolocLongitude;
	private double geolocLatitude;

	private CustomerAccount customerAccount;
	
	/**
	 * Getter and Setter
	 */
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCustomerLogin() {
		return customerLogin;
	}

	public void setCustomerLogin(String customerLogin) {
		this.customerLogin = customerLogin;
	}
	
	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	
	public double getGeolocLongitude() {
		return geolocLongitude;
	}

	public void setGeolocLongitude(double geolocLongitude) {
		this.geolocLongitude = geolocLongitude;
	}

	public double getGeolocLatitude() {
		return geolocLatitude;
	}

	public void setGeolocLatitude(double geolocLatitude) {
		this.geolocLatitude = geolocLatitude;
	}
	
	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}
	
	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}
	
	public String execute() throws Exception
	{
		System.out.println("call execute");
		
		System.out.println("id : "+id);
		System.out.println("customerLogin : " + customerLogin);
		System.out.println("customerPassword : " + customerPassword);

		
		CustomerAccountEngineAction customerAccountEngine = SpringEngine.getSpring().getCustomerAccountEngineAction();//new CustomerAccountEngineAction();

		// Ugly part
		if(id != null && geolocLongitude != 0)
		{
			System.out.println("geolocLongitude");
			customerAccountEngine.saveCustomerLocation(id, geolocLongitude, geolocLatitude);
		}
		else if(id != null)
		{
			System.out.println("pass by load");
			customerAccount = customerAccountEngine.load(id);
		}
		else
		{
			System.out.println("pass by identification");
			customerAccount = customerAccountEngine.identification(customerLogin, customerPassword);
		}
		
		System.out.println("customerAccount.getFirstName() : " + customerAccount.getFirstName());
		
		//System.out.println(customerAccount.getCustomerLogin());
		
		/*
		CustomerAccounts user = new CustomerAccounts();
		
		user.setCustomerLogin("login");
		user.setCustomerPassword("password");
		user.setCustomerType(0);
		
		user.setDatetimeLastCarSharing(null);
		user.setDatetimeLastConnection(null);
		user.setDatetimeLastOfferCreated(null);
		user.setDatetimeRegistration(null);
		
		user.setAcceptAnimals(0);
		user.setAcceptRadio(0);
		user.setAcceptSmoker(0);
		user.setAcceptToDiscuss(0);
		user.setAcceptToMakeADetour(0);
		
		oos = new ObjectOutputStream() {
		};
		oos.writeObject(user);
		
		ObjectInputStream ois = new ObjectInputStream(){ };
		
		CustomerAccounts user2 = (CustomerAccounts) ois.readObject();
		System.out.println(oos.toString());
		*/
		
		System.out.println("Fin du call execute");
		return SUCCESS;
  }
}  