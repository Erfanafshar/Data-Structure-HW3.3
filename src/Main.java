import java.util.Scanner;

public class Main {
    private static final long INF = 999999999999L;

    private static int minDistance(long minWays[], Boolean checked[]) {
        long min =INF;
        int minIndex = -1;

        for (int i = 0; i < 1000; i++)
            if (!checked[i] && minWays[i] <= min) {
                min = minWays[i];
                minIndex = i;
            }

        return minIndex;
    }

    private static long[] dijkstra(long table[][], long table2[][], int source, int destination) {

        long result[] = new long[2];
        long wayLength[] = new long[1000];
        long minWays[] = new long[1000];
        Boolean checked[] = new Boolean[1000];
        for (int i = 0; i < 1000; i++) {
            minWays[i] = INF;
            wayLength[i] = INF;
            checked[i] = false;
        }
        minWays[source] = 0L;
        wayLength[source] = 0L;
        for (int i = 0; i < 1000; i++) {
            int choose = minDistance(minWays, checked);
            if(choose == -1)
                break;
            checked[choose] = true;
            for (int j = 0; j < 1000; j++)
                if (!checked[j] &&
                        table[choose][j] != 0 &&
                        (minWays[choose]+table[choose][j]<minWays[j]
                                || (minWays[choose]+table[choose][j]==minWays[j]
                                && wayLength[choose]+table2[choose][j]<wayLength[j] ))) {

                    minWays[j] = minWays[choose]+table[choose][j];
                    wayLength[j] = wayLength[choose] + table2[choose][j];

                }

        }

        result[0] = minWays[destination];
        result[1] = wayLength[destination];

        return result;

    }

    public static void main(String[] args) {

        String input1;
        String input2;
        String input3;

        String[] input1Parts;
        String[] input2Parts;
        String[] input3Parts;

        long[][] table = new long[1000][1000];
        long[][] table2 = new long[1000][1000];
        int start;
        int end;
        int n;
        long cost;
        int citiesNumber;
        long[] results;

        Scanner scanner = new Scanner(System.in);
        input1 = scanner.nextLine();

        input1Parts = input1.split(" ");

        start = Integer.valueOf(input1Parts[0]) - 1;
        end = Integer.valueOf(input1Parts[1]) - 1;

        n = Integer.valueOf(input1Parts[2]);


        for (int i = 0; i < n; i++) {

            input2 = scanner.nextLine();
            input2Parts = input2.split(" ");

            cost = Integer.valueOf(input2Parts[0]);
            citiesNumber = Integer.valueOf(input2Parts[1]);

            int[] cities = new int[citiesNumber];

            input3 = scanner.nextLine();
            input3Parts = input3.split(" ");

            for (int j = 0; j < citiesNumber; j++) {
                cities[j] = Integer.valueOf(input3Parts[j]) - 1;
            }

            for (int j = 0; j < citiesNumber; j++) {
                for (int k = j + 1; k < citiesNumber; k++) {
                    if (table[cities[j]][cities[k]] == 0) {
                        table[cities[j]][cities[k]] = cost;
                        table2[cities[j]][cities[k]] = k - j;
                    } else {
                        if (table[cities[j]][cities[k]] > cost
                                || (table[cities[j]][cities[k]] == cost && table2[cities[j]][cities[k]] > k-j)) {
                            table[cities[j]][cities[k]] = cost;
                            table2[cities[j]][cities[k]] = k - j;
                        }
                    }

                }
            }

        }

        results = dijkstra(table, table2, start, end);

        if (results[1] == INF) {
            System.out.println(-1 + " " + -1);
        } else {
            System.out.println(results[0] + " " + results[1]);
        }

    }
}
