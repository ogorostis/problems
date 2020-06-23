# Problems

## Airplane Seat Map
One possible [solution](src/main/java/com/problems/SeatMap.java) ([tests](src/test/java/com/problems/SeatMapTests.java)) where:
- Hard rule is that they be seated on the same row.
- Preference is given to contiguous (no aisle separation) seats over aisle-separated seats
- Also returns aisle indices when party seating crosses aisles. 
```
mvn test -Dtest=SeatMapTests
```
