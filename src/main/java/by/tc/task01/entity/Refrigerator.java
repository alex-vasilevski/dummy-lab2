package by.tc.task01.entity;

public class Refrigerator extends Appliance{

    private Integer powerConsumption;
    private Double weight;
    private Integer freezerCapacity;
    private Double overallCapacity;
    private Double height;
    private Double width;

    public Integer getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(Integer powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getFreezerCapacity() {
        return freezerCapacity;
    }

    public void setFreezerCapacity(Integer freezerCapacity) {
        this.freezerCapacity = freezerCapacity;
    }

    public Double getOverallCapacity() {
        return overallCapacity;
    }

    public void setOverallCapacity(Double overallCapacity) {
        this.overallCapacity = overallCapacity;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Refrigerator{" +
                "powerConsumption=" + powerConsumption +
                ", weight=" + weight +
                ", freezerCapacity=" + freezerCapacity +
                ", overallCapacity=" + overallCapacity +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}
