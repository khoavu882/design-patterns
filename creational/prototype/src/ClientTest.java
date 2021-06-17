public class ClientTest {
    public static void main(String[] args) {

        User user1 = new User("kai.vu.dev","kai.vu.dev@gmail.com",27);
        System.out.println(user1);
        User user2 = user1.clone();
        System.out.println(user2);

        System.out.println("==================");
        user1.setAge(30);
        System.out.println(user1);
        System.out.println(user2);
    }
}
