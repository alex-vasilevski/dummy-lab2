package by.tc.task01.entity;

public class Laptop extends Appliance{

    private Double batteryCapacity;
    private String os;
    private Integer memoryRom;
    private Integer systemMemory;
    private Double cpu;
    private Double displayInches;

    public Double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Integer getMemoryRom() {
        return memoryRom;
    }

    public void setMemoryRom(Integer memoryRom) {
        this.memoryRom = memoryRom;
    }

    public Integer getSystemMemory() {
        return systemMemory;
    }

    public void setSystemMemory(Integer systemMemory) {
        this.systemMemory = systemMemory;
    }

    public Double getCpu() {
        return cpu;
    }

    public void setCpu(Double cpu) {
        this.cpu = cpu;
    }

    public Double getDisplayInches() {
        return displayInches;
    }

    public void setDisplayInches(Double displayInches) {
        this.displayInches = displayInches;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "batteryCapacity=" + batteryCapacity +
                ", os='" + os + '\'' +
                ", memoryRom=" + memoryRom +
                ", systemMemory=" + systemMemory +
                ", cpu=" + cpu +
                ", displayInches=" + displayInches +
                '}';
    }
}
