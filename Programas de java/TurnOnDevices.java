public class TurnOnDevices {

    public static void main(String[] args) {
        turnOnDevice(new Lamp());
        turnOnDevice(new Computer());

        CoffeMaker coffeMaker = new CoffeMaker();
        Connectable Adapter = new Adapter(coffeMaker);
        turnOnDevice(Adapter);
    }

    private static void turnOnDevice(Connectable device) {
        device.turnOn();
        System.out.println(device.isOn());
    }
}
