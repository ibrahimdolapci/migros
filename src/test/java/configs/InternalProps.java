package configs;

public class InternalProps extends AbstractProps
{
    private String phoneNumber;


    public InternalProps()
    {
        loadConfigProperties("internal_config.properties");

        this.phoneNumber = configProps.getProperty("phoneNumber");
    }

    //Getters and Setters


    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
}
