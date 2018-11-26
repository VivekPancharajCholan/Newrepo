package Object.Repo.Demo;

public class UserDetails {
	
	private String Testcase;
	public String getTestcase() {
		return Testcase;
	}

	public void setTestcase(String testcase) {
		this.Testcase = testcase;
	}

	public String getBrowser() {
		return Browser;
	}

	public void setBrowser(String browser) {
		this.Browser = browser;
	}

	public String getExecute() {
		return Execute;
	}

	public void setExecute(String execute) {
		this.Execute = execute;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		this.Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		this.Password = password;
	}

	private String Browser;
	private String Execute;
	private String Username;
	private  String Password;
	
	public  UserDetails(String Testcase,String Browser, String Execute, String Username, String Password)
	{
		super();
		this.Testcase = Testcase;
		this.Browser = Browser;
		this.Execute = Execute;
		this.Username = Username;
		this.Password = Password;
	}
	

	
	
	
	
}
