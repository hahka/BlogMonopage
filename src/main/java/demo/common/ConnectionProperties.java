package demo.common;

import java.util.Properties;

/**
 * Created by thibautvirolle on 08/10/15.
 */
public class ConnectionProperties extends Properties{

    public ConnectionProperties(boolean remote)
    {
        if(remote)
        {
            setProperty("user","zxnrefrpvwoodi");
            setProperty("password","64s1lFRS8jW7NueE_FbDLt-Osx");
            setProperty("ssl","true");
            setProperty("sslfactory","org.postgresql.ssl.NonValidatingFactory");
        }
        else
        {
            setProperty("user","sa");
            setProperty("password","");
        }

    }


}
