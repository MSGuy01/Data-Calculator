import java.util.Arrays;

public class Calculator {
    int[] data;
    public int[] sortedData;
    int len = 0;
    public Calculator(int[] data, int len) {
        this.len = len;
        this.data = new int[len];
        for (int i = 0; i < len; i++) {
            this.data[i] = data[i];
        }
        sortedData = sort(this.data);
    }
    public double getMean() {
        double total = 0;
        for (int i = 0; i < len; i++) {
            total += data[i];
        }
        return total / len;
    }
    public double getMAD() {
        double mean = getMean();
        double finalAdd = 0;
        for (int i = 0; i < data.length; i++) {
            finalAdd += Math.abs(data[i] - mean);
        }
        return finalAdd / len;
    }

    public int[] sort(int[] rawData) {
        int temp;
        for (int i = 0; i < rawData.length; i++) {
            for (int j = 0; j < rawData.length; j++) {
                if (rawData[j] > rawData[i]) {
                    temp = rawData[i];
                    rawData[i] = rawData[j];
                    rawData[j] = temp;
                }
            }
        }
        return rawData;
    }
    public double getMedian(int[] thisData) {
        double median;
        double median2 = 0;
        if (thisData.length % 2 != 0) {
            median = thisData[((thisData.length + 1) / 2) - 1];
        }
        else{
            median = (thisData[thisData.length / 2] - thisData[((thisData.length / 2) - 1)]);
            median = thisData[((thisData.length / 2) - 1)] + (median / 2);
        }
        return median;
    }
    public int[] greatestAndSmallest() {
        int best = 0;
        int[] finalArr = new int[2];
        for (int i = 0; i < data.length; i++) {
            if (data[i] <= data[best]) {
                best = i;
            }
        }
        finalArr[0] = data[best];
        best = 0;
        for (int i = 0; i <data.length; i++) {
            if (data[i] >= data[best]) {
                best = i;
            }
        }
        finalArr[1] = data[best];
        return finalArr;
    }
    public double getRange() {
        return greatestAndSmallest()[1] - greatestAndSmallest()[0];
    }
    public double getMode() {
        int times = 0;
        int best = 0;
        int bestTimes = 0;
        for (int j = 0; j < sortedData.length; j++) {
            for (int i = 0; i < sortedData.length; i++) {
                if (sortedData[i] == sortedData[j]) {
                    times++;
                }
            }
            if (times >= bestTimes) {
                bestTimes = times;
                best = j;
            }
            times = 0;
        }
        return sortedData[best];
    }
    public double getQ1() {
        int[] q1Arr;
        //even
        if (len % 2 == 0) {
            q1Arr = new int[len / 2];
        }
        //odd
        else{
            q1Arr = new int[(len + 1) / 2];
        }
        for (int i = 0; i < q1Arr.length; i++) {
            q1Arr[i] = sortedData[i];
        }
        return getMedian(q1Arr);
    }
    public double getQ3() {
        int[] q3Arr;
        int medianIndex = 0;
        //even
        if (len % 2 == 0) {
            q3Arr = new int[len / 2];
            medianIndex = len / 2;
        }
        //odd
        else{
            q3Arr = new int[(len + 1) / 2];
            medianIndex = (len - 1) / 2;
        }
        int j = 0;
        for (int i = medianIndex; i < len; i++) {
            q3Arr[j] = sortedData[i];
            j++;
        }
        return getMedian(q3Arr);
    }
}
