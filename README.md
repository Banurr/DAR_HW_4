# HomeWork 4 Shelters

## Description
Short tutorial for project run

## Installation

To set up this project locally, follow these steps:

1. Clone this repository:
   ```bash
   git clone https://github.com/Banurr/DAR_HW_4.git

2. Set up docker containers
   ```bash
   sudo docker-compose up -d

3. Run the application

## Endpoints to check
All endpoints are written in the Shelter API postman collection

## Filter endpoint 
- Rules:
   - All query attributes are not necessary!
   - For paging some default values provided!
   - Values in the request are snakeCase!

### Searching by fields  
name type location

### Range of values  
min max (capacity, rating, averageAdoptionTime, dailyCost)
E.g. minCapacity=10  maxDailyCost=5

### Pagination
default : page = 0 size = 10 , you can modify by yourself in the query

### Sorting
rating, capacity, averageAdoptionTime
E.g. sortByRating=asc  sortByCapacity=desc 
