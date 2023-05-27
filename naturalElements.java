public class naturalElements {

    private Integer temperature;
    private Integer humidity;
    private Integer windSpeed;
    private String label;
    private Integer risk;

    public naturalElements(String label, Integer temperature, Integer humidity, Integer windSpeed) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.label = label;
        this.risk=0;

        // Temperature risk
        if (temperature >= 25 && temperature <= 32) {
            risk += 1;
        } else if (temperature >= 33 && temperature <= 40) {
            risk += 2;
        } else if (temperature > 40) {
            risk += 3;
        }
    
        // Humidity risk
        if (humidity > 50) {
            risk += 1;
        } else if (humidity >= 31 && humidity <= 50) {
            risk += 2;
        } else if (humidity < 30) {
            risk += 3;
        }
    
        // Wind speed risk
        if (windSpeed < 40) {
            risk += 1;
        } else if (windSpeed >= 41 && windSpeed <= 55) {
            risk += 2;
        } else if (windSpeed > 55) {
            risk += 3;
        }
        
    }

    public naturalElements() {
        this.temperature = 0;
        this.humidity = 0;
        this.windSpeed = 0;
    }

    
    public String risklevel(){
        // Determine overall risk level based on the risk value
        String riskLevel;
        if (risk <= 3) {
            riskLevel = "Low";
        } else if (risk <= 6) {
            riskLevel = "Medium";
        } else {
            riskLevel = "High";
        }
    
        return riskLevel;
    }
    public Integer riskInteger() {
        return risk;
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
        return "Location: "+this.label+" Temperature: " + this.temperature + " Humidity: " + this.humidity + " Windspeed: " + this.windSpeed+"Risk: "+riskInteger()+" "+risklevel();
    }
}
