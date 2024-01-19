public class CoffeMaker {

    private boolean isOn;

    public void on() {
        isOn = true;
        System.out.println("Coffee Maker is ON.");
    }

    public void off() {
        isOn = false;
        System.out.println("Coffee Maker is OFF.");
    }

    public boolean isOff() {
        return !isOn;
    }
}