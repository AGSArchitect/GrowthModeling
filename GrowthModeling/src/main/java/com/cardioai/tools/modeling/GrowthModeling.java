package com.cardioai.tools.modeling;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * GrowthModeling
 *
 * @author Ariel Gonzalez
 * @version 1.0
 */
public class GrowthModeling {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static void main(String[] args) {

        // Carrying capacity
        final long K = 3500000;

        // Intrinsic growth rate
        final double r = 0.07;

        // Intrinsic decline rate
        final double d = 0.02;

        // Years of forecast
        final long t = 15 * 12;

        // Processing time per user/month (2880 files)
        final double p = 111.07;

        // Maximum desired computing unit utilization
        final double u = 0.63;

        // Seconds in a month
        final long m = 2592000;

        // Monthly electrocardiogram readings per user/month (30 x 24 x 4)
        final long f = 2880;

        // Size of a compressed 15-seconds electrocardiogram data file (275 uncompressed)
        final long z = 89;

        // Kilobytes (KB) in a Gigabyte (GB)
        final long g = 1048576;

        // electrocardiogram files per archive
        final long h = 345;

        // Expected number of PUT data operation in S3 Standard
        final long n1 = 3;

        // Expected number of GET data operation in S3 Standard
        final long n2 = 5;

        // Expected number of PUT data operation in S3 Glacier Deep Archive
        final long n3 = 2;

        // Number of Data Storage replicas
        final long e1 = 3;

        // Number of Data Archive replicas
        final long e2 = 2;

        // Cost per g6e.48xlarge unit/month
        final double c1 = 13857.33;

        // Cost per GB/month of S3 Standard
        final double c2 = 0.023;

        // Cost per GB/month of S3 Glacier Deep Archive
        final double c3 = 0.00099;

        // Cost per PUT data operation in S3 Standard
        final double c4 = 0.000005;

        // Cost per GET data operation in S3 Standard
        final double c5 = 0.0000004;

        // Cost per PUT data operation in S3 Glacier Deep Archive
        final double c6 = 0.00005;

        // Compute carbon footprint in gCO2e/AMI-month
        final double b1 = 2316.083;

        // Storage carbon footprint in gCO2e/GB-month
        final double b2 = 0.615;

        // Archive carbon footprint in gCO2e/GB-month
        final double b3 = 0.075;

        // Basic plan subscribers
        final double j1 = 0.39;

        // Standard plan subscribers
        final double j2 = 0.34;

        // Premium plan subscribers
        final double j3 = 0.27;

        // Basic plan subcription cost
        final double v1 = 75.00;

        // Standard plan subcription cost
        final double v2 = 35.00;

        // Premium plan subcription cost
        final double v3 = 15.00;

        // Forecast initial year
        final int year = 2026;

        // Forecast initial month
        final int month = 1;

        // Forecast initial day
        final int day = 1;

        LocalDate date = LocalDate.of(year, month, day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

        Configuration configuration = new Configuration(K, r, d, t, p, u, m, f, z, g, h, n1, n2, n3, e1, e2, c1, c2, c3, c4, c5, c6, b1, b2, b3, j1, j2, j3, v1, v2, v3, year, month, day);
        Forecast forecast = new Forecast(getId(), configuration, getCreated());

        long N = 1000;
        for (int i = 1; i <= t; i++) {
            Metrics metrics = new Metrics();
            forecast.getMetrics().add(metrics);

            metrics.setDate(formatter.format(date));

            metrics.setCustomers(N);

            metrics.setCustomerDelta(
                    calculateBusinessGrowth(N, K, r, d));

            metrics.setComputingUnits(
                    calculateComputingUnits(N, p, u, m));

            metrics.setComputingCost(
                    calculateComputingCost(
                            metrics.getComputingUnits(), c1));

            metrics.setDataStorageSize(
                    calculateStorageSize(e1, N, f, z, g));

            metrics.setDataArchiveSize(
                    calculateStorageSize(e2, N, f, z, g));

            metrics.setDataStorageCost(
                    calculateStorageCost(
                            metrics.getDataStorageSize(), c2));

            metrics.setDataArchiveCost(
                    calculateStorageCost(
                            metrics.getDataStorageSize(), c3));

            metrics.setStorageDataOperationsCost(
                    calculateStorageDataOperationsCost(N, f, n1, n2, c4, c5));

            metrics.setArchiveDataOperationsCost(
                    calculateArchiveDataOperationsCost(N, f, h, n3, c6));

            metrics.setInfrastructureCost(
                    calculateInfrastructureCost(
                            metrics.getComputingCost(), metrics.getDataStorageCost(), metrics.getDataArchiveCost(),
                            metrics.getStorageDataOperationsCost(), metrics.getArchiveDataOperationsCost()));

            metrics.setPerUserCost(
                    calculatePerUserCost(N, metrics.getInfrastructureCost()));

            metrics.setCarbonFootprint(
                    calculateCarbonFootprint(
                            metrics.getComputingUnits(), metrics.getDataStorageSize(), metrics.getDataArchiveSize(), b1, b2, b3));

            metrics.setGrossRevenue(
                    calculateGrossRevenue(N, j1, j2, j3, v1, v2, v3));

            metrics.setNetRevenue(
                    calculateNetRevenue(
                            metrics.getInfrastructureCost(), metrics.getGrossRevenue()));

            N = N + metrics.getCustomerDelta();
            date = date.plusMonths(1);
        }

        Utils.renderConsoleOutput(forecast);
    }

    private static long calculateBusinessGrowth(long N, long K, double r, double d) {
        return (long) (r * N * (1 - (N / K)) - (d * N));
    }

    private static long calculateComputingUnits(long N, double p, double u, long m) {
        long computingUnits = (long) Math.ceil((N * p * ((1 - u) + 1)) / m);

        return (computingUnits < 2) ? 2 : computingUnits;
    }

    private static double calculateComputingCost(long computingUnits, double c1) {
        return computingUnits * c1;
    }

    private static double calculateStorageSize(long e, long N, long f, long z, long g) {
        return ((double) e * N * f * z) / g;
    }

    private static double calculateStorageCost(double gigabytes, double c) {
        return gigabytes * c;
    }

    private static double calculateStorageDataOperationsCost(long N, long f, long n1, long n2, double c1, double c2) {
        return (n1 * N * f * c1) + (n2 * N * f * c2);
    }

    private static double calculateArchiveDataOperationsCost(long N, long f, long h, long n3, double c3) {
        return n3 * (((N * f) / h) * c3);
    }

    private static double calculateInfrastructureCost(double computingCost, double dataStorageCost, double dataArchiveCost, double storageDataOperationsCost, double archiveDataOperationsCost) {
        return computingCost + dataStorageCost + dataArchiveCost + storageDataOperationsCost + archiveDataOperationsCost;
    }

    private static double calculatePerUserCost(long N, double infrastructureCost) {
        return infrastructureCost / N;
    }

    private static double calculateCarbonFootprint(long computingUnits, double storageSize, double archiveSize, double b1, double b2, double b3) {
        return (computingUnits * b1) + (storageSize * b2) + (archiveSize * b3);
    }

    private static double calculateGrossRevenue(long N, double j1, double j2, double j3, double v1, double v2, double v3) {
        return (N * j1 * v1) + (N * j2 * v2) + (N * j3 * v3);
    }

    private static double calculateNetRevenue(double infrastructureCost, double grossRevenue) {
        return grossRevenue - infrastructureCost;
    }

    private static String getId() {
        return UUID.randomUUID().toString();
    }

    private static long getCreated() {
        return System.currentTimeMillis();
    }
}
