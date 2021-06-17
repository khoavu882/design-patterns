public class CandyFactory {
    private CandyFactory(){}

    public static Candy getCandy(CandyType candyType){
        return switch (candyType) {
            case MINTY_CANDY -> new MintyCandy();
            case HARD_CANDY -> new HardCandy();
        };
    }
}
