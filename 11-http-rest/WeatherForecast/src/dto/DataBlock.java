package dto;

import java.util.List;

public class DataBlock {

    private String summary;
    private List<DataPoint> data;

    public DataBlock(String summary, List<DataPoint> data) {
        this.summary = summary;
        this.data = data;
    }

    public String getSummary() {
        return summary;
    }

    public List<DataPoint> getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("Summary: " + summary + System.lineSeparator());
        str.append("Data: ");

        for (DataPoint dataPoint : data) {
            str.append(dataPoint);
        }

        return data.toString();
    }
}
