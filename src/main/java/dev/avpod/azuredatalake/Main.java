package dev.avpod.azuredatalake;

import com.microsoft.azure.datalake.store.ADLStoreClient;
import com.microsoft.azure.datalake.store.oauth2.AccessTokenProvider;
import com.microsoft.azure.datalake.store.oauth2.AzureADToken;
import com.microsoft.azure.datalake.store.oauth2.ClientCredsTokenProvider;
import com.microsoft.azure.datalake.store.oauth2.UserPasswordTokenProvider;

/**
 * @author Andrei.Podznoev
 * Date    08.12.2017.
 */
public class Main {
    private static String clientId = "FILL-IN-HERE";
    private static String authTokenEndpoint = "FILL-IN-HERE";
    private static String clientKey = "FILL-IN-HERE";

    AccessTokenProvider provider = new ClientCredsTokenProvider(authTokenEndpoint, clientId, clientKey);


    public static void main(String[] args) throws Exception {
        String tenant = System.getProperty("tenant");
        String subscriptionId = System.getProperty("subscription");
        String secret = System.getProperty("secret");
        String restPath = "https://login.microsoftonline.com/" +
                tenant +
                "/oauth2/token";
        AccessTokenProvider provider = new ClientCredsTokenProvider(restPath, subscriptionId, secret);
        AzureADToken token = provider.getToken();
        System.out.println(token.accessToken);
        ADLStoreClient client = ADLStoreClient.createClient("btgusqlworkshop.azuredatalakestore.net", provider);
        System.out.println(client.checkExists("catalog"));
    }
}
