# GrowthModeling
The GrowthModeling repository contains a utility to estimate business growth based on various input conditions. Using the Logistic Growth equation and a density-dependent decline term as the foundation (also known as the Allee Effects equation), our Team has developed a system of seven differential equations that allow us to model multiple business-specific and topology-specific metrics such as the number of computing units based on processing requirements, computing cost, storage cost, archive cost, number of storage data IO operations, number of archive data IO operations, storage data IO operations cost, archive data IO operations cost, infrastructure cost, total carbon footprint, carbon footprint by infrastructure tier, gross revenue, net revenue, and cost per platform user, among other metrics.

The utility has been written to achieve the intended functionality using multiple approaches in the fastest way possible, and does not claim to be either logically or functionally correct. Lastly, the decision to concentrate on Solution Architecture instead of software development as part of the Impact Project has allocated more attention to other areas of concentration.

<br>

$$\frac{dN}{dt}=rN\left(1-\frac{N}{K}\right)-dN$$

## Coefficients

The model we are proposing has 34 coefficients that can be adjusted to perform various business growth experiments, including defining multiple rate plans and modeling those simultaneously to analyze profitability under different growth conditions.

| No.  | Coefficient | Value         | Description                                                      |
| ---: | ---:        | :---          | :---                                                             |
| 1.   | K           | 3500000       | Carrying capacity                                                |
| 2.   | r           | 0.07          | Intrinsic growth rate                                            |
| 3.   | d           | 0.02          | Intrinsic decline rate                                           |
| 4.   | t           | 180           | Months of forecast                                               |
| 5.   | p           | 111.07        | Processing time per user/month                                   |
| 6.   | u           | 0.63          | Maximum desired computing unit utilization                       |
| 7.   | m           | 2592000       | Seconds in a month                                               |
| 8.   | f           | 2880          | Monthly electrocardiogram readings per user/month                |
| 9.   | z           | 89            | Size of a compressed 15-seconds electrocardiogram data file      |
| 10.  | g           | 1048576       | Kilobytes (KB) in a Gigabyte (GB)                                |
| 11.  | h           | 345           | Electrocardiogram files per archive                              |
| 12.  | n1          | 3             | Expected number of PUT data operation in S3 Standard             |
| 13.  | n2          | 5             | Expected number of GET data operation in S3 Standard             |
| 14.  | n3          | 2             | Expected number of PUT data operation in S3 Glacier Deep Archive |
| 15.  | e1          | 3             | Number of Data Storage replicas                                  |
| 16.  | e2          | 2             | Number of Data Archive replicas                                  |
| 17.  | c1          | 13857.33      | Cost per g6e.48xlarge unit/month                                 |
| 18.  | c2          | 0.023         | Cost per GB/month of S3 Standard                                 |
| 19.  | c3          | 0.00099       | Cost per GB/month of S3 Glacier Deep Archive                     |
| 20.  | c4          | 0.000005      | Cost per PUT data operation in S3 Standard                       |
| 21.  | c5          | 0.0000004     | Cost per GET data operation in S3 Standard                       |
| 22.  | c6          | 0.00005       | Cost per PUT data operation in S3 Glacier Deep Archive           |
| 23.  | b1          | 2316.083      | Compute carbon footprint in gCO2e/AMI-month                      |
| 24.  | b2          | 0.615         | Storage carbon footprint in gCO2e/GB-month                       |
| 25.  | b3          | 0.075         | Archive carbon footprint in gCO2e/GB-month                       |
| 26.  | j1          | 0.39          | Basic plan subscribers                                           |
| 27.  | j2          | 0.34          | Standard plan subscribers                                        |
| 28.  | j3          | 0.27          | Premium plan subscribers                                         |
| 29.  | v1          | 75.00         | Basic plan subcription cost                                      |
| 30.  | v2          | 35.00         | Standard plan subcription cost                                   |
| 31.  | v3          | 15.00         | Premium plan subcription cost                                    |
| 32.  | year        | 2026          | Forecast initial year                                            |
| 33.  | month       | 1             | Forecast initial month                                           |
| 34.  | day         | 1             | Forecast initial day                                             |

## Metrics

The model generates the metrics below for each month (t) in the forecast beginning on the indicated date.

| No.  | Metric              | Sample Value | Variable Name             |
| ---: | :---                | ---:         | :---                      |
| 1.   | Date                | 2026-01-01   | date                      |
| 2.   | Customers           | 1000         | customers                 |
| 3.   | Delta               | 37           | customerDelta             |
| 4.   | Units               | 2            | computingUnits            |
| 5.   | Units Cost          | $27,714.66   | computingCost             |
| 6.   | Storage Size (GB)   | 733.34       | dataStorageSize           |
| 7.   | Archive Size (GB)   | 488.89       | dataArchiveSize           |
| 8.   | Storage Cost        | $16.87       | dataStorageCost           |
| 9.   | Archive Cost        | $0.73        | dataArchiveCost           |
| 10.  | Storage IO Cost     | $48.96       | storageDataOperationsCost |
| 11.  | Archive IO Cost     | $0.83        | archiveDataOperationsCost |
| 12.  | Infrastructure Cost | $27,782.05   | infrastructureCost        |
| 13.  | Per User Cost       | $27.78       | perUserCost               |
| 14.  | CO<sub>2</sub>e (g) | 5,119.84     | carbonFootprint           |
| 15.  | Gross Revenue       | $45,200.00   | grossRevenue              |
| 16.  | Net Revenue         | $17,417.95   | netRevenue                |

## The Logistic Growth Model

The CardioAI Team estimates the monthly computing utilization based on 111.07 seconds of electrocardiogram (ECG) data processing per customer (*p*) and AI inference, with a maximum computing unit utilization rate (*u*) of 63%. The estimations are directly associated with the Amazon EC2 high-performance, GPU-accelerated g6e.48xlarge instance type. The instance is equipped with 1536 GiB of system memory and is powered by a 3rd Gen AMD EPYC 7R13 processor with 96 physical CPU cores, which translates to 192 vCPUs when leveraging a thread-per-core ratio of 2. Its accelerators consist of 8 NVIDIA L40S Tensor Core GPUs, each with 48 GB of memory, for a total of 384 GB of GPU memory. This configuration is specifically designed for high-performance generative AI, machine learning training, and spatial computing tasks.

![Console Output](https://raw.githubusercontent.com/AGSArchitect/GrowthModeling/refs/heads/main/GrowthModeling/images/output.png "Console Output")
**Picture 1:** Sample Console Output.

## The Team

The team members of the CardioAI Impact Project are listed below in alphabetical order:

- Barroso, Helga
- Bishop, Jonathan
- Gonzalez, Ariel
- Kasani, Chaitanya
- Philippe, Vincent
- Schenone, Maria

> Ariel Gonzalez - Solutions Architect
