package com.cardioai.tools.modeling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Utils
 *
 * @author Ariel Gonzalez
 * @version 1.0
 */
public class Utils {

    public static void renderForecast(Forecast forecast) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(
                gson.toJson(forecast));
    }

    public static void renderConsoleOutput(Forecast forecast) {
        int[] cws = {
            0, 13, 12, 9, 8, 16, 20, 20, 15, 15, 18, 18, 22, 16, 15, 18, 18
        };

        StringBuilder headers = new StringBuilder();
        headers.append(String.format("%" + cws[1] + "s", "Date"));
        headers.append(String.format("%" + cws[2] + "s", "Customers"));
        headers.append(String.format("%" + cws[3] + "s", "Delta"));
        headers.append(String.format("%" + cws[4] + "s", "Units"));
        headers.append(String.format("%" + cws[5] + "s", "Units Cost"));
        headers.append(String.format("%" + cws[6] + "s", "Storage Size (GB)"));
        headers.append(String.format("%" + cws[7] + "s", "Archive Size (GB)"));
        headers.append(String.format("%" + cws[8] + "s", "Storage Cost"));
        headers.append(String.format("%" + cws[9] + "s", "Archive Cost"));
        headers.append(String.format("%" + cws[10] + "s", "Storage IO Cost"));
        headers.append(String.format("%" + cws[11] + "s", "Archive IO Cost"));
        headers.append(String.format("%" + cws[12] + "s", "Infrastructure Cost"));
        headers.append(String.format("%" + cws[13] + "s", "Per User Cost"));
        headers.append(String.format("%" + cws[14] + "s", "CO2e (g)"));
        headers.append(String.format("%" + cws[15] + "s", "Gross Revenue"));
        headers.append(String.format("%" + cws[16] + "s", "Net Revenue"));

        System.out.println(headers.toString());

        for (Metrics metric : forecast.getMetrics()) {
            StringBuilder data = new StringBuilder();
            data.append(String.format("%" + cws[1] + "s", metric.getDate()));
            data.append(String.format("%" + cws[2] + "s", metric.getCustomers()));
            data.append(String.format("%" + cws[3] + "s", metric.getCustomerDelta()));
            data.append(String.format("%" + cws[4] + "s", metric.getComputingUnits()));
            data.append(String.format("%" + cws[5] + "s", formatAmount(metric.getComputingCost())));
            data.append(String.format("%" + cws[6] + "s", formatValue(metric.getDataStorageSize())));
            data.append(String.format("%" + cws[7] + "s", formatValue(metric.getDataArchiveSize())));
            data.append(String.format("%" + cws[8] + "s", formatAmount(metric.getDataStorageCost())));
            data.append(String.format("%" + cws[9] + "s", formatAmount(metric.getDataArchiveCost())));
            data.append(String.format("%" + cws[10] + "s", formatAmount(metric.getStorageDataOperationsCost())));
            data.append(String.format("%" + cws[11] + "s", formatAmount(metric.getArchiveDataOperationsCost())));
            data.append(String.format("%" + cws[12] + "s", formatAmount(metric.getInfrastructureCost())));
            data.append(String.format("%" + cws[13] + "s", formatAmount(metric.getPerUserCost())));
            data.append(String.format("%" + cws[14] + "s", formatValue(metric.getCarbonFootprint())));
            data.append(String.format("%" + cws[15] + "s", formatAmount(metric.getGrossRevenue())));
            data.append(String.format("%" + cws[16] + "s", formatAmount(metric.getNetRevenue())));
            System.out.println(data.toString());
        }
    }

    private static String formatAmount(double amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

        return currencyFormatter.format(amount);
    }

    private static String formatValue(double value) {
        return String.format("%,.2f", value);
    }
}
