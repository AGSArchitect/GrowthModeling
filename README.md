# The Logistic Growth Model
The GrowthModeling repository contains a Java utility designed to estimate business growth based on various input conditions. The utility was developed by Group 2 (Cohort 20024) as part of the Professional Certificate Program in Product Management from the Massachusetts Institute of Technology (MIT).

Using the Logistic Growth equation and a density-dependent decline term (also known as the Allee Effects equation), our Team has developed a system of differential equations that allow us to model multiple business-specific and topology-specific metrics such as the number of computing units based on processing requirements, computing cost, storage cost, archive cost, number of storage data IO operations, number of archive data IO operations, storage data IO operations cost, archive data IO operations cost, infrastructure cost, carbon footprint, gross revenue, net revenue, and per user cost, among other metrics.

The main differential equation is as follows:

<br>

$$\frac{dN}{dt}=rN\left(1-\frac{N}{K}\right)-dN$$

<br>

The Team estimates the monthly computing utilization of CardioAI based on 111.07 seconds of electrocardiogram (ECG) data processing per customer (*p*) and AI inference, with a desired maximum computing unit utilization rate (*u*) of 63%. The various coefficients are directly associated with the Amazon EC2 high-performance, GPU-accelerated `g6e.48xlarge` instance type. The instance is equipped with 1536 GiB of system memory and is powered by a 3rd Gen AMD EPYC 7R13 processor with 96 physical CPU cores, which translates to 192 vCPUs when leveraging a thread-per-core ratio of 2. Its accelerators consist of 8 NVIDIA L40S Tensor Core GPUs, each with 48 GB of memory, for a total of 384 GB of GPU memory. This configuration is specifically designed for high-performance generative AI, machine learning training, and spatial computing tasks.

Data storage and archive costs in S3 Standard and S3 Glacier Deep Archive are estimated based on compressed electrocardiogram files of 89 KB in size (*z*) or an archive file containing 345 electrocardiogram files (*h*), respectively. The model accounts for multiple data replicas (*e1*) and archive replicas (*e2*) in addition to estimating the overall cost of corresponding data operations (*n1*, *n2*, *n3*). The carbon footprint estimations are based on publicly available information from different sources. They are explicitly related to the estimated monthly CO<sub>2</sub> emissions of the computing unit (*b1*), storage (*b2*), and archive (*b3*) services.

Lastly, we are currently modeling a Basic, Standard, and Premium plan at different adoption rates (*j1*, *j2*, *j3*) and price points (*v1*, *v2*, *v3*). However, additional plans can be added at any time should it be necessary.

The differential equation to calculate revenue from subscription plans is as follows:

<br>

$$\frac{dN}{dt}=Nj_1v_1+Nj_2v_2+Nj_3v_3+Nj_nv_n$$

<br>

The utility has been written to achieve the intended functionality using multiple approaches in the fastest way possible, and does not claim to be either logically or functionally correct. Lastly, the decision to concentrate on Solution Architecture instead of software development as part of the Impact Project has allocated more attention to other areas of concentration.

## 1. Coefficients

The proposed model has 34 coefficients, all of which can be individually adjusted to create various business growth conditions. Some of the conditions can include using various computing units, adjusting the desired utilization, modifying the number of data replicas, or optimizing the cost of data operations.

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

## 2. Metrics

The model outputs the following metrics for each month (*t*) in the forecast beginning on the indicated date. The forecast can be displayed as a table directly on the screen for immediate analysis or output as a serialized JSON to use in conjunction with a visualization tool.

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

## 3. Configuration

The following is a serialized configuration file containing the default values for all coefficients.

```
{
  "K": 3500000,       "r": 0.07,          "d": 0.02,          "t": 180,
  "p": 111.07,        "u": 0.63,          "m": 2592000,       "f": 2880,
  "z": 89,            "g": 1048576,       "h": 345,
  "n1": 3,            "n2": 5,            "n3": 2,
  "e1": 3,            "e2": 2,
  "c1": 13857.33,     "c2": 0.023,        "c3": 0.00099,
  "c4": 0.000005,     "c5": 0.0000004,    "c6": 0.00005,
  "b1": 2316.083,     "b2": 0.615,        "b3": 0.075,
  "j1": 0.39,         "j2": 0.34,         "j3": 0.27,
  "v1": 75.0,         "v2": 35.0,         "v3": 15.0,
  "year": 2026,       "month": 1,         "day": 1
}
```
**Example 1:** Default configuration file

## 4. Console Output

Below is a sample of the forecast displayed as a table. Plans for the utility include the development of a visualization tool and export functions to multiple files and formats, including TXT, CSV, Excel, HTML, and PDF.

![Console Output](https://raw.githubusercontent.com/AGSArchitect/GrowthModeling/refs/heads/main/GrowthModeling/images/output.png "Console Output")
**Picture 1:** Sample of a forecast displayed in the output console.

## 5. Forecasts

The Team will use the following forecasts during our final presentation, and also as supporting material towards fulfilling the requirements of the MIT certification.

| No.  | (*K*)   | (*N*) | (*r*)  | (*d*)   | (*u*) | (*t*) | Created    | Forecast |
| ---: | :---    | :---  | :---   | :---    | :---  | :---  | :---       | :---     |
| 1.   | 3500000 | 1000  | `0.05` | `0.013` | 0.63  | 180   | 2025-09-12 | [HTML](https://cardioai.s3.us-east-2.amazonaws.com/K3500000-r05d013-t180.html) &nbsp; [PDF](https://cardioai.s3.us-east-2.amazonaws.com/K3500000-r05d013-t180.pdf) |
| 2.   | 3500000 | 1000  | `0.06` | `0.013` | 0.63  | 180   | 2025-09-12 | [HTML](https://cardioai.s3.us-east-2.amazonaws.com/K3500000-r06d013-t180.html) &nbsp; [PDF](https://cardioai.s3.us-east-2.amazonaws.com/K3500000-r06d013-t180.pdf) |
| 3.   | 3500000 | 1000  | `0.07` | `0.015` | 0.63  | 180   | 2025-09-12 | [HTML](https://cardioai.s3.us-east-2.amazonaws.com/K3500000-r07d015-t180.html) &nbsp; [PDF](https://cardioai.s3.us-east-2.amazonaws.com/K3500000-r07d015-t180.pdf) |
| 4.   | 3500000 | 1000  | `0.07` | `0.020` | 0.63  | 180   | 2025-09-12 | [HTML](https://cardioai.s3.us-east-2.amazonaws.com/K3500000-r07d02-t180.html)  &nbsp; [PDF](https://cardioai.s3.us-east-2.amazonaws.com/K3500000-r07d02-t180.pdf)  |
| 5.   | 3500000 | 1000  | `0.17` | `0.030` | 0.63  | 180   | 2025-09-12 | [HTML](https://cardioai.s3.us-east-2.amazonaws.com/K3500000-r17d03-t180.html)  &nbsp; [PDF](https://cardioai.s3.us-east-2.amazonaws.com/K3500000-r17d03-t180.pdf)  |

**Note:** In addition, we are currently reviewing a forecast of (*K*) 3500000, (*N*) 1000, (*r*) 0.07, and (*d*) 0.0231, which converges exactly with the carrying capacity at time (*t*) 180.

&nbsp; &nbsp; &nbsp; Mens et Manus

## The Team

The team members of the CardioAI Impact Project are listed below in alphabetical order:

- Barroso, Helga
- Bishop, Jonathan
- Gonzalez, Ariel
- Kasani, Chaitanya
- Philippe, Vincent
- Schenone, Maria

> Ariel Gonzalez - Solutions Architect
