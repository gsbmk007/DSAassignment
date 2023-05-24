public class naturalElements {

    private Integer temperature;
    private Integer humidity;
    private Integer windSpeed;
    private String label;

    public naturalElements(String label, Integer temperature, Integer humidity, Integer windSpeed) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.label = label;

    }

    public naturalElements() {
        this.temperature = 0;
        this.humidity = 0;
        this.windSpeed = 0;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public String getlabel() {
        return label;
    }

    public void setlabel(String label) {
        this.label = label;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Integer windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String toString() {
        return "Temperature: " + this.temperature + " Humidity: " + this.humidity + " Windspeed: " + this.windSpeed;
    }
}
