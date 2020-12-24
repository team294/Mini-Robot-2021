package frc.robot.utilities;

public class ShotData {
    public double velocity, timeDelay;
    public int shotCount;
    public ShotData(double v, double t, int sc) {
        this.velocity = v;
        this.timeDelay = t;
        this.shotCount = sc;
    }
}
