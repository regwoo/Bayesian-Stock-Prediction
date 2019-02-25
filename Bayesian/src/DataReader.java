import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {
    private int N;
    private double[] prices;
    private String symbol;
    private String[] info;
    int count;
    boolean isStock;

    public DataReader(int N, String symbol) {
        this.N = N;
        this.symbol = symbol;
        prices = new double[N];
        String fileName;

            fileName = "Data/" + symbol + ".csv";//modify it if the data location or file name changed

        info = new String[1000];
        String line = "";
        count = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            line = reader.readLine();
            while(line != null) {
                String[] item = line.split(",");

                    info[count] = item[3];

                count++;
                line = reader.readLine();
            }
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < N; i++) {
            prices[i] = Double.parseDouble(info[count - i - 1]);
        }
    }

    public double[] getPrices() {
        return prices;
    }

    public double getActualPrice() {
        double actual = Double.parseDouble(info[count - N - 1]);//the first price is latest price
        return actual;
    }

    public String getSymbol() {
        return symbol;
    }
}