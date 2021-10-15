package by.tc.task01.entity;

public class TabletPC extends Appliance{

    private Double batteryCapacity;
    private Double displayInches;
    private Integer memoryRom;
    private Integer flashMemoryCapacity;
    private String color;

    public Double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public Double getDisplayInches() {
        return displayInches;
    }

    public void setDisplayInches(Double displayInches) {
        this.displayInches = displayInches;
    }

    public Integer getMemoryRom() {
        return memoryRom;
    }

    public void setMemoryRom(Integer memoryRom) {
        this.memoryRom = memoryRom;
    }

    public Integer getFlashMemoryCapacity() {
        return flashMemoryCapacity;
    }

    public void setFlashMemoryCapacity(Integer flashMemoryCapacity) {
        this.flashMemoryCapacity = flashMemoryCapacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "TabletPC{" +
                "batteryCapacity=" + batteryCapacity +
                ", displayInches=" + displayInches +
                ", memoryRom=" + memoryRom +
                ", flashMemoryCapacity=" + flashMemoryCapacity +
                ", color='" + color + '\'' +
                '}';
    }
}
