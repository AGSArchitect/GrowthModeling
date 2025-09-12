# GrowthModeling
The GrowthModeling repository contains a utility to estimate business growth based on various input conditions. Using the Logistic Growth equation and a density-dependent decline term as the foundation (also known as the Allee Effects equation), our Team has developed a system of seven differential equations that allow us to model multiple business-specific and topology-specific metrics such as the number of computing units based on processing requirements, computing cost, storage cost, archive cost, number of storage data IO operations, number of archive data IO operations, storage data IO operations cost, archive data IO operations cost, infrastructure cost, total carbon footprint, carbon footprint by infrastructure tier, gross revenue, net revenue, and cost per platform user, among other metrics.

The utility has been written to achieve the intended functionality using multiple approaches in the fastest way possible, and does not claim to be either logically or functionally correct. Lastly, the decision to concentrate on Solution Architecture instead of software development as part of the Impact Project has allocated more attention to other areas of concentration.

<br>

$$\frac{dN}{dt}=rN\left(1-\frac{N}{K}\right)-dN$$

## Coefficients

The model we are proposing has 34 coefficients that can be adjusted to perform various business growth experiments, including defining multiple rate plans and modeling those simultaneously to analyze profitability under different growth conditions.

| Coefficient | Default   | Description                                                                    |
| :---        | :---      | :---                                                                           |
| K           | 3500000   | Carrying capacity                                                              |
| r           | 0.07      | Intrinsic growth rate                                                          |
| d           | 0.02      | Intrinsic decline rate                                                         |
| t           | 180       | Years of forecast                                                              |
| p           | 111.07    | Processing time per user/month (2880 files)                                    |
| u           | 0.63      | Maximum desired computing unit utilization                                     |
| m           | 2592000   | Seconds in a month                                                             |
| f           | 2880      | Monthly electrocardiogram readings per user/month (30 x 24 x 4)                |
| z           | 89        | Size of a compressed 15-seconds electrocardiogram data file (275 uncompressed) |
| g           | 1048576   | Kilobytes (KB) in a Gigabyte (GB)                                              |
| h           | 345       | Electrocardiogram files per archive                                            |
| n1          | 3         | Expected number of PUT data operation in S3 Standard                           |
| n2          | 5         | Expected number of GET data operation in S3 Standard                           |
| n3          | 2         | Expected number of PUT data operation in S3 Glacier Deep Archive               |
| e1          | 3         | Number of Data Storage replicas                                                |
| e2          | 2         | Number of Data Archive replicas                                                |
| c1          | 13857.33  | Cost per g6e.48xlarge unit/month                                               |
| c2          | 0.023     | Cost per GB/month of S3 Standard                                               |
| c3          | 0.00099   | Cost per GB/month of S3 Glacier Deep Archive                                   |
| c4          | 0.000005  | Cost per PUT data operation in S3 Standard                                     |
| c5          | 0.0000004 | Cost per GET data operation in S3 Standard                                     |
| c6          | 0.00005   | Cost per PUT data operation in S3 Glacier Deep Archive                         |
| b1          | 2316.083  | Compute carbon footprint in gCO2e/AMI-month                                    |
| b2          | 0.615     | Storage carbon footprint in gCO2e/GB-month                                     |
| b3          | 0.075     | Archive carbon footprint in gCO2e/GB-month                                     |
| j1          | 0.39      | Basic plan subscribers                                                         |
| j2          | 0.34      | Standard plan subscribers                                                      |
| j3          | 0.27      | Premium plan subscribers                                                       |
| v1          | 75.00     | Basic plan subcription cost                                                    |
| v2          | 35.00     | Standard plan subcription cost                                                 |
| v3          | 15.00     | Premium plan subcription cost                                                  |
| year        | 2026      | Forecast initial year                                                          |
| month       | 1         | Forecast initial month                                                         |
| day         | 1         | Forecast initial day                                                           |

## The Team

The team members of the CardioAI Impact Project are listed below in alphabetical order:

- Barroso, Helga
- Bishop, Jonathan
- Gonzalez, Ariel
- Kasani, Chaitanya
- Philippe, Vincent
- Schenone, Maria

> Ariel Gonzalez - Solutions Architect
