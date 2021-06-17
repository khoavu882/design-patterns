import java.util.Arrays;
import java.util.List;

/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 3:15 PM
 */

class ProxyInternet implements Internet {
    private RealInternet realInternet = new RealInternet();
    private static List<String> bannedSites = Arrays.asList("banned.com", "blocked.com");

    public void connectTo(String serverHost) {
        if (bannedSites.contains(serverHost)) {
            System.out.println("Truy cập bị chặn: " + serverHost);
            return;
        }
        realInternet.connectTo(serverHost);
    }
}
