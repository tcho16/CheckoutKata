Tarikh Chouhan Kata Solution.
-

Prerequisites: 
-
- Maven 
- Java

Features:
-
- Able to add items.
- Able to calculate the cost of items regardless if they have a special offer.
- Able to view the list of items along with their price and special offer if they have any.
- Able to add special offer to items.
- For the UI, it's an 'interactive' console in which you type the number based on the selection present.
- Coded with the Command Pattern as I thought it would be the best use case given the situation.

Instructions:
-

- Run `mvn clean install` on the pom.
- Then go into the generated `target` folder and run the jar file with the command `java -jar ITVKata-1.0-SNAPSHOT.jar`

Assumptions made:
- 
- SKUs are not case sensitive. E.g. if item A exists, and you update the price of item `a`, then it'll update item A.
- Assumed prices and quantities will not go negative.
- Able to store SKUs with only capital letter
- Only integers. No decimals.

