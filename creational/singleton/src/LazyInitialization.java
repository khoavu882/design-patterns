public class LazyInitialization {
    private static LazyInitialization instance;

    // Private constructor to prevent instantiation
    private LazyInitialization() {}

    // Public method to provide access to the instance
    public static LazyInitialization getInstance() {
        if (instance == null) {
            instance = new LazyInitialization();
        }
        return instance;
    }
}
