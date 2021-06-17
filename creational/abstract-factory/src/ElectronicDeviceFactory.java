public class ElectronicDeviceFactory {
    public static ElectronicDeviceAbstractFactory getFactory(Segment segment){
        return switch (segment) {
            case HIGH_END -> new HighEndDeviceFactory();
            case MID_RANGE -> new MidRangeDeviceFactory();
        };
    }
}
