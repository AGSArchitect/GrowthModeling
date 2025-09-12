package com.cardioai.tools.modeling;

/**
 * Metrics
 *
 * @author Ariel Gonzalez
 * @version 1.0
 */
public class Metrics {

    private String date;
    private long customers;
    private long customerDelta;
    private long computingUnits;
    private double computingCost;
    private double dataStorageSize;
    private double dataArchiveSize;
    private double dataStorageCost;
    private double dataArchiveCost;
    private double storageDataOperationsCost;
    private double archiveDataOperationsCost;
    private double infrastructureCost;
    private double perUserCost;
    private double carbonFootprint;
    private double grossRevenue;
    private double netRevenue;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getCustomers() {
        return customers;
    }

    public void setCustomers(long customers) {
        this.customers = customers;
    }

    public long getCustomerDelta() {
        return customerDelta;
    }

    public void setCustomerDelta(long customerDelta) {
        this.customerDelta = customerDelta;
    }

    public long getComputingUnits() {
        return computingUnits;
    }

    public void setComputingUnits(long computingUnits) {
        this.computingUnits = computingUnits;
    }

    public double getComputingCost() {
        return computingCost;
    }

    public void setComputingCost(double computingCost) {
        this.computingCost = computingCost;
    }

    public double getDataStorageSize() {
        return dataStorageSize;
    }

    public void setDataStorageSize(double dataStorageSize) {
        this.dataStorageSize = dataStorageSize;
    }

    public double getDataArchiveSize() {
        return dataArchiveSize;
    }

    public void setDataArchiveSize(double dataArchiveSize) {
        this.dataArchiveSize = dataArchiveSize;
    }

    public double getDataStorageCost() {
        return dataStorageCost;
    }

    public void setDataStorageCost(double dataStorageCost) {
        this.dataStorageCost = dataStorageCost;
    }

    public double getDataArchiveCost() {
        return dataArchiveCost;
    }

    public void setDataArchiveCost(double dataArchiveCost) {
        this.dataArchiveCost = dataArchiveCost;
    }

    public double getStorageDataOperationsCost() {
        return storageDataOperationsCost;
    }

    public void setStorageDataOperationsCost(double storageDataOperationsCost) {
        this.storageDataOperationsCost = storageDataOperationsCost;
    }

    public double getArchiveDataOperationsCost() {
        return archiveDataOperationsCost;
    }

    public void setArchiveDataOperationsCost(double archiveDataOperationsCost) {
        this.archiveDataOperationsCost = archiveDataOperationsCost;
    }

    public double getInfrastructureCost() {
        return infrastructureCost;
    }

    public void setInfrastructureCost(double infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public double getPerUserCost() {
        return perUserCost;
    }

    public void setPerUserCost(double perUserCost) {
        this.perUserCost = perUserCost;
    }

    public double getCarbonFootprint() {
        return carbonFootprint;
    }

    public void setCarbonFootprint(double carbonFootprint) {
        this.carbonFootprint = carbonFootprint;
    }

    public double getGrossRevenue() {
        return grossRevenue;
    }

    public void setGrossRevenue(double grossRevenue) {
        this.grossRevenue = grossRevenue;
    }

    public double getNetRevenue() {
        return netRevenue;
    }

    public void setNetRevenue(double netRevenue) {
        this.netRevenue = netRevenue;
    }
}
