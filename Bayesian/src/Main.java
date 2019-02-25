import java.util.Scanner;

public class Main {
            public static void main(String[] args) {
                DataReader reader;
                BayesianCurveFitting fit;
                String exitSign = "Y";
                int num = 0;
                double absMeanError = 0.0;	// absolute mean error
                double relativeError = 0.0;	// relative error;
                double sumError = 0.0;
                double sumPrice = 0.0;
                boolean isstock=true;
                while(exitSign.equals("Y") || exitSign.equals("y")) {

                    System.out.println("Please choose a stock symbol to input (AMZN/GOOG):");

                    Scanner in = new Scanner(System.in);
                    String stockName=in.nextLine();

                    System.out.println("Enter number of data (N):");
                    int N = in.nextInt();
                    double[] x = new double[N];
                    for(int i = 0; i < N; i++) {
                        x[i] = i + 1;
                    }
                    int m=9;


                        // my own stock data file
                        reader = new DataReader(N, stockName);

                    double[] stockPrices = reader.getPrices();
                    fit = new BayesianCurveFitting(x, stockPrices, m);
                    double prediction = fit.getMean(N + 1);
                    double s2x = fit.getS2X(N + 1);
                    //double stdVar = Math.sqrt(s2x);	// standard variance
                    double actualPrice = 0.0;

                        actualPrice = reader.getActualPrice();

                    num++;
                    sumError += Math.abs(actualPrice - prediction);
                    sumPrice += actualPrice;
                    absMeanError = sumError / num;
                    relativeError = sumError / sumPrice;
                    System.out.println("========================================");

                    System.out.println("Symbol: " +stockName);
                    System.out.printf("Predicted price is: %.2f\n", prediction);
                    System.out.println("prediction Variation: " + s2x);

                    System.out.println("Actual price:" + actualPrice);
                    System.out.println("Absolute mean error:" + absMeanError);
                    System.out.println("Average relative error:" + relativeError);

                    System.out.println("Continue? (Y/N)");
                    exitSign = in.next();
                }
            }



        }



