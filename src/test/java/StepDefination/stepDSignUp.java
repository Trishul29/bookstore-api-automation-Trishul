package StepDefination;
import PojoClasses.create.SignUpRequestBody;
import PojoClasses.create.SignUpResponse;
import modules.clientRequest.BaseClientHelper;
import modules.clientService.SignUpService;

import static org.testng.Assert.assertEquals;

public class stepDSignUp extends BaseClientHelper {

   // public static String propertyPath = System.getProperty("user.dir") + "/src/main/resources/data.properties";
   // public static Properties properties = FileUtility.loadProperties(propertyPath);
    SignUpService signUpService=new SignUpService();
     SignUpRequestBody signUpRequestBody;
     SignUpResponse signUpResponse;

}


